package com.book.manager.repos;

import com.book.manager.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 期刊
 * @Date 2020/7/14 16:12
 * @Author by Tuple
 */
@Repository
public interface JournalRepository extends JpaRepository<Journal,Integer> {
    /**
     * ISSN编码查询
     * @param issn
     * @return
     */
    Journal findByIssn(String issn);

    /**
     * 根据id查询期刊信息
     */

    Journal findJournalById(Integer id);

    /**
     * 根据名字查期刊
     */

    Journal findJournalByName(String name);
}
