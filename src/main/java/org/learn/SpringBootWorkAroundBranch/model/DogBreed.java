package org.learn.SpringBootWorkAroundBranch.model;

public enum DogBreed {
    LABRADOR("labrador"),
    DOBBER("dobber"),
    CHICHIUAW("chichiuaw"),
    GERMAN_SHEPHERD("german shepherd"),
    ROTT_WEILER("rott weiler");

    final String value;

    DogBreed(String value) {
        this.value = value;
    }

    public static DogBreed getBreed(String breed) {
        for(DogBreed d : DogBreed.values()) {
            if(d.value.equals(breed)) return d;
        }
        return null;
    }

}
