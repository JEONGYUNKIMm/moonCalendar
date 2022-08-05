package com.codeIT.moonCalendar.entity.feedback.File;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FeedbackFileRepository extends JpaRepository<FeedbackFile, Long> {

    static final String SELECT_FILE_ID= "SELECT ID FROM feedback_file "
            + "WHERE FEEDBACK_ID = :feedbackId AND DELETE_YN != 'Y'";

    static final String UPDATE_DELETE_YN= "UPDATE feedback_file "
            + "SET DELETE_YN = 'Y' "
            + "WHERE ID IN (:deleteIdList)";

    static final String DELETE_FEEDBACK_FILE_YN= "UPDATE feedback_file "
            + "SET DELETE_YN = 'Y' "
            + "WHERE FEEDBACK_ID IN (:feedbackIdList)";

    @Query(value = SELECT_FILE_ID, nativeQuery = true)
    public List<Long> findByFeedbackId(@Param("feedbackId") Long feedbackId);

    @Transactional
    @Modifying
    @Query(value = UPDATE_DELETE_YN, nativeQuery = true)
    public int updateDeleteYn(@Param("deleteIdList") Long[] deleteIdList);

    @Transactional
    @Modifying
    @Query(value = DELETE_FEEDBACK_FILE_YN, nativeQuery = true)
    public int deleteFeedbackFileYn(@Param("feedbackIdList") Long[] feedbackIdList);
}
