package com.book.manager.dao;

import com.book.manager.entity.Journal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description 期刊
 * @Date 2020/7/14 19:57
 * @Author by Tuple
 */
@Mapper
@Component
public interface JournalMapper {
    /**
     * 模糊分页查询用户
     * @param keyword 关键字
     * @return
     */
    List<Journal> findJournalListByLike(String keyword);

    /**
     * 编辑用户
     * @param map
     * @return
     */
    int updateJournal(Map<String, Object> map);

    /**
     * 期刊类型统计
     */

    int JournalTypeTj(String type);
}
