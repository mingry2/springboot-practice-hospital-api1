package com.springboot.hello01.dao;

import com.springboot.hello01.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Hospital hospital) {
        String sql = "INSERT INTO `likelion-db1`.`nation_wide_hospitals` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area_size`) " +
                "VALUES (?, ?, ?, " +
                        "?, ?, ?, " +
                        "?, ?, ?, " +
                        "?, ?, ?, " +
                        "?, ?, ?, " +
                        "?);";
        this.jdbcTemplate.update(sql,
                hospital.getId(),
                hospital.getOpenServiceName(),
                hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(),
                hospital.getLicenseDate(),
                hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(),
                hospital.getPhone(),
                hospital.getFullAddress(),
                hospital.getRoadNameAddress(),
                hospital.getHospitalName(),
                hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(),
                hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize()
        );
    }

    public int getCount() {
        String sql = "select count(id) from nation_wide_hospitals;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from nation_wide_hospitals;");
    }

    //(1, hospital.getId()); // col:0
    //("의원", hospital.getOpenServiceName()); // col:1
    //(3620000,hospital.getOpenLocalGovernmentCode()); // col:3
    //("PHMA119993620020041100004",hospital.getManagementNumber()); // col:4
    //(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); // col:5
    //(1, hospital.getBusinessStatus()); // col:7
    //(13, hospital.getBusinessStatusCode()); // col:9
    //("062-515-2875", hospital.getPhone()); // col:15
    //("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); // col:18
    //("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress()); // col:19
    //("효치과의원", hospital.getHospitalName()); // col:21
    //("치과의원", hospital.getBusinessTypeName()); // col:25
    //(1, hospital.getHealthcareProviderCount()); // col:30
    //(0, hospital.getPatientRoomCount()); // col:31
    //(0, hospital.getTotalNumberOfBeds()); // col:32
    //(52.29, hospital.getTotalAreaSize()); // col:33
    RowMapper<Hospital> rowMapper = (rs, rowNum) -> {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        // 날짜 매핑
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());
        hospital.setBusinessStatus(rs.getInt("business_status"));
        hospital.setBusinessStatusCode(rs.getInt("business_status_code"));
        hospital.setPhone(rs.getString("phone"));
        hospital.setFullAddress(rs.getString("full_address"));
        hospital.setRoadNameAddress(rs.getString("road_name_address"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setBusinessTypeName(rs.getString("business_type_name"));
        hospital.setHealthcareProviderCount(rs.getInt("healthcare_provider_count"));
        hospital.setPatientRoomCount(rs.getInt("patient_room_count"));
        hospital.setTotalNumberOfBeds(rs.getInt("total_number_of_beds"));
        hospital.setTotalAreaSize(rs.getFloat("total_area_size"));
        return hospital;
    };

    public Hospital findById(int id){
        return this.jdbcTemplate.queryForObject("select * from nation_wide_hospitals where id = ?", rowMapper, id);
    }
}
