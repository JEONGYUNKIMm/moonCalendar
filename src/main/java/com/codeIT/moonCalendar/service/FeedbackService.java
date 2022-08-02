package com.codeIT.moonCalendar.service;



import com.codeIT.moonCalendar.dto.feedback.FeedbackRequestDto;
import com.codeIT.moonCalendar.dto.feedback.FeedbackResponseDto;
import com.codeIT.moonCalendar.entity.feedback.Feedback;
import com.codeIT.moonCalendar.entity.feedback.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Transactional
    public Long save(FeedbackRequestDto feedbackSaveDto){
        return feedbackRepository.save(feedbackSaveDto.toEntity()).getId();
    }

    //@Transactional(readOnly = true)
    //public List <FeedbackResponseDto> findAll() {
    //    return feedbackRepository.findAll().stream().map(FeedbackResponseDto::new).collect(Collectors.toList());
    //}

    @Transactional(readOnly = true)
    public HashMap<String, Object> findAll(Integer page, Integer size){

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        Page<Feedback> list = feedbackRepository.findAll(PageRequest.of(page, size));

        resultMap.put("list", list.stream().map(FeedbackResponseDto::new).collect(Collectors.toList()));
        resultMap.put("paging", list.getPageable());
        resultMap.put("totalCnt", list.getTotalElements());
        resultMap.put("totalPage", list.getTotalPages());

        return resultMap;
    }

    public FeedbackResponseDto findById(Long id){
        return new FeedbackResponseDto(feedbackRepository.findById(id).get());
    }

    public int updateFeedback(FeedbackRequestDto feedbackRequestDto){
        return feedbackRepository.updateFeedback(feedbackRequestDto);
    }

    public int updateFeedbackReadCntInc(Long id){
        return feedbackRepository.updateFeedbackReadCntInc(id);
    }

    public void deleteById(Long id){
        feedbackRepository.deleteById(id);
    }
}
