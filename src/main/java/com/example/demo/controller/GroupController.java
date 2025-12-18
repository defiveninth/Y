package com.example.demo.controller;

import com.example.demo.Dto.BookDto;
import com.example.demo.Dto.GroupDto;
import com.example.demo.service.GroupService;
import com.example.demo.service.impl.GroupServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupServiceImpl groupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAll() {

        return ResponseEntity.ok(groupService.getAll());
    }
    @GetMapping("/{id}")
    private ResponseEntity<GroupDto> getbyid(@PathVariable Long id){
        return ResponseEntity.ok(groupService.getbyid(id));

    }

    @PostMapping
    public ResponseEntity<GroupDto> add(@RequestBody GroupDto dto) {
        return ResponseEntity.ok(groupService.addOne(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @RequestBody GroupDto dto) {
        return ResponseEntity.ok(groupService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.ok().build();
    }
}
