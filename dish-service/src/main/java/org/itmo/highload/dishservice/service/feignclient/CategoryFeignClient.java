package org.itmo.highload.dishservice.service.feignclient;

import org.itmo.highload.dishservice.controller.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="category-service")
public interface CategoryFeignClient {

    @GetMapping("/categories/{id}")
    CategoryDto getOne(@PathVariable UUID id);

}
