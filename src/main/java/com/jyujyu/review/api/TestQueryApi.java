package com.jyujyu.review.api;

import com.jyujyu.review.model.TestEntity;
import com.jyujyu.review.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestQueryApi {
    private final TestService testService;

    @GetMapping("/test/query/jpa")
    public List<TestEntity> queryJpa(){
        return testService.findAllByNameByJPA("muscat");
    }

    @GetMapping("/test/query/dsl")
    public List<TestEntity> queryDsl(){
        return testService.findAllByNameByQuerydsl("muscat");
    }
}
