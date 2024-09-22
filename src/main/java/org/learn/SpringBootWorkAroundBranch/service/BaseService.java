package org.learn.SpringBootWorkAroundBranch.service;

import lombok.extern.slf4j.Slf4j;
import org.learn.SpringBootWorkAroundBranch.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.*;

@Slf4j
@Service
public class BaseService {

    private CacheManager cacheManager;

    private ItemManager itemManager;

    @Autowired
    public BaseService(CacheManager cacheManager, ItemManager itemManager) {
        this.cacheManager = cacheManager;
        this.itemManager = itemManager;
    }

    @Cacheable(value = "items", key = "#dealer")
    public Item getItem(int y, String dealer) {
        Item item = null;
        log.info("inside get method");
        if(!Objects.equals(dealer, "")) {
            List<Item> items = itemManager.getItems();
            item = items.stream()
                    .filter(m -> m.getDealer().equals(dealer))
                    .findFirst().orElse(null);
        }
        return item;
    }

    @CacheEvict(value = "items", key = "#dealer")
    public Item delete(String dealer) {
        log.info("inside delete method");
        Item item = itemManager.delete(dealer);
        return item;
    }

//    @CachePut(value = "items", key = "#item.getDealer()")
    public Item add(Item item) {
        log.info("inside add method");
        Item items = itemManager.add(item);
        // Using cache manager to manually update the records
        cacheManager.getCache("items")
                .put(item.getDealer(), item);

        return items;
    }

    @CachePut(value = "items", key = "#dealer")
    public Item update(String dealer, Item item) {
        return itemManager.update(dealer, item);
    }

}
