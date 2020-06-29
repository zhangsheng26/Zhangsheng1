package com.zhangsheng26.Lect10Net.bean;

import java.util.List;

public class VideoListResponse {
    private String result;
    private List<VideoInfo> list;
    public void setResult (String result){
        this.result=result;
    }
    public String getResult() {
        return result;
    }
    public List<VideoInfo> getList() {
        return list;
    }
    public void setList(List<VideoInfo> list) {
        this.list = list;
    }
}
