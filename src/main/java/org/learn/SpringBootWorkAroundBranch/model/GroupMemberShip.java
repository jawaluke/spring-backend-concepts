package org.learn.SpringBootWorkAroundBranch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "groupId"}))
public class GroupMemberShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long groupId;
    private Instant joinedAt;
}
