package com.book.manager.controller;

import com.book.manager.dao.CountMapper;
import com.book.manager.entity.Question;
import com.book.manager.entity.Users;
import com.book.manager.repos.QuestionRepository;
import com.book.manager.util.R;
import com.book.manager.util.http.CodeEnum;
import com.book.manager.util.vo.tj.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "调查问卷")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CountMapper countMapper;

    @ApiOperation("回答问卷")
    @PostMapping("/add")
    public R addQuestion(@RequestBody Question question) {
        return R.success(CodeEnum.SUCCESS, questionRepository.saveAndFlush(question));
    }

    @ApiOperation("查看是否已做问卷")
    @GetMapping("is_over")
    public R queryQuestion(Integer user_id){
        Question question = questionRepository.findQuestionByUserId(user_id);

        if (question != null){
            return R.success(CodeEnum.HAS_DONE); // 不为空已做 不给显示了
        }
        return R.success(CodeEnum.SUCCESS);
    }

    @ApiOperation("第一题统计")
    @GetMapping("/sex")
    public R countSex(){

        SexOut out = new SexOut();
        out.setMale(countMapper.countSex("1"));
        out.setFemale(countMapper.countSex("2"));
        return R.success(CodeEnum.SUCCESS, out);

    }

    @ApiOperation("第二题统计")
    @GetMapping("/age")
    public R countAge(){

        AgeOut out = new AgeOut();
        out.setAnswer_1(countMapper.countAge("1"));
        out.setAnswer_2(countMapper.countAge("2"));
        out.setAnswer_3(countMapper.countAge("3"));
        out.setAnswer_4(countMapper.countAge("4"));
        return R.success(CodeEnum.SUCCESS, out);

    }

    @ApiOperation("第三题统计")
    @GetMapping("/fren")
    public R countFren(){
        FrenOut out = new FrenOut();
        out.setAnswer_1(countMapper.countFren("1"));
        out.setAnswer_2(countMapper.countFren("2"));
        out.setAnswer_3(countMapper.countFren("3"));
        out.setAnswer_4(countMapper.countFren("4"));
        return R.success(CodeEnum.SUCCESS, out);
    }

    @ApiOperation("第四题统计")
    @GetMapping("/book_type")
    public R countBookType(){
        BookTypeOut out = new BookTypeOut();
        out.setAnswer_1(countMapper.countBookType("1"));
        out.setAnswer_2(countMapper.countBookType("2"));
        out.setAnswer_3(countMapper.countBookType("3"));
        out.setAnswer_4(countMapper.countBookType("4"));
        out.setAnswer_5(countMapper.countBookType("5"));
        return R.success(CodeEnum.SUCCESS, out);
    }

    @ApiOperation("第五题统计")
    @GetMapping("/journal_type")
    public R countJournalType(){
        JournalTypeOut out = new JournalTypeOut();
        out.setAnswer_1(countMapper.countJournalType("1"));
        out.setAnswer_2(countMapper.countJournalType("2"));
        out.setAnswer_3(countMapper.countJournalType("3"));
        out.setAnswer_4(countMapper.countJournalType("4"));
        out.setAnswer_5(countMapper.countJournalType("5"));
        return R.success(CodeEnum.SUCCESS, out);
    }

    @ApiOperation("第六题统计")
    @GetMapping("/bug")
    public R countBug(){
        BugOut out = new BugOut();
        out.setAnswer_1(countMapper.countBug("1"));
        out.setAnswer_2(countMapper.countBug("2"));
        out.setAnswer_3(countMapper.countBug("3"));
        return R.success(CodeEnum.SUCCESS, out);
    }

    @ApiOperation("第七题统计")
    @GetMapping("/count_add")
    public R countAdd(){
        AddOut out = new AddOut();
        out.setAnswer_1(countMapper.countAdd("1"));
        out.setAnswer_2(countMapper.countAdd("2"));
        out.setAnswer_3(countMapper.countAdd("3"));
        out.setAnswer_4(countMapper.countAdd("4"));
        return R.success(CodeEnum.SUCCESS, out);
    }

    @ApiOperation("第八题统计")
    @GetMapping("/borrow_time")
    public R countBorrowTime(){
        BorrowTimeOut out = new BorrowTimeOut();
        out.setAnswer_1(countMapper.countBorrowTime("1"));
        out.setAnswer_2(countMapper.countBorrowTime("2"));
        out.setAnswer_3(countMapper.countBorrowTime("3"));
        out.setAnswer_4(countMapper.countBorrowTime("4"));
        return R.success(CodeEnum.SUCCESS, out);
    }

    @ApiOperation("第九题统计")
    @GetMapping("/pj")
    public R countPj(){
        PjOut out = new PjOut();
        out.setAnswer_1(countMapper.countPj("1"));
        out.setAnswer_2(countMapper.countPj("2"));
        out.setAnswer_3(countMapper.countPj("3"));
        out.setAnswer_4(countMapper.countPj("4"));
        return R.success(CodeEnum.SUCCESS, out);
    }

}
