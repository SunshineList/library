package com.book.manager.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.book.manager.dao.JournalMapper;
import com.book.manager.entity.Journal;
import com.book.manager.repos.JournalRepository;
import com.book.manager.util.ro.PageIn;
import com.book.manager.util.vo.JournalOut;
import com.book.manager.util.vo.PageOut;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository JournalRepository;

    @Autowired
    private JournalMapper JournalMapper;


    /**
     * 添加用户
     * @param Journal 图书
     * @return 返回添加的图书
     */
    public Journal addJournal(Journal Journal) {
        return JournalRepository.saveAndFlush(Journal);
    }

    /**
     * 编辑用户
     * @param Journal 图书对象
     * @return true or false
     */
    public boolean updateJournal(Journal Journal) {
        return JournalMapper.updateJournal(BeanUtil.beanToMap(Journal))>0;
    }

    /**
     * 图书详情
     * @param id 主键
     * @return 图书详情
     */
    public JournalOut findJournalById(Integer id) {
        Optional<Journal> optional = JournalRepository.findById(id);
        if (optional.isPresent()) {
            Journal Journal = optional.get();
            JournalOut out = new JournalOut();
            BeanUtil.copyProperties(Journal,out);
            out.setPublishTime(DateUtil.format(Journal.getPublishTime(),"yyyy-MM-dd"));
            return out;
        }
        return null;
    }

    public Journal findJournal(Integer id) {
        Optional<Journal> optional = JournalRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
     * ISSN查询
     * @param issn
     * @return
     */
    public JournalOut findJournalByIssn(String issn) {
        System.out.println(issn);
        Journal journal = JournalRepository.findByIssn(issn);
        System.out.println(journal);
        JournalOut out = new JournalOut();
        if (journal == null) {
            return out;
        }
        BeanUtil.copyProperties(journal,out);
        out.setPublishTime(DateUtil.format(journal.getPublishTime(),"yyyy-MM-dd"));
        return out;
    }

    /**
     * 删除图书
     * @param id 主键
     * @return true or false
     */
    public void deleteJournal(Integer id) {
        JournalRepository.deleteById(id);
    }

    /**
     * 批量删除过期期刊
     */

    public void deleteExpiredJournal(){
        JournalMapper.deleteExpiredJournal();
    }


    /**
     * 图书搜索查询(mybatis 分页)
     * @param pageIn
     * @return
     */
    public PageOut getJournalList(PageIn pageIn) {

        PageHelper.startPage(pageIn.getCurrPage(),pageIn.getPageSize());
        List<Journal> list = JournalMapper.findJournalListByLike(pageIn.getKeyword());
        PageInfo<Journal> pageInfo = new PageInfo<>(list);

        List<JournalOut> JournalOuts = new ArrayList<>();
        for (Journal Journal : pageInfo.getList()) {
            JournalOut out = new JournalOut();
            BeanUtil.copyProperties(Journal,out);
            out.setPublishTime(DateUtil.format(Journal.getPublishTime(),"yyyy-MM-dd"));
            JournalOuts.add(out);
        }

        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(JournalOuts);
        pageOut.setTotal((int)pageInfo.getTotal());
        pageOut.setCurrPage(pageInfo.getPageNum());
        pageOut.setPageSize(pageInfo.getPageSize());
        return pageOut;
    }

    /**
     * 过期期刊搜索查询(mybatis 分页)
     * @param pageIn
     * @return
     */
    public PageOut getExpiredJournalList(PageIn pageIn) {

        PageHelper.startPage(pageIn.getCurrPage(),pageIn.getPageSize());
        List<Journal> list = JournalMapper.findExpiredJournalList(pageIn.getKeyword());
        PageInfo<Journal> pageInfo = new PageInfo<>(list);

        List<JournalOut> JournalOuts = new ArrayList<>();
        for (Journal Journal : pageInfo.getList()) {
            JournalOut out = new JournalOut();
            BeanUtil.copyProperties(Journal,out);
            out.setPublishTime(DateUtil.format(Journal.getPublishTime(),"yyyy-MM-dd"));
            JournalOuts.add(out);
        }

        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(JournalOuts);
        pageOut.setTotal((int)pageInfo.getTotal());
        pageOut.setCurrPage(pageInfo.getPageNum());
        pageOut.setPageSize(pageInfo.getPageSize());
        return pageOut;
    }
}
