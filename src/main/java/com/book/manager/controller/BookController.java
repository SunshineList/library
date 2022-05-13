package com.book.manager.controller;

import com.book.manager.dao.BookMapper;
import com.book.manager.entity.Book;
import com.book.manager.service.BookService;
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
 * @Description 用户管理
 * @Date 2020/7/14 16:35
 * @Author by Tuple
 */
@Api(tags = "图书管理")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @ApiOperation("图书搜索列表")
    @PostMapping("/list")
    public R getBookList(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }
        return R.success(CodeEnum.SUCCESS,bookService.getBookList(pageIn));
    }

    @ApiOperation("添加图书")
    @PostMapping("/add")
    public R addBook(@RequestBody Book book) {
        return R.success(CodeEnum.SUCCESS,bookService.addBook(book));
    }

    @ApiOperation("编辑图书")
    @PostMapping("/update")
    public R modifyBook(@RequestBody Book book) {
        return R.success(CodeEnum.SUCCESS,bookService.updateBook(book));
    }


    @ApiOperation("图书详情")
    @GetMapping("/detail")
    public R bookDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS,bookService.findBookById(id));
    }

    @ApiOperation("图书详情 根据ISBN获取")
    @GetMapping("/detailByIsbn")
    public R bookDetailByIsbn(String isbn) {
        return R.success(CodeEnum.SUCCESS,bookService.findBookByIsbn(isbn));
    }

    @ApiOperation("删除图书")
    @GetMapping("/delete")
    public R delBook(Integer id) {
        bookService.deleteBook(id);
        return R.success(CodeEnum.SUCCESS);
    }

    @ApiModelProperty("图书类型统计")
    @GetMapping("/book_statistics")
    public R bookStatistics(){
        BookTjOut out = new BookTjOut();
        out.setSx(bookMapper.bookTypeTj("1"));
        out.setZx(bookMapper.bookTypeTj("2"));
        out.setWh(bookMapper.bookTypeTj("3"));
        out.setZf(bookMapper.bookTypeTj("4"));
        out.setLd(bookMapper.bookTypeTj("5"));
        return R.success(CodeEnum.SUCCESS, out);
    }
}
