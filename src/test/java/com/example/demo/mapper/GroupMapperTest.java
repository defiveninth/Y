package com.example.demo.mapper;

import com.example.demo.Dto.GroupDto;
import com.example.demo.model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GroupMapperTest {
    @Autowired
    private GroupMapper groupMapper;

    @Test
    void convertEntityToDtoTest() {
        Group groupEntity = new Group(1L, "Group A", "Science", null);
        GroupDto groupDto = groupMapper.toDto(groupEntity);

        Assertions.assertNotNull(groupDto);
        Assertions.assertNotNull(groupDto.getId());
        Assertions.assertNotNull(groupDto.getName());
        Assertions.assertNotNull(groupDto.getType());

        Assertions.assertEquals(groupEntity.getId(), groupDto.getId());
        Assertions.assertEquals(groupEntity.getName(), groupDto.getName());
        Assertions.assertEquals(groupEntity.getType(), groupDto.getType());
    }

    @Test
    void convertDtoToEntityTest() {
        GroupDto groupDto = new GroupDto(1L, "Group A", "Science", null);
        Group groupEntity = groupMapper.toEntity(groupDto);

        Assertions.assertNotNull(groupEntity);
        Assertions.assertNotNull(groupEntity.getId());
        Assertions.assertNotNull(groupEntity.getName());
        Assertions.assertNotNull(groupEntity.getType());

        Assertions.assertEquals(groupDto.getId(), groupEntity.getId());
        Assertions.assertEquals(groupDto.getName(), groupEntity.getName());
        Assertions.assertEquals(groupDto.getType(), groupEntity.getType());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Group> groupEntityList = new ArrayList<>();
        groupEntityList.add(new Group(1L, "Group A", "Science", null));
        groupEntityList.add(new Group(2L, "Group B", "Arts", null));
        groupEntityList.add(new Group(3L, "Group C", "Engineering", null));

        List<GroupDto> groupDtoList = groupMapper.toDtoList(groupEntityList);

        Assertions.assertNotNull(groupDtoList);
        Assertions.assertEquals(3, groupDtoList.size());

        for (int i = 0; i < groupEntityList.size(); i++) {
            Group groupEntity = groupEntityList.get(i);
            GroupDto groupDto = groupDtoList.get(i);

            Assertions.assertEquals(groupEntity.getId(), groupDto.getId());
            Assertions.assertEquals(groupEntity.getName(), groupDto.getName());
            Assertions.assertEquals(groupEntity.getType(), groupDto.getType());
        }
    }
}
