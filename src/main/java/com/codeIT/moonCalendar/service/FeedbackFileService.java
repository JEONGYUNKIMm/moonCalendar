package com.codeIT.moonCalendar.service;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.codeIT.moonCalendar.dto.feedback.file.FeedbackFileResponseDto;
import com.codeIT.moonCalendar.entity.feedback.File.FeedbackFile;
import com.codeIT.moonCalendar.entity.feedback.File.FeedbackFileRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FeedbackFileService {

    private final FeedbackFileRepository feedbackFileRepository;

    public FeedbackFileResponseDto findById(Long id) throws Exception {
        return new FeedbackFileResponseDto(feedbackFileRepository.findById(id).get());
    }

    public List<Long> findByFeedbackId(Long feedbackId) throws Exception {
        return feedbackFileRepository.findByFeedbackId(feedbackId);
    }

    public boolean uploadFile(MultipartHttpServletRequest multiRequest, Long feedbackId) throws Exception {

        if (feedbackId == null) throw new NullPointerException("Empty FEEDBACK_ID.");

        Map<String, MultipartFile> files = multiRequest.getFileMap();

        Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();

        MultipartFile mFile;

        String savaFilePath = "", randomFileName = "";

        Calendar cal = Calendar.getInstance();

        List<Long> resultList = new ArrayList<Long>();

        while (itr.hasNext()) {

            Entry<String, MultipartFile> entry = itr.next();

            mFile = entry.getValue();

            int fileSize = (int) mFile.getSize();

            if (fileSize > 0) {

                String filePath = "C:\\Users\\SM-PC\\upload";


                filePath = filePath + File.separator + String.valueOf(cal.get(Calendar.YEAR)) + File.separator + String.valueOf(cal.get(Calendar.MONTH) + 1);
                randomFileName = "FILE_" + RandomStringUtils.random(8, 0, 0, false, true, null, new SecureRandom());

                String realFileName = mFile.getOriginalFilename();
                String fileExt = realFileName.substring(realFileName.lastIndexOf(".") + 1);
                String saveFileName = randomFileName + "." + fileExt;
                String saveFilePath = filePath + File.separator + saveFileName;

                File filePyhFolder = new File(filePath);

                if (!filePyhFolder.exists()) {
                    if (!filePyhFolder.mkdirs()) {
                        throw new Exception("File.mkdir() : Fail.");
                    }
                }

                File saveFile = new File(saveFilePath);

                if (saveFile.isFile()) {
                    boolean _exist = true;

                    int index = 0;

                    while (_exist) {
                        index++;

                        saveFileName = randomFileName + "(" + index + ")." + fileExt;

                        String dictFile = filePath + File.separator + saveFileName;

                        _exist = new File(dictFile).isFile();

                        if (!_exist) {
                            savaFilePath = dictFile;
                        }
                    }

                    mFile.transferTo(new File(savaFilePath));
                } else {
                    mFile.transferTo(saveFile);
                }

                FeedbackFile feedbackFile = FeedbackFile.builder()
                        .feedbackId(feedbackId)
                        .origFileName(realFileName)
                        .saveFileName(saveFileName)
                        .fileSize(fileSize)
                        .fileExt(fileExt)
                        .filePath(filePath)
                        .deleteYn("N")
                        .build();

                resultList.add(feedbackFileRepository.save(feedbackFile).getId());
            }
        }

        return (files.size() == resultList.size()) ? true : false;
    }

    public int updateDeleteYn(Long[] deleteIdList) throws Exception {
        return feedbackFileRepository.updateDeleteYn(deleteIdList);
    }

    public int deleteFeedbackFileYn(Long[] feedbackIdList) throws Exception {
        return feedbackFileRepository.deleteFeedbackFileYn(feedbackIdList);
    }
}