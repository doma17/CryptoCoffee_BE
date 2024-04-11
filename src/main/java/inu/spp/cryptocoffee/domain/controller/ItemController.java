package inu.spp.cryptocoffee.domain.controller;

import inu.spp.cryptocoffee.domain.dto.ItemRequestDto;
import inu.spp.cryptocoffee.domain.entity.ItemEntity;
import inu.spp.cryptocoffee.domain.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Item API")
@RequestMapping("/v1/api/item")
@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "아이템 목록 조회")
    @GetMapping
    public List<ItemEntity> getItemList() {
        return itemService.getItemList();
    }

    @Operation(summary = "카테고리별 아이템 목록 조회")
    @GetMapping("/category")
    public List<ItemEntity> getItemListByCategory(@RequestParam String category) {
        return itemService.getItemListByCategory(category);
    }

    @Operation(summary = "아이템 생성")
    @PostMapping
    public ResponseEntity<?> createItem(@Validated @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(itemService.createItem(itemRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "아이템 삭제")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
        return new ResponseEntity<>(itemService.deleteItem(itemId), HttpStatus.OK);
    }

    @Operation(summary = "아이템 수정")
    @PutMapping("/{itemId}")
    public ResponseEntity<?> updateItem(@PathVariable Long itemId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(itemService.updateItem(itemId, itemRequestDto), HttpStatus.OK);
    }
}
