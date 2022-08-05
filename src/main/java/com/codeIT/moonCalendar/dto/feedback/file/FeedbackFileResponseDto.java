package com.codeIT.moonCalendar.dto.feedback.file;

import com.codeIT.moonCalendar.entity.feedback.File.FeedbackFile;
import lombok.Getter;

@Getter
public class FeedbackFileResponseDto {

    private String origFileName;
    private String saveFileName;
    private String filePath;

    public FeedbackFileResponseDto(FeedbackFile entity){
        this.origFileName = entity.getOrigFileName();
        this.saveFileName = entity.getSaveFileName();
        this.filePath = entity.getFilePath();
    }

    @Override
    public String toString(){
        return "FileMstResponseDto [origFileName= " + origFileName + ", saveFileName= " + saveFileName + ", filePath= " + filePath + "]";
    }
}
