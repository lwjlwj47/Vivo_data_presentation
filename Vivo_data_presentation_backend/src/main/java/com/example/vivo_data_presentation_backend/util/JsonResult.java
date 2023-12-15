package com.example.vivo_data_presentation_backend.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult {

    private int code;

    private String msg;

    private Object data;

    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult error(String msg){
        return new JsonResult(500,msg,null);
    }

}
