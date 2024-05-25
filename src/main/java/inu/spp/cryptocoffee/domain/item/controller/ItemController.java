package inu.spp.cryptocoffee.domain.item.controller;

import inu.spp.cryptocoffee.domain.item.dto.ItemCreateRequestDto;
import inu.spp.cryptocoffee.domain.item.dto.ItemResponseDto;
import inu.spp.cryptocoffee.domain.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/items")
@Tag(name = "Item API")
@RestController
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "전체 상품 목록 조회")
    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getItems() {
        List<ItemResponseDto> response = itemService.getItems();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "카테고리별 상품 조회")
    @GetMapping("/{category}")
    public ResponseEntity<List<ItemResponseDto>> getItemsByCategory(@PathVariable("category") String categoryName) {
        List<ItemResponseDto> response = itemService.getItemsByCategory(categoryName);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "상품 추가")
    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody ItemCreateRequestDto itemCreateRequestDto) {
        itemService.createItem(itemCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItem(Long itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Operation(summary = "카테고리 조회")
    @GetMapping("/category")
    public ResponseEntity<List<String>> getCategories() {
        List<String> response = itemService.getCategories();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "카테고리 추가")
    @PostMapping("/category/create")
    public ResponseEntity<String> createCategory(String categoryName) {
        itemService.createCategory(categoryName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/category/delete")
    public ResponseEntity<String> deleteCategory(Long categoryId) {
        itemService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
