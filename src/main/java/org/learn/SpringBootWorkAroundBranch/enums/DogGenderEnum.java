package org.learn.SpringBootWorkAroundBranch.enums;

public enum DogGenderEnum {
    MALE(1),
    FEMALE(0);

    int val;
    DogGenderEnum(int val) {
        this.val = val;
    }

    public static String getGender(int dogGender) {
        for(DogGenderEnum gender: DogGenderEnum.values()) {
            if(gender.val == dogGender) {
                return gender.name();
            }
        }
        return null;
    }
}
