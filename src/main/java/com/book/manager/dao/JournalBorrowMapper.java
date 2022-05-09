package com.book.manager.dao;

import com.book.manager.entity.Borrow;
import com.book.manager.entity.JournalBorrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface JournalBorrowMapper {
    @Update("update journal_borrow set user_id = #{userId},journal_id = #{journalId},update_time = #{updateTime} where id = #{id}")
    int updateJournalBorrow(JournalBorrow journalBorrow);

    @Select("select * from journal_borrow where user_id = #{userId} and journal_id = #{journalId} and ret = 1")
    JournalBorrow findBorrowByUserIdAndBookId(@Param("userId") Integer userId, @Param("journalId") Integer journalId);

    int updateBor(Map<String,Object> map);

    List<JournalBorrow> findLogList(String keyword);
}
