package com.book.manager.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.book.manager.dao.BookMapper;
import com.book.manager.dao.BorrowMapper;
import com.book.manager.entity.Borrow;
import com.book.manager.entity.Journal;
import com.book.manager.entity.JournalBorrow;
import com.book.manager.repos.BookRepository;
import com.book.manager.repos.UsersRepository;
import com.book.manager.service.BookService;
import com.book.manager.service.BorrowService;
import com.book.manager.service.JournalBorrowService;
import com.book.manager.service.JournalService;
import com.book.manager.util.R;
import com.book.manager.util.consts.Constants;
import com.book.manager.util.http.CodeEnum;
import com.book.manager.util.ro.PageIn;
import com.book.manager.util.ro.RetBookIn;
import com.book.manager.util.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 期刊借阅管理
 * @Date 2020/7/14 16:35
 * @Author by Tuple
 */
@Api(tags = "借阅管理")
@RestController
@RequestMapping("/journal_borrow")
public class JournalBorrowController {

    @Autowired
    private JournalBorrowService borrowService;

    @Autowired
    private JournalService journalService;


    @ApiOperation("借阅列表")
    @GetMapping("/list")
    public R getBorrowList(Integer userId) {
        return R.success(CodeEnum.SUCCESS,borrowService.findAllBorrowByUserId(userId));
    }

    @ApiOperation("借阅图书")
    @PostMapping("/add")
    public R addBorrow(@RequestBody JournalBorrow journalBorrow) {
        Integer result = borrowService.addBorrow(journalBorrow);
        if (result == Constants.BOOK_BORROWED) {
            return R.success(CodeEnum.BOOK_BORROWED);
        }else if (result == Constants.USER_SIZE_NOT_ENOUGH) {
            return R.success(CodeEnum.USER_NOT_ENOUGH);
        }else if (result == Constants.BOOK_SIZE_NOT_ENOUGH) {
            return R.success(CodeEnum.BOOK_NOT_ENOUGH);
        }
        return R.success(CodeEnum.SUCCESS,Constants.OK);
    }

    @ApiOperation("编辑借阅")
    @PostMapping("/update")
    public R modifyBorrow(@RequestBody JournalBorrow journalBorrow) {
        return R.success(CodeEnum.SUCCESS,borrowService.updateBorrow(journalBorrow));
    }


    @ApiOperation("借阅详情")
    @GetMapping("/detail")
    public R borrowDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS,borrowService.findById(id));
    }

    @ApiOperation("删除归还记录")
    @GetMapping("/delete")
    public R delBorrow(Integer id) {
        borrowService.deleteBorrow(id);
        return R.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("已借阅列表")
    @GetMapping("/borrowed")
    public R borrowedList(Integer userId) {
        List<JournalBackOut> outs = new ArrayList<>();
        if (userId!=null&&userId>0) {
            // 获取所有 已借阅 未归还书籍
            List<JournalBorrow> borrows = borrowService.findBorrowsByUserIdAndRet(userId, Constants.NO);
            for (JournalBorrow journalBorrow : borrows) {
                JournalBackOut backOut = new JournalBackOut();
                Journal out = journalService.findJournal(journalBorrow.getJournalId());
                BeanUtils.copyProperties(out,backOut);

                backOut.setBorrowTime(DateUtil.format(journalBorrow.getCreateTime(),Constants.DATE_FORMAT));

                String endTimeStr = DateUtil.format(journalBorrow.getEndTime(), Constants.DATE_FORMAT);
                backOut.setEndTime(endTimeStr);
                // 判断是否逾期
                String toDay = DateUtil.format(new Date(), Constants.DATE_FORMAT);
                int i = toDay.compareTo(endTimeStr);
                if (i>0) {
                    backOut.setLate(Constants.YES_STR);
                }else {
                    backOut.setLate(Constants.NO_STR);
                }

                outs.add(backOut);
            }
        }

        return R.success(CodeEnum.SUCCESS,outs);
    }

    @ApiOperation("归还书籍")
    @PostMapping("/ret")
    public R retBook(Integer userId, Integer bookId) {
        // 归还图书
        borrowService.retBook(userId,bookId);
        return R.success(CodeEnum.SUCCESS);
    }

    @ApiOperation("借阅操作日志记录")
    @PostMapping("/borrow_log")
    public R borrowLog(@RequestBody PageIn pageIn){

        System.out.println(pageIn);

        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }

        return R.success(CodeEnum.SUCCESS, borrowService.getLogList(pageIn));
    }

}
