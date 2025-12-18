package com.example.demo.service.impl;

import com.example.Midka.Dto.GroupDto;
import com.example.Midka.mapper.GroupMapper;
import com.example.Midka.mapper.StudentMapper;
import com.example.Midka.model.Group;
import com.example.Midka.repositories.GroupRepository;
import com.example.Midka.service.GroupService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, StudentMapper studentMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<GroupDto> getAll() {
        return groupMapper.toDtoList(groupRepository.findAll());
    }

    @Override
    public GroupDto getbyid(Long id){
        return groupMapper.toDto(groupRepository.findById(id).orElse(null));
    }

    @Override
    public GroupDto addOne(GroupDto dto) {
        Group group = new Group();
        group.setName(dto.getName());
        group.setType(dto.getType());
        if (dto.getStudents() != null) {
            group.setStudents(studentMapper.toEntityList(dto.getStudents()));
        }
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public GroupDto update(Long id, GroupDto dto) {
        Group group = groupRepository.findById(id).orElse(null);
        if(group != null) {
            group.setName(dto.getName());
            group.setType(dto.getType());
            return groupMapper.toDto(groupRepository.save(group));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Group group = groupRepository.findById(id).orElse(null);
        if(group != null) {
            groupRepository.delete(group);
        }
    }
}
