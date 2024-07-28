package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.repository.ItemRepository;
import com.gogedon.rss_feed_aggregator.domain.Item;
import com.gogedon.rss_feed_aggregator.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    public static final String ITEM_WITH_ID_NOT_FOUND = "Item with id %d not found.";

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ITEM_WITH_ID_NOT_FOUND, id)));
    }

    @Override
    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ITEM_WITH_ID_NOT_FOUND, id)));
        existingItem.setName(item.getName());
        existingItem.setDescription(item.getDescription());
        return itemRepository.save(existingItem);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ITEM_WITH_ID_NOT_FOUND, id)));
        itemRepository.deleteById(id);
    }
}