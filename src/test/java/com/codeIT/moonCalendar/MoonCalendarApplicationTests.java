package com.codeIT.moonCalendar;


import com.codeIT.moonCalendar.dto.feedback.FeedbackRequestDto;
import com.codeIT.moonCalendar.dto.feedback.FeedbackResponseDto;
import com.codeIT.moonCalendar.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MoonCalendarApplicationTests {
    @Autowired
    private FeedbackService feedbackService;

    @Test
    void save(){
        FeedbackRequestDto feedbackSaveDto = new FeedbackRequestDto();

        feedbackSaveDto.setTitle("피드백 제목입니다.");
        feedbackSaveDto.setContents("피드백 내용입니다.");
        feedbackSaveDto.setEmail("회신 이메일");

        Long result = feedbackService.save(feedbackSaveDto);

        if (result > 0){
            System.out.println("# Success save() ~");
            findAll();
            findById(result);
        } else {
            System.out.println("# Fail save() ~");
        }
    }

    void findAll(){
        /*List<FeedbackResponseDto> list = feedbackService.findAll();

        if (list != null) {
            System.out.println("# Success findAll() : " + list.toString());
        } else {
            System.out.println("# Fail findAll() ~");
        }*/
    }

    void findById(Long id){
        FeedbackResponseDto info = feedbackService.findById(id);

        if (info != null){
            System.out.println("# Success findById() : " + info.toString());
        } else {
            System.out.println("# Fail findById() ~");
        }
    }

    void updateFeedback(Long id){
        FeedbackRequestDto feedbackRequestDto = new FeedbackRequestDto();

        feedbackRequestDto.setId(id);
        feedbackRequestDto.setTitle("업데이트 피드백 제목");
        feedbackRequestDto.setContents("업데이트 피드백 유형");
        feedbackRequestDto.setEmail("회신 이메일");

        int result = feedbackService.updateFeedback(feedbackRequestDto);

        if (result > 0){
            System.out.println("# Success updateFeedback() ~");
        } else {
            System.out.println("# Fail updateFeedback() ~");
        }
    }
}
