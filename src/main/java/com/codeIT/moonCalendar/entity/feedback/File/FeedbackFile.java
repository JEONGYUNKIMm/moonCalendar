package com.codeIT.moonCalendar.entity.feedback.File;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class FeedbackFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long feedbackId;
    private String origFileName;
    private String saveFileName;
    private int fileSize;
    private String fileExt;
    private String filePath;
    private String deleteYn;

    @CreatedDate
    private LocalDateTime registerTime;

    @Builder
    public FeedbackFile(Long id, Long feedbackId, String origFileName, String saveFileName, int fileSize, String fileExt, String filePath, String deleteYn, LocalDateTime registerTime){
        this.id = id;
        this.feedbackId = feedbackId;
        this.origFileName = origFileName;
        this.saveFileName = saveFileName;
        this.fileSize = fileSize;
        this.fileExt = fileExt;
        this.filePath = filePath;
        this.deleteYn = deleteYn;
        this.registerTime = registerTime;
    }
}
