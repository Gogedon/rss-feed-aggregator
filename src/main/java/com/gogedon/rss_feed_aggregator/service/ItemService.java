package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(Item item);
    List<Item> getAllItems();
    Item getItemById(Long id);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
}