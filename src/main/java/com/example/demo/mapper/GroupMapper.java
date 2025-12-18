package com.example.demo.mapper;

import com.example.Midka.Dto.GroupDto;
import com.example.Midka.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "students", ignore = true)
    GroupDto toDto(Group group);

    Group toEntity(GroupDto dto);

    List<GroupDto> toDtoList(List<Group> list);
    List<Group> toEntityList(List<GroupDto> list);
}

