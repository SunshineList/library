package com.book.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CountMapper {

    int countSex(String id);

    int countAge(String id);

    int countFren(String id);

    int countBookType(String id);

    int countJournalType(String id);

    int countBug(String id);

    int countAdd(String id);

    int countBorrowTime(String id);

    int countPj(String id);

}
