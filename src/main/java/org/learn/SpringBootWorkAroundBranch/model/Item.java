package org.learn.SpringBootWorkAroundBranch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Item {
    private String name;
    private int price;
    private String dealer;

    public Item(ItemBuilder itemBuilder) {
        this.name = itemBuilder.name;
        this.price = itemBuilder.price;
        this.dealer = itemBuilder.dealer;
    }

    public Item(String oleg, int i, String polu) {
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public static class ItemBuilder {
        private String name;
        private int price;
        private String dealer;

        public ItemBuilder name(String name) { this.name = name; return this; }
        public ItemBuilder price(int price) { this.price = price; return this; }
        public ItemBuilder dealer(String dealer) { this.dealer = dealer; return this; }

        public Item build() {
            return new Item(this);
        }
    }
}
