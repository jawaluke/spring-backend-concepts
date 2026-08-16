package org.learn.SpringBootWorkAroundBranch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.SpringBootWorkAroundBranch.dto.GroupRequest;
import org.learn.SpringBootWorkAroundBranch.dto.JoinRideGroup;
import org.learn.SpringBootWorkAroundBranch.service.AppUserService;
import org.learn.SpringBootWorkAroundBranch.service.GroupMemberService;
import org.learn.SpringBootWorkAroundBranch.service.RideGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
@Slf4j
public class RideGroupController {

    private final RideGroupService rideGroupService;

    private final GroupMemberService groupMemberService;

    private final AppUserService appUserService;

    @PostMapping("/")
    public ResponseEntity createGroup(@RequestBody GroupRequest groupRequest) {
        log.info("Request for creating a group named : {0}", groupRequest.getGroupName());
        return ResponseEntity.accepted().body(rideGroupService.createRideGroup(groupRequest));
    }

    @GetMapping("/")
    public ResponseEntity getAllGroups() {
        log.info("Request For all the groups list");
        return ResponseEntity.ok(rideGroupService.getRideGroups());
    }

    @PostMapping("/{groupId}/join")
    public ResponseEntity joinTheRideGroup(@RequestBody JoinRideGroup joinRideGroup,
                                           @PathVariable Long groupId) {
        if(groupMemberService.isExists(groupId, joinRideGroup.getUserId())) {
            return ResponseEntity.badRequest().body("Already joined the group");
        }
        if(!rideGroupService.isExist(groupId)) {
            return ResponseEntity.badRequest().body("group id doestn't exist");
        }
        if(!appUserService.isExist(joinRideGroup.getUserId())) {
            return ResponseEntity.badRequest().body("user id doesn't exist");
        }
        return ResponseEntity.ok(groupMemberService.joinRideGroup(groupId, joinRideGroup.getUserId()));
    }




}
