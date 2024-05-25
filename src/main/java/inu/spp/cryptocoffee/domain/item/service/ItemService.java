package inu.spp.cryptocoffee.domain.item.service;

import inu.spp.cryptocoffee.domain.item.dto.ItemCreateRequestDto;
import inu.spp.cryptocoffee.domain.item.dto.ItemResponseDto;
import inu.spp.cryptocoffee.domain.item.entity.CategoryEntity;
import inu.spp.cryptocoffee.domain.item.repository.CategoryRepository;
import inu.spp.cryptocoffee.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public List<ItemResponseDto> getItems() {
        return itemRepository.findAll().stream()
                .map(ItemResponseDto::from)
                .toList();
    }

    public List<ItemResponseDto> getItemsByCategory(String categoryName) {
        CategoryEntity category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        return itemRepository.findByCategory(category).stream()
                .map(ItemResponseDto::from)
                .toList();
    }

    public List<String> getCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryEntity::getName)
                .toList();
    }

    public void createItem(ItemCreateRequestDto itemCreateRequestDto) {
        CategoryEntity category = categoryRepository.findByName(itemCreateRequestDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        itemRepository.save(ItemCreateRequestDto.from(itemCreateRequestDto, category));
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public void createCategory(String categoryName) {
        categoryRepository.save(CategoryEntity.builder()
                .name(categoryName)
                .build());
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
