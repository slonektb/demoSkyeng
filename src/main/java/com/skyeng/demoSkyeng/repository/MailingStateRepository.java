package com.skyeng.demoSkyeng.repository;

import com.skyeng.demoSkyeng.entity.Mailing;
import com.skyeng.demoSkyeng.entity.MailingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MailingStateRepository extends JpaRepository<MailingState, Long> {
    Set<MailingState> findByMailing(Mailing mailing);

}
