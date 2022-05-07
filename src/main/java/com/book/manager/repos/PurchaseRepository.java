package com.book.manager.repos;


import com.book.manager.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 采购
 * @Date 2020/7/14 16:12
 * @Author by Tuple
 */
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

}
