package org.learn.SpringBootWorkAroundBranch.service;

import org.learn.SpringBootWorkAroundBranch.model.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ItemManager {

    private List<Item> items;

    public ItemManager() {
        this.items = new ArrayList<>();
        populate();
    }

    public Item add(Item dealer) {
        Boolean isExist = items.stream().anyMatch(m -> m.getDealer().equals(dealer));
        if(!isExist) this.items.add(dealer);
        return items.stream()
                .filter(m -> m.getDealer().equals(dealer))
                .findFirst()
                .orElse(null);
    }

    public Item delete(String dealer) {
        items = new ArrayList<>(getItems().stream()
                .filter(m -> !Objects.equals(m.getDealer(), dealer))
                .toList());
        return items.stream()
                .filter(m -> m.getDealer().equals(dealer))
                .findFirst()
                .orElse(null);
    }

    public List<Item> getItems() {
        return items;
    }

    private void populate() {
        items.addAll(new ArrayList<>(
                Arrays.asList(
                        Item.builder().name("Milk").price(100).dealer("mohan").build(),
                        Item.builder().name("Potato").price(200).dealer("ram").build(),
                        Item.builder().name("Tomato").price(300).dealer("june").build(),
                        Item.builder().name("Curd").price(50).dealer("sam").build()
                ))
        );
    }

    public Item update(String dealer, Item item) {
        items = new ArrayList<>(items.stream()
                .filter(m -> Objects.equals(m.getDealer(), dealer))
                .map(m -> item)
                .toList());
        return item;
    }
}
