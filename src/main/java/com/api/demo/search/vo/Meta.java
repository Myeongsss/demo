package com.api.demo.search.vo;

import lombok.Data;

@Data
public class Meta {

    private boolean isEnd;
    private int pageable_count;
    private int total_count;

    public boolean getIsEnd() {
        return isEnd;
    }
}
