package org.learn.SpringBootWorkAroundBranch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupRequest {
    private String groupName;
    private Double destinationLatitude;
    private Double destinationLongitude;
}
