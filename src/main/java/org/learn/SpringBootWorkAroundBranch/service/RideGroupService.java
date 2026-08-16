package org.learn.SpringBootWorkAroundBranch.service;

import lombok.RequiredArgsConstructor;
import org.learn.SpringBootWorkAroundBranch.dto.GroupRequest;
import org.learn.SpringBootWorkAroundBranch.model.RideGroup;
import org.learn.SpringBootWorkAroundBranch.repository.RideGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RideGroupService {

    private final RideGroupRepository rideGroupRepository;

    public RideGroup createRideGroup(GroupRequest groupRequest) {
        return rideGroupRepository.save(RideGroup.builder()
                .groupName(groupRequest.getGroupName())
                .destinationLat(groupRequest.getDestinationLatitude())
                .destinationLong(groupRequest.getDestinationLongitude())
                .status("ACTIVE")
                .build());
    }

    public List<RideGroup> getRideGroups() {
        return rideGroupRepository.findAll();
    }

    public boolean isExist(Long groupId) {
        return rideGroupRepository.existsById(groupId);
    }
}
