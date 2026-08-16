package org.learn.SpringBootWorkAroundBranch.repository;

import org.learn.SpringBootWorkAroundBranch.model.GroupMemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberShipRepository extends JpaRepository<GroupMemberShip, Long> {

    boolean existsByUserIdAndGroupId(Long userId, Long groupId);
}
