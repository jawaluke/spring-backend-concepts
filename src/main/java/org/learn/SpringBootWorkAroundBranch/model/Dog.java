package org.learn.SpringBootWorkAroundBranch.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dog {
    private String name;
    private String breed;
    private int gender;
}
