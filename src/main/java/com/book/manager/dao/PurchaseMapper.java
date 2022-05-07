package com.book.manager.dao;

import com.book.manager.entity.Borrow;
import com.book.manager.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description 采购
 * @Date 2020/7/15 16:45
 * @Author by Tuple
 */
@Mapper
@Component

public interface PurchaseMapper {

//    @Update("update borrow set user_id = #{userId},book_id = #{bookId},update_time = #{updateTime} where id = #{id}")
//    int updateBorrow(Borrow borrow);

//    @Select("select * from borrow where user_id = #{userId} and book_id = #{bookId} and ret = 1")
//    Borrow findBorrowByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    int updatePurchase(Map<String,Object> map);

    List<Purchase> findPurchaseList(String keyword);

    int checkPurchase(Integer id, String status);
}
