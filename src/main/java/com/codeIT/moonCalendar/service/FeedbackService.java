package com.codeIT.moonCalendar.service;



import com.codeIT.moonCalendar.dto.feedback.FeedbackRequestDto;
import com.codeIT.moonCalendar.dto.feedback.FeedbackResponseDto;
import com.codeIT.moonCalendar.entity.feedback.Feedback;
import com.codeIT.moonCalendar.entity.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackFileService feedbackFileService;

    @Transactional
    public boolean save(FeedbackRequestDto feedbackRequestDto, MultipartHttpServletRequest multiRequest) throws Exception {

        Feedback result = feedbackRepository.save(feedbackRequestDto.toEntity());

        boolean resultFlag = false;

        if(result != null){
            feedbackFileService.uploadFile(multiRequest, result.getId());
            resultFlag = true;
        }

        return resultFlag;
    }

    @Transactional(readOnly = true)
    public HashMap<String, Object> findAll(Integer page, Integer size) throws Exception {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        Page<Feedback> list = feedbackRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")));

        resultMap.put("list", list.stream().map(FeedbackResponseDto::new).collect(Collectors.toList()));
        resultMap.put("paging", list.getPageable());
        resultMap.put("totalCnt", list.getTotalElements());
        resultMap.put("totalPage", list.getTotalPages());

        return resultMap;
    }

    public HashMap<String, Object> findById(Long id) throws Exception{
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        feedbackRepository.updateFeedbackReadCntInc(id);

        FeedbackResponseDto info = new FeedbackResponseDto(feedbackRepository.findById(id).get());

        resultMap.put("info", info);
        resultMap.put("fileList", feedbackFileService.findByFeedbackId(info.getId()));

        return resultMap;
    }

    public boolean updateFeedback(FeedbackRequestDto feedbackRequestDto, MultipartHttpServletRequest multiRequest) throws Exception{
        int result = feedbackRepository.updateFeedback(feedbackRequestDto);

        boolean resultFlag = false;

        if (result > 0){
            feedbackFileService.uploadFile(multiRequest, feedbackRequestDto.getId());
            resultFlag = true;
        }
        return resultFlag;
    }

    public int updateFeedbackReadCntInc(Long id){
        return feedbackRepository.updateFeedbackReadCntInc(id);
    }

}
