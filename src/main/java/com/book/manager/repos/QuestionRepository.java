package com.book.manager.repos;

import com.book.manager.entity.Question;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    /**
     * 查看当前登录用户是否做了问卷
     * @param user_id
     * @return
     */

    Question findQuestionByUserId(@Param("user_id") Integer user_id);

}
