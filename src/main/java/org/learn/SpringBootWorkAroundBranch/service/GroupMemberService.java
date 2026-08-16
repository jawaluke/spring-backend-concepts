package org.learn.SpringBootWorkAroundBranch.service;

import lombok.RequiredArgsConstructor;
import org.learn.SpringBootWorkAroundBranch.model.GroupMemberShip;
import org.learn.SpringBootWorkAroundBranch.repository.GroupMemberShipRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GroupMemberService {

    private final GroupMemberShipRepository groupMemberShipRepository;

    public boolean isExists(Long groupId, Long userId) {
        return groupMemberShipRepository.existsByUserIdAndGroupId(userId, groupId);
    }

    public GroupMemberShip joinRideGroup(Long groupId, Long userId) {
        return groupMemberShipRepository.save(GroupMemberShip.builder()
                .groupId(groupId)
                .userId(userId)
                .joinedAt(Instant.now()).build());
    }
}
