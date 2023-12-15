package com.example.vivo_data_presentation_backend.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@TableName(value ="PublicOpinionFeedbacks")
@Data
public class PublicOpinionFeedback implements Serializable {

    private String machineType;

    private String imei;

    private Date time;

    private String category;

    private String description;

    private String  series;

}


