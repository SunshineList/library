package com.book.manager.controller;

import com.book.manager.dao.JournalBorrowMapper;
import com.book.manager.dao.JournalMapper;
import com.book.manager.entity.Journal;
import com.book.manager.service.JournalService;
import com.book.manager.util.R;
import com.book.manager.util.http.CodeEnum;
import com.book.manager.util.ro.PageIn;
import com.book.manager.util.vo.BookTjOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 期刊
 * @Date 2020/7/14 16:35
 * @Author by Tuple
 */
@Api(tags = "期刊管理")
@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private JournalMapper journalMapper;

    @ApiOperation("期刊搜索列表")
    @PostMapping("/list")
    public R getJournalList(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }
        return R.success(CodeEnum.SUCCESS,journalService.getJournalList(pageIn));
    }

    @ApiOperation("过期期刊列表")
    @PostMapping("/expired_list")
    public R getExpiredList(@RequestBody PageIn pageIn){
        if (pageIn == null){
            return R.fail(CodeEnum.PARAM_ERROR);
        }
        return R.success(CodeEnum.SUCCESS, journalService.getExpiredJournalList(pageIn));
    }

    @ApiOperation("添加期刊")
    @PostMapping("/add")
    public R addJournal(@RequestBody Journal journal) {
        return R.success(CodeEnum.SUCCESS,journalService.addJournal(journal));
    }

    @ApiOperation("编辑期刊")
    @PostMapping("/update")
    public R modifyJournalk(@RequestBody Journal journal) {
        return R.success(CodeEnum.SUCCESS,journalService.updateJournal(journal));
    }


    @ApiOperation("期刊详情")
    @GetMapping("/detail")
    public R JournalDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS,journalService.findJournalById(id));
    }

    @ApiOperation("期刊详情 根据ISSN获取")
    @GetMapping("/detailByIssn")
    public R JournalDetailByIsbn(String issn) {
        return R.success(CodeEnum.SUCCESS,journalService.findJournalByIssn(issn));
    }

    @ApiOperation("删除期刊")
    @GetMapping("/delete")
    public R delJournal(Integer id) {
        journalService.deleteJournal(id);
        return R.success(CodeEnum.SUCCESS);
    }

    @ApiOperation("批量删除过期期刊")
    @PostMapping("/all_delete")
    public R allDeLeteJournal(){
        journalService.deleteExpiredJournal();
        return R.success(CodeEnum.SUCCESS);
    }

    @ApiModelProperty("期刊类型统计")
    @GetMapping("/journal_statistics")
    public R bookStatistics(){
        BookTjOut out = new BookTjOut();
        out.setSx(journalMapper.JournalTypeTj("1"));
        out.setZx(journalMapper.JournalTypeTj("2"));
        out.setWh(journalMapper.JournalTypeTj("3"));
        out.setZf(journalMapper.JournalTypeTj("4"));
        out.setLd(journalMapper.JournalTypeTj("5"));
        return R.success(CodeEnum.SUCCESS, out);
    }

}
