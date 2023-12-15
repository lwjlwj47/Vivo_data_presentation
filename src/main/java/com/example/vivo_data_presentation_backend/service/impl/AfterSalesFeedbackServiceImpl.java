package com.example.vivo_data_presentation_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vivo_data_presentation_backend.domain.AfterSalesFeedback;
import com.example.vivo_data_presentation_backend.domain.PublicOpinionFeedback;
import com.example.vivo_data_presentation_backend.mapper.AfterSalesFeedbackMapper;
import com.example.vivo_data_presentation_backend.service.AfterSalesFeedbackService;
import org.springframework.stereotype.Service;

@Service
public class AfterSalesFeedbackServiceImpl extends ServiceImpl<AfterSalesFeedbackMapper, AfterSalesFeedback> implements AfterSalesFeedbackService {
}
