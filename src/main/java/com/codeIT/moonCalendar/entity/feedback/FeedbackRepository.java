package com.codeIT.moonCalendar.entity.feedback;

import com.codeIT.moonCalendar.dto.feedback.FeedbackRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FeedbackRepository extends JpaRepository <Feedback, Long> {

    static final String UPDATE_FEEDBACK = "UPDATE Feedback " +
            "SET TITLE = :#{#feedbackRequestDto.title}, " +
            "CONTENTS = :#{#feedbackRequestDto.contents}, " +
            "EMAIL = :#{#feedbackRequestDto.email}, " +
            "UPDATE_TIME = NOW() " +
            "WHERE ID = :#{#feedbackRequestDto.id}";

    static final String UPDATE_FEEDBACK_READ_CNT_INC = "UPDATE Feedback " +
            "SET READ_CNT = READ_CNT + 1 " +
            "WHERE ID = :id";

    @Transactional
    @Modifying
    @Query(value = UPDATE_FEEDBACK, nativeQuery = true)
    public int updateFeedback(@Param("feedbackRequestDto") FeedbackRequestDto feedbackRequestDto);

    @Transactional
    @Modifying
    @Query(value = UPDATE_FEEDBACK_READ_CNT_INC, nativeQuery = true)
    public int updateFeedbackReadCntInc(@Param("id") Long id);

}
