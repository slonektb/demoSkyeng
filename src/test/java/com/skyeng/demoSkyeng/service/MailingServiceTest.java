package com.skyeng.demoSkyeng.service;

import com.skyeng.demoSkyeng.repository.MailingRepository;
import com.skyeng.demoSkyeng.repository.MailingStateRepository;
import com.skyeng.demoSkyeng.repository.PostOfficeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailingServiceTest {

    @BeforeAll
    public void setUp () {
        MailingRepository mailingRepositoryMock = Mockito.mock(MailingRepository.class);
        MailingStateRepository mailingStateRepositoryMock = Mockito.mock(MailingStateRepository.class);
        PostOfficeRepository postOfficeRepositoryMock = Mockito.mock(PostOfficeRepository.class);
    }
}
