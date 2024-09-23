package org.learn.SpringBootWorkAroundBranch.model;

public enum Gender {
    MALE(1),
    FEMALE(0);

    final int value;
    Gender(int n) {
        value = n;
    }

    public static Gender getGender(int gender) {
        for(Gender g : Gender.values()) {
            if(g.value==gender) return g;
        }
        return null;
    }
}
