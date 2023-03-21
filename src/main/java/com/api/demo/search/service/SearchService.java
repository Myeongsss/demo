package com.api.demo.search.service;

import com.api.demo.common.component.Client;
import com.api.demo.common.core.Pagination;
import com.api.demo.common.properties.CoreProperties;
import com.api.demo.search.entity.Search;
import com.api.demo.search.repository.SearchRepository;
import com.api.demo.search.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SearchService {

    @Autowired
    private Client client;

    @Autowired
    private CoreProperties properties;

    @Autowired
    private SearchRepository repository;

    public void validateReq(SearchReq req) throws Exception{
        if (StringUtils.isEmpty(req.getQuery())) {
            throw new Exception("empty search parameter : query");
        }
        if( (req.getPage()) > 50 ){
            throw new Exception("page is more than max : 1-50");
        }
    }

    public SearchRes search(SearchReq req) {

        SearchRes res = new SearchRes();

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.set("query",req.getQuery());
        parameters.set("sort",req.getSort());
        parameters.set("size",req.getSize());
        parameters.set("page",req.getPage());

        ClientRes clientRes = client.post(properties.getKakaoUrl(), ClientRes.class, parameters);

        addSearchKeyword(req.getQuery());

        Meta meta = clientRes.getMeta();
        int total = meta.getPageable_count();
        Pagination pagination = new Pagination();
        pagination.pageInfo(req.getPage(), req.getSize(), total);
        res.setPagination(pagination);
        res.setDocuments(clientRes.getDocuments());

        return res;

    }

    public void addSearchKeyword(String keyword){

        Search search = new Search();
        int count = 0;
        search.setKeyword(keyword);
        Search SearchKeyword = repository.findByKeyword(keyword);
        if(SearchKeyword == null) {
            count = 1;
        }else{
            count = SearchKeyword.getCount()+1;
        }
        search.setCount(count);
        repository.save(search);
    }

    public List<Search> popularKeyword() {
        Search search = new Search();

        List<Search> list = repository.findAll();
        List<Search> sortList = list.stream()
                .sorted(Comparator.comparing(Search::getCount).reversed())
                .collect(Collectors.toList());
        return sortList.stream().limit(10).collect(Collectors.toList());

    }

}
