package org.itmo.highload.facade.controller;


import lombok.RequiredArgsConstructor;
import org.itmo.highload.facade.common.ResponsePage;
import org.itmo.highload.facade.dto.CategoryDto;
import org.itmo.highload.facade.feignclient.CategoryFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    private final CategoryFeignClient categoryFeignClient;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryFeignClient.create(categoryDto);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getOne(@PathVariable UUID id) {
        return categoryFeignClient.getOne(id);
    }
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
//    @GetMapping("/categories")
//    ResponseEntity<ResponsePage> getAll(@PageableDefault Pageable pageable){
//        return categoryFeignClient.getAll(pageable);
//    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        return categoryFeignClient.delete(id);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id}")
    ResponseEntity<CategoryDto> update(@PathVariable UUID id, @RequestBody @Valid CategoryDto categoryDto) {
        return categoryFeignClient.update(id, categoryDto);
    }
}
