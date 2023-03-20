package com.api.demo.search.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "SEARCH")
@Entity
public class Search {

    @Id
    @Column(name = "KEYWORD", unique = true)
    private String keyword;

    @Column(name = "COUNT")
    private Integer count;


}
