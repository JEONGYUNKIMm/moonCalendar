package com.codeIT.moonCalendar.dto.feedback.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackFileRequestDto {
    private Long id;
    private Long[] idArr;
    private String fileId;
}
