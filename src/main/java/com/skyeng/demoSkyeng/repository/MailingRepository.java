package com.skyeng.demoSkyeng.repository;

import com.skyeng.demoSkyeng.entity.Mailing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingRepository extends JpaRepository<Mailing, Long> {

    Mailing findAllById(Long id);
}
