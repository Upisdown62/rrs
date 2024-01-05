package com.jyujyu.review.api;

import com.jyujyu.review.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TestEntityApi {
    private final TestService testService;

    @PostMapping("/test/entity/create")
    public void createTestEntity(
            @RequestBody CreateTestEntityRequest request
    ){
        testService.create(request.name, request.age);
    }

    @PutMapping("/test/entity/{id}")
    public void createTestEntity(
            @PathVariable(name = "id") Long id,
            @RequestBody CreateTestEntityRequest request
    ){
        testService.update(id, request.getName(), request.getAge());
    }

    @DeleteMapping("/test/entity/{id}")
    public void deleteTestEntity(
            @PathVariable(name = "id") Long id
    ){
        testService.delete(id);
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class CreateTestEntityRequest{
        private String name;
        private Integer age;
    }
}
