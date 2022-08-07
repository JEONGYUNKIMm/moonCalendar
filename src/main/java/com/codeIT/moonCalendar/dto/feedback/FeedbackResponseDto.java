package com.codeIT.moonCalendar.dto.feedback;

import com.codeIT.moonCalendar.entity.feedback.Feedback;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class FeedbackResponseDto {
    private Long id;
    private String title;
    private String contents;
    private int readCnt;
    private String email;
    private LocalDateTime registerTime;

    public FeedbackResponseDto(Feedback entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.readCnt = entity.getRead_cnt();
        this.email = entity.getEmail();
        this.registerTime = entity.getRegisterTime();
    }

    @Override
    public String toString(){
        return "FeedbackListDto [id=" + id + ", title=" + title + ", contents=" + contents +
                ", readCnt=" + readCnt + ", email=" + email + ", registerTime=" + registerTime + "]";
    }

    public String getRegisterTime(){
        return toStringDateTime(this.registerTime);
    }

    public static String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
