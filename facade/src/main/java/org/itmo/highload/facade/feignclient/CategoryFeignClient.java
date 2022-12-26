package org.itmo.highload.facade.feignclient;

import org.itmo.highload.facade.common.ResponsePage;
import org.itmo.highload.facade.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.UUID;

@FeignClient(name = "category-service")
public interface CategoryFeignClient {

    @PostMapping("/categories")
    ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryDto categoryDto);

    @GetMapping("/categories/{id}")
    ResponseEntity<CategoryDto> getOne(@PathVariable UUID id);

//    @GetMapping("/categories")
//    ResponseEntity<ResponsePage> getAll(@PageableDefault Pageable pageable);

    @DeleteMapping("/categories/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);

    @PostMapping("/{id}")
    ResponseEntity<CategoryDto> update(@PathVariable UUID id, @RequestBody @Valid CategoryDto categoryDto);
}
