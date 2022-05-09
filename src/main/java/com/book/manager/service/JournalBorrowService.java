package com.book.manager.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.book.manager.dao.BorrowMapper;
import com.book.manager.dao.JournalBorrowMapper;
import com.book.manager.entity.*;
import com.book.manager.repos.*;
import com.book.manager.util.consts.Constants;
import com.book.manager.util.ro.PageIn;
import com.book.manager.util.vo.BorrowOut;
import com.book.manager.util.vo.JournalBorrowOut;
import com.book.manager.util.vo.PageOut;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class JournalBorrowService {
    @Autowired
    private JournalBorrowRepository journalBorrowRepository;

    @Autowired
    private JournalBorrowMapper journalBorrowMapper;

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JournalRepository journalRepository;

    /**
     * 添加
     * （添加事物）
     */
    @Transactional
    public Integer addBorrow(JournalBorrow journalBorrow) {
        Journal journal = journalService.findJournal(journalBorrow.getJournalId());
        Users users = userService.findUserById(journalBorrow.getUserId());

        // 查询是否已经借阅过该图书
        JournalBorrow bor = findBorrowByUserIdAndBookId(users.getId(),journal.getId());

        int isBorrow = findBorrowByBookId(journal.getId());

        /**
         * 一本只能借一次
         */

        if (isBorrow >= 1){
            return Constants.JOURNAL_ERROR;
        }

        if (bor!=null) {
            Integer ret = bor.getRet();
            if (ret!=null) {
                // 已借阅, 未归还 不可再借
                if (ret == Constants.NO) {
                    return Constants.BOOK_BORROWED;
                }
            }
        }

        // 库存数量减一
        int size = journal.getSize();
        if (size>0) {
            size--;
            journal.setSize(size);
            journalService.updateJournal(journal);
        }else {
            return Constants.BOOK_SIZE_NOT_ENOUGH;
        }

        // 用户可借数量减一
        int userSize = users.getQkSize();
        try {
            if (userSize>0) {
                userSize --;
                users.setQkSize(userSize);
                userService.updateUser(users);
            }else {
                return Constants.USER_SIZE_NOT_ENOUGH;
            }

        }catch (Exception e){
            return Constants.USER_SIZE_NOT_ENOUGH;
        }

        // 添加借阅信息, 借阅默认为未归还状态
        journalBorrow.setRet(Constants.NO);
        journalBorrow.setUsername(users.getUsername());
        journalBorrow.setJournalname(journal.getName());
        journalBorrowRepository.save(journalBorrow);

        // 一切正常
        return Constants.OK;
    }

    /**
     * user id查询所有借阅信息
     */
    public List<JournalBorrow> findAllBorrowByUserId(Integer userId) {
        return journalBorrowRepository.findBorrowByUserId(userId);
    }

    /**
     * user id查询所有 已借阅信息
     */
    public List<JournalBorrow> findBorrowsByUserIdAndRet(Integer userId, Integer ret) {
        return journalBorrowRepository.findBorrowsByUserIdAndRet(userId,ret);
    }


    /**
     * 详情
     */
    public Journal findById(Integer id) {
        Optional<Journal> optional = journalRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
     * 编辑
     */
    public boolean updateBorrow(JournalBorrow journalBorrow) {
        return journalBorrowMapper.updateJournalBorrow(journalBorrow)>0;
    }

    /**
     * 编辑
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBorrowInfo(JournalBorrow journalBorrow){
        return journalBorrowMapper.updateBor(BeanUtil.beanToMap(journalBorrow)) > 0;
    }

    /**
     * 编辑
     */
    public JournalBorrow updateBorrowByRepo(JournalBorrow journalBorrow) {
        return journalBorrowRepository.saveAndFlush(journalBorrow);
    }

    /**
     * s删除
     */
    public void deleteBorrow(Integer id) {
        journalRepository.deleteById(id);
    }

    /**
     * 查询用户某一条借阅信息
     * @param userId 用户id
     * @param journalId 图书id
     */
    public JournalBorrow findBorrowByUserIdAndBookId(int userId, int journalId) {
        return journalBorrowMapper.findBorrowByUserIdAndBookId(userId,journalId);
    }

    /**
     * 查看某本期刊是否被借出了
     */
    public int findBorrowByBookId(int journalId){
        return journalBorrowMapper.findBorrowByBookId(journalId);
    }

    /**
     * 编辑用户
     * @param book 图书对象
     * @return true or false
     */
    public boolean updateBook(Book book) {
        return journalBorrowMapper.updateBor(BeanUtil.beanToMap(book))>0;
    }

    /**
     * 归还书籍, 使用事务保证 ACID
     * @param userId 用户Id
     * @param journalId 书籍id
     */
    @Transactional(rollbackFor = Exception.class)
    public void retBook(int userId,int journalId) {
        // 用户可借数量加1
        Users user = userService.findUserById(userId);
        Integer size = user.getSize();
        size++;
        user.setSize(size);
        userService.updateUser(user);


        // 书籍库存加1
        Journal journal = journalService.findJournal(journalId);
        Integer bookSize = journal.getSize();
        bookSize++;
        journal.setSize(bookSize);
        journalService.updateJournal(journal);
        // 借阅记录改为已归还,删除记录
        JournalBorrow journalBorrow = this.findBorrowByUserIdAndBookId(userId, journalId);
        journalBorrow.setRet(Constants.YES);
        journalBorrow.setUpdateTime(new Date());
        this.updateBorrowInfo(journalBorrow);
    }


    /**
     * 期刊操作日志记录
     * @param pageIn
     * @return
     */

    public PageOut getLogList(PageIn pageIn) {

        PageHelper.startPage(pageIn.getCurrPage(),pageIn.getPageSize());

        List<JournalBorrow> list = journalBorrowMapper.findLogList(pageIn.getKeyword());

        PageInfo<JournalBorrow> pageInfo = new PageInfo<>(list);

        List<JournalBorrowOut> borrowOuts = new ArrayList<>();
        for (JournalBorrow journalBorrow : pageInfo.getList()) {
            JournalBorrowOut out = new JournalBorrowOut();
            BeanUtil.copyProperties(journalBorrow,out);
            if (out.getUsername() == null) {
                out.setUsername(usersRepository.findUsersById(out.getUserId()).getUsername());
            }
            if (out.getJournalname() == null) {
                out.setJournalname(journalRepository.findJournalById(out.getJournalId()).getName());
            }
            out.setEndTime(DateUtil.format(journalBorrow.getEndTime(), "yyyy-MM-dd"));
            out.setCreateTime(DateUtil.format(journalBorrow.getCreateTime(),"yyyy-MM-dd"));
            out.setUpdateTime(DateUtil.format(journalBorrow.getUpdateTime(), "yyyy-MM-dd"));
            borrowOuts.add(out);
        }

        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(borrowOuts);
        pageOut.setTotal((int)pageInfo.getTotal());
        pageOut.setCurrPage(pageInfo.getPageNum());
        pageOut.setPageSize(pageInfo.getPageSize());
        return pageOut;
    }
}
