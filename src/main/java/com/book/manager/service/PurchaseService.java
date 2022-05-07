package com.book.manager.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.book.manager.dao.PurchaseMapper;
import com.book.manager.entity.Book;
import com.book.manager.entity.Borrow;
import com.book.manager.entity.Purchase;
import com.book.manager.repos.PurchaseRepository;
import com.book.manager.util.consts.ConvertUtil;
import com.book.manager.util.ro.PageIn;
import com.book.manager.util.vo.BookOut;
import com.book.manager.util.vo.BorrowOut;
import com.book.manager.util.vo.PageOut;
import com.book.manager.util.vo.PurchaseOut;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description 采购业务类
 * @Date 2020/7/14 16:31
 * @Author by Tuple
 */

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;


    /**
     * 添加采购记录
     *
     * @param purchase 采购
     * @return 返回添加的采购记录
     */

    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepository.saveAndFlush(purchase);
    }


    /**
     * 编辑采购记录
     * @param purchase 采购对象
     * @return true or false
     */
    public boolean updatePurchase(Purchase purchase) {
        return purchaseMapper.updatePurchase(BeanUtil.beanToMap(purchase))>0;
    }


    /**
     * 删除采购记录
     * @param id 主键
     * @return true or false
     */
    public void deletePurchase(Integer id) {
        purchaseRepository.deleteById(id);
    }

    /**
     * 审核采购记录
     */

    public void  checkPurchase(Integer id, String status){
        purchaseMapper.checkPurchase(id, status);
    }


    /**
     * 采购详情
     * @param id 主键
     * @return 采购详情
     */
    public PurchaseOut findBookById(Integer id) {
        Optional<Purchase> optional = purchaseRepository.findById(id);
        if (optional.isPresent()) {
            Purchase purchase = optional.get();
            PurchaseOut out = new PurchaseOut();
            BeanUtil.copyProperties(purchase,out);
            out.setCreateTime(DateUtil.format(purchase.getCreateTime(),"yyyy-MM-dd"));
            out.setStatus(ConvertUtil.statusPurchase(purchase.getStatus()));
            return out;
        }
        return null;
    }

    /**
     * 采购记录
     *
     * @param pageIn
     * @return
     */

    public PageOut getPurchaseList(PageIn pageIn) {

        PageHelper.startPage(pageIn.getCurrPage(), pageIn.getPageSize());

        List<Purchase> list = purchaseMapper.findPurchaseList(pageIn.getKeyword());

        PageInfo<Purchase> pageInfo = new PageInfo<>(list);

        List<PurchaseOut> purchaseOuts = new ArrayList<>();
        for (Purchase purchase : pageInfo.getList()) {
            PurchaseOut out = new PurchaseOut();
            BeanUtil.copyProperties(purchase, out);
            out.setStatus(ConvertUtil.statusPurchase(out.getStatus()));
            out.setCreateTime(DateUtil.format(purchase.getCreateTime(),"yyyy-MM-dd"));
            purchaseOuts.add(out);
        }

        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(purchaseOuts);
        pageOut.setTotal((int) pageInfo.getTotal());
        pageOut.setCurrPage(pageInfo.getPageNum());
        pageOut.setPageSize(pageInfo.getPageSize());
        return pageOut;
    }

}
