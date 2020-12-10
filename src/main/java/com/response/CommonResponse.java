package com.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonResponse {
    Map<String, String> headers = new HashMap<>();
    private List<?> data;
    private Object recordinfo;


    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Object getRecordinfo() {
        return recordinfo;
    }

    public void setRecordinfo(Object recordinfo) {
        this.recordinfo = recordinfo;
    }
}
