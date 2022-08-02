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
    private int read_cnt;
    private String email;
    private LocalDateTime register_time;

    public FeedbackResponseDto(Feedback entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.read_cnt = entity.getRead_cnt();
        this.email = entity.getEmail();
        this.register_time = entity.getRegister_time();
    }

    @Override
    public String toString(){
        return "FeedbackListDto [id=" + id + ", title=" + title + ", contents=" + contents +
                ", read_cnt=" + read_cnt + ", email=" + email + ", register_time=" + register_time + "]";
    }

    public String getRegister_time(){
        return toStringDateTime(this.register_time);
    }

    public static String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
