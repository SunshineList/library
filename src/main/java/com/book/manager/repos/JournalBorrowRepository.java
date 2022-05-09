package com.book.manager.repos;

import com.book.manager.entity.Borrow;
import com.book.manager.entity.Journal;
import com.book.manager.entity.JournalBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalBorrowRepository extends JpaRepository<JournalBorrow, Integer> {
    /**
     * 查询借阅信息
     * @param userId 用户id
     * @return
     */
    List<JournalBorrow> findBorrowByUserId(Integer userId);

    /**
     * 查询已借阅信息
     * @param userId 用户id
     * @param ret 是否归还, 0 已归还/ 1 未归还
     * @return
     */
    List<JournalBorrow> findBorrowsByUserIdAndRet(@Param("userId") Integer userId, @Param("ret")Integer ret);

    /**
     * 查询用户某一条借阅信息
     * @param userId 用户id
     * @param journalId 图书id
     */
    JournalBorrow findBorrowByUserIdAndJournalId(@Param("userId") Integer userId, @Param("journalId") Integer journalId);
}
