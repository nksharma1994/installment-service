package com.daollar.instalment.controller;

import com.daollar.instalment.dto.*;
import com.daollar.instalment.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent")
public class ParentController {

    private final ParentService service;

    @Autowired
    public ParentController(ParentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Parent> addParent(@RequestBody ParentBody parentBody){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addParent(parentBody));
    }

    @GetMapping
    public ResponseEntity<PageResponse<Parent>> getListOfParent(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "2") Integer limit
    ){
        return ResponseEntity.ok(service.getAllParent(page, limit));
    }

    @PostMapping("/{id}/child")
    public ResponseEntity<MessageResponse> addNewChildToParent(@PathVariable("id") Long parentId,
                                                     @RequestBody ChildBody childBody){
        Child child = service.addChildToParent(parentId, childBody);
        if(child != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Child Added."));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/child")
    public ResponseEntity<List<Child>> getListOfChild(
            @PathVariable("id") Long parentId
    ){
        List<Child> childs = service.getAllChild(parentId);
        if(childs != null) {
            return ResponseEntity.ok(childs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
