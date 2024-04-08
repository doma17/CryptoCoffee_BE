package inu.spp.cryptocoffee.domain.service;

import inu.spp.cryptocoffee.domain.dto.ItemRequestDto;
import inu.spp.cryptocoffee.domain.entity.CategoryEntity;
import inu.spp.cryptocoffee.domain.entity.ItemEntity;
import inu.spp.cryptocoffee.domain.repository.CategoryRepository;
import inu.spp.cryptocoffee.domain.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    public ItemService(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    public List<ItemEntity> getItemList() {
        List<ItemEntity> itemList = itemRepository.findAll();
        return itemList;
    }

    public ItemEntity createItem(ItemRequestDto itemRequestDto) {
        ItemEntity item = ItemEntity.builder()
                .name(itemRequestDto.getName())
                .description(itemRequestDto.getDescription())
                .category(categoryRepository.findById(itemRequestDto.getCategoryId()).orElseThrow(()
                        -> new EntityNotFoundException("Category not found"))).build();

        item = itemRepository.save(item);
        return item;
    }

    public String deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
        return "Item deleted successfully";
    }

    public String updateItem(Long itemId, ItemRequestDto itemRequestDto) {
        ItemEntity item = itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException("Item not found"));
        item.updateItem(
                itemRequestDto.getName(),
                itemRequestDto.getDescription(),
                categoryRepository.findById(itemRequestDto.getCategoryId()).orElseThrow(()
                        -> new IllegalArgumentException("Category not found"))
        );
        return "Item updated successfully";
    }

    public List<ItemEntity> getItemListByCategory(String categoryName) {
        CategoryEntity category = categoryRepository.findByName(categoryName).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        List<ItemEntity> itemList = itemRepository.findByCategory(category);

        return itemList;
    }
}
