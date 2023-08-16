package com.skyeng.demoSkyeng.repository;

import com.skyeng.demoSkyeng.entity.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
    Optional<PostOffice> findByZipcode (String zipcode);
}
