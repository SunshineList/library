package com.book.manager.controller;


import com.book.manager.entity.Book;
import com.book.manager.entity.Purchase;
import com.book.manager.service.PurchaseService;
import com.book.manager.util.R;
import com.book.manager.util.http.CodeEnum;
import com.book.manager.util.ro.PageIn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "采购管理")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @ApiOperation("添加采购")
    @PostMapping("/addPurchase")
    public R addPurchase(@RequestBody Purchase purchase) {
        return R.success(CodeEnum.SUCCESS, purchaseService.addPurchase(purchase));
    }


    @ApiOperation("编辑采购")
    @PostMapping("/update")
    public R modifyPurchase(@RequestBody Purchase purchase) {
        return R.success(CodeEnum.SUCCESS,purchaseService.updatePurchase(purchase));
    }

    @ApiOperation("采购详情")
    @GetMapping("/detail")
    public R purchaseDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS,purchaseService.findBookById(id));
    }


    @ApiOperation("删除图书")
    @GetMapping("/delete")
    public R delPurchase(Integer id) {
        purchaseService.deletePurchase(id);
        return R.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("审核记录")
    @GetMapping("/check")
    public R checkPurchase(Integer id, String status){
        purchaseService.checkPurchase(id, status);
        return R.success(CodeEnum.SUCCESS);
    }

    @ApiOperation("采购记录")
    @PostMapping("/list")
    public R purchaseList(@RequestBody PageIn pageIn){


        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }

        return R.success(CodeEnum.SUCCESS, purchaseService.getPurchaseList(pageIn));
    }
}
