package com.springboot.hello01.service;

import com.springboot.hello01.dao.HospitalDao;
import com.springboot.hello01.domain.Hospital;
import com.springboot.hello01.parser.ReadLineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// Presentation 과 비즈니스 로직을 분리하기 위한 계층(간단한 예제를 만들 때는 필요 없음)
// @Component 어노테이션과 같은 기능
// @ComponentScan 할때 Bean 으로 등록됨
@Service
public class HospitalService {
    // test 코드에 만들면 많은 양의 데이터가 계속 들어가서 좀 과하지만 Service 클래스에서 사용
    // Service class 에서 Parser 사용 할 것임

    private final ReadLineContext<Hospital> hospitalReadLineContext;

    private final HospitalDao hospitalDao;

    public HospitalService(ReadLineContext hospitalReadLineContext, HospitalDao hospitalDao) {
        this.hospitalReadLineContext = hospitalReadLineContext;
        this.hospitalDao = hospitalDao;
    }

    @Transactional
    public int insertLargeVolumeHospitalData(String filename){
        List<Hospital> hospitalList;
        try {
            hospitalList = hospitalReadLineContext.readByLine(filename);
            System.out.println("파싱이 끝났습니다.");
            hospitalList.stream()
                    .parallel()
                    .forEach(hospital -> {
                        try {
                            this.hospitalDao.add(hospital); // db에 insert 하는 구간
                        } catch (Exception e) {
                            System.out.printf("id:%d 레코드에 문제가 있습니다.", hospital.getId());
                            throw new RuntimeException(e);
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!Optional.of(hospitalList).isEmpty()) {
            return hospitalList.size();
        }else {
            return 0;
        }
    }
}
