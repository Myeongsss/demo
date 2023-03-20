package com.api.demo.search.vo;

import lombok.Data;

@Data
public class Meta {

    private boolean is_end;
    private int pageable_count;
    private int total_count;

}
