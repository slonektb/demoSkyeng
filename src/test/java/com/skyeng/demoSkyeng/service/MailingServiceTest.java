package com.skyeng.demoSkyeng.service;

import com.skyeng.demoSkyeng.dto.MailingChangeStateDTO;
import com.skyeng.demoSkyeng.dto.MailingIdentityDTO;
import com.skyeng.demoSkyeng.dto.RegisterMailingDTO;
import com.skyeng.demoSkyeng.entity.Mailing;
import com.skyeng.demoSkyeng.entity.TypeMailing;
import com.skyeng.demoSkyeng.repository.MailingRepository;
import com.skyeng.demoSkyeng.repository.MailingStateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MailingServiceTest {

    @Autowired
    MailingService mailingService;

    @Autowired
    MailingRepository mailingRepository;

    @Autowired
    MailingStateRepository mailingStateRepository;

    @Test
    public void registerNewMailing () {
        RegisterMailingDTO registerMailingDTO = new RegisterMailingDTO(
                TypeMailing.PACKAGE,
                "654321",
                "забугорскася стрит 21",
                "Пупкин ВВ"
        );

        int countBefore = (int) mailingRepository.findAll().stream().count();

        MailingIdentityDTO mailing = mailingService.register(registerMailingDTO);
        int countAfter = (int) mailingRepository.findAll().stream().count();

        assertEquals(countAfter, countBefore+1);
    }

    @Test
    public void getStates() {
        List<Mailing> mailings = mailingRepository.findAll();
        MailingIdentityDTO mid = new MailingIdentityDTO(mailings.get(0).getId());

        assertNotNull(mailingService.getAllStatesMailing(mid));
    }

    @Test
    public void mailingChangeStates () {
        RegisterMailingDTO registerMailingDTO = new RegisterMailingDTO(
                TypeMailing.PACKAGE,
                "654321",
                "забугорскася стрит 21",
                "Пупкин ВВ"
        );
        MailingIdentityDTO mailing = mailingService.register(registerMailingDTO);

        MailingChangeStateDTO chnageMailing = new MailingChangeStateDTO(
                mailing.getIdentity(),
                "111111",
                "Сортировочная 2"
        );

        assertTrue(mailingService.incomingPostOffice(chnageMailing));
        assertTrue(mailingService.outgoingPostOffice(chnageMailing));
        assertTrue(mailingService.mailingDelivery(mailing));
    }
}
