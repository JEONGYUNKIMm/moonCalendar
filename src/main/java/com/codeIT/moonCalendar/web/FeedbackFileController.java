package com.codeIT.moonCalendar.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import com.codeIT.moonCalendar.dto.feedback.file.FeedbackFileRequestDto;
import com.codeIT.moonCalendar.dto.feedback.file.FeedbackFileResponseDto;
import com.codeIT.moonCalendar.service.FeedbackFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FeedbackFileController {

    private final FeedbackFileService feedbackFileService;

    @GetMapping("/file/download")
    public void downloadFile(@RequestParam() Long id, HttpServletResponse response) throws Exception {
        try {
            FeedbackFileResponseDto fileInfo = feedbackFileService.findById(id);

            if (fileInfo == null) throw new FileNotFoundException("Empty FileData.");

            File dFile  = new File(fileInfo.getFilePath(), fileInfo.getSaveFileName());

            int fSize = (int) dFile.length();

            if (fSize > 0) {
                String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileInfo.getOrigFileName(), "UTF-8");

                response.setContentType("application/octet-stream; charset=utf-8");

                response.setHeader("Content-Disposition", encodedFilename);

                response.setContentLengthLong(fSize);

                BufferedInputStream in = null;
                BufferedOutputStream out = null;


                in = new BufferedInputStream(new FileInputStream(dFile));


                out = new BufferedOutputStream(response.getOutputStream());

                try {
                    byte[] buffer = new byte[4096];
                    int bytesRead=0;

                    while ((bytesRead = in.read(buffer))!=-1) {
                        out.write(buffer, 0, bytesRead);
                    }

                    out.flush();
                }
                finally {

                    in.close();
                    out.close();
                }
            } else {
                throw new FileNotFoundException("Empty FileData.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/file/delete.ajax")
    public String updateDeleteYn(Model model, FeedbackFileRequestDto feedbackFileRequestDto) throws Exception {
        try {
            model.addAttribute("result", feedbackFileService.updateDeleteYn(feedbackFileRequestDto.getIdArr()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "jsonView";
    }
}