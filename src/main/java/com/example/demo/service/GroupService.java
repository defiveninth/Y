package com.example.demo.service;

import com.example.demo.Dto.GroupDto;
import java.util.List;

public interface GroupService {

    List<GroupDto> getAll();               

    GroupDto getbyid(Long id);

    GroupDto addOne(GroupDto dto);         
    GroupDto update(Long id, GroupDto dto);
    void delete(Long id);
}
