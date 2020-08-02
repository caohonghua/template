package org.caohh.template.vo;

import lombok.Data;

@Data
public class Page {
    private static final Long MAX_OFFSET = 2000L;
    //页面显示条数，默认10条
    private Integer pageSize = 10;
    //当前页码，默认第一页
    private Integer currentPage = 1;

    //偏移量
    public long offset() {
        return (currentPage - 1) * pageSize;
    }

    //最大偏移量
    public long maxOffset() {
        return MAX_OFFSET;
    }

}
