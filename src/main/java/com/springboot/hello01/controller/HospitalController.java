package com.springboot.hello01.controller;

import com.springboot.hello01.dao.HospitalDao;
import com.springboot.hello01.domain.Hospital;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospital")
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalDao hospitalDao;

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> findById(@PathVariable Integer id){
        log.info("hospital 정보를 찾았습니다.");
        // optional 기능 사용
        Hospital hospital = hospitalDao.findById(id);
        Optional<Hospital> opt = Optional.of(hospital);

        if (!opt.isEmpty()) {
            return ResponseEntity.ok().body(hospital);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Hospital());
        }
    }

}
