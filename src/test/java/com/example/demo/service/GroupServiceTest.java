package com.example.demo.Service;

import com.example.demo.Dto.GroupDto;
import com.example.demo.service.GroupService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class GroupServiceTest {
    @Autowired
    private GroupService groupService;

    @Test
    void getAllTest() {
        List<GroupDto> groupDtoList = groupService.getAll();

        Assertions.assertNotNull(groupDtoList);
        Assertions.assertNotEquals(0, groupDtoList.size());

        for (GroupDto groupDto : groupDtoList) {
            Assertions.assertNotNull(groupDto);
            Assertions.assertNotNull(groupDto.getId());
            Assertions.assertNotNull(groupDto.getName());
            Assertions.assertNotNull(groupDto.getType());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        List<GroupDto> allGroups = groupService.getAll();
        int randomIndex = random.nextInt(allGroups.size());
        Long someId = allGroups.get(randomIndex).getId();
        
        GroupDto groupDto = groupService.getbyid(someId);

        Assertions.assertNotNull(groupDto);
        Assertions.assertNotNull(groupDto.getId());
        Assertions.assertNotNull(groupDto.getName());
        Assertions.assertNotNull(groupDto.getType());
    }

    @Test
    void addTest() {
        GroupDto groupDto = new GroupDto();
        groupDto.setName("Test Group");
        groupDto.setType("Test Type");

        GroupDto added = groupService.addOne(groupDto);
        
        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertEquals("Test Group", added.getName());
        Assertions.assertEquals("Test Type", added.getType());

        GroupDto found = groupService.getbyid(added.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals(added.getId(), found.getId());
        Assertions.assertEquals(added.getName(), found.getName());
        Assertions.assertEquals(added.getType(), found.getType());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        List<GroupDto> allGroups = groupService.getAll();
        int randomIndex = random.nextInt(allGroups.size());
        Long someId = allGroups.get(randomIndex).getId();

        GroupDto updatedGroup = GroupDto.builder()
                .id(someId)
                .name("Updated Group")
                .type("Updated Type")
                .build();

        GroupDto result = groupService.update(someId, updatedGroup);
        
        Assertions.assertNotNull(result);
        Assertions.assertEquals(someId, result.getId());
        Assertions.assertEquals("Updated Group", result.getName());
        Assertions.assertEquals("Updated Type", result.getType());

        GroupDto found = groupService.getbyid(someId);
        Assertions.assertEquals(result.getName(), found.getName());
        Assertions.assertEquals(result.getType(), found.getType());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        List<GroupDto> allGroups = groupService.getAll();
        int randomIndex = random.nextInt(allGroups.size());
        Long someId = allGroups.get(randomIndex).getId();

        groupService.delete(someId);
        GroupDto deleted = groupService.getbyid(someId);

        Assertions.assertNull(deleted);
    }
}
