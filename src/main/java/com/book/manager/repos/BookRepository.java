package com.book.manager.repos;

import com.book.manager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 书籍
 * @Date 2020/7/14 16:12
 * @Author by Tuple
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    /**
     * ISBN编码查询
     * @param isbn
     * @return
     */
    Book findByIsbn(String isbn);

    /**
     * 根据id查询图书信息
     */

    Book findBookById(Integer id);

    /**
     * 根据名字查图书
     */

    Book findBookByName(String name);
}
