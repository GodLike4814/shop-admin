package com.fh.utils.page;

import java.util.List;

public class PageBean<T> {


    //开始下表
    private Integer start;
    //每页条数
    private Integer length;
    //绘制次数
    private Integer draw=1;
    //总长度
    private Long recordsTotal=0l;
    //过滤后的长度
    private Long recordsFiltered=0l;
    //包含的数据
    private List<T> data;


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
