package com.springboot.hello01.controller;

import com.springboot.hello01.dao.HospitalDao;
import com.springboot.hello01.domain.Hospital;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalDao hospitalDao;

    @GetMapping("/")
    public String hello1() {
        return "Hello World!!!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> findById(@PathVariable int id){
        log.info("hospital 정보를 찾았습니다.");
        return ResponseEntity.ok().body(this.hospitalDao.findById(id));
    }

}
