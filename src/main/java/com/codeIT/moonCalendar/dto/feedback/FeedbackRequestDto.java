package com.codeIT.moonCalendar.dto.feedback;

import com.codeIT.moonCalendar.entity.feedback.Feedback;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String email;

    public Feedback toEntity(){
        return Feedback.builder()
                .title(title)
                .contents(contents)
                .email(email)
                .build();
    }

    @Override
    public String toString(){
        return "FeedbackRequestDto [id= " + id
                + ", title = " + title
                + ", contents= " + contents
                + ", email= " + email + "]";
    }
}
