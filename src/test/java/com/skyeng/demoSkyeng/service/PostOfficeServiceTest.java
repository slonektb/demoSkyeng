package com.skyeng.demoSkyeng.service;

import com.skyeng.demoSkyeng.repository.MailingRepository;
import com.skyeng.demoSkyeng.repository.MailingStateRepository;
import com.skyeng.demoSkyeng.repository.PostOfficeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostOfficeServiceTest {

    @BeforeAll
    public void setUp () {
        PostOfficeRepository postOfficeRepositoryMock = Mockito.mock(PostOfficeRepository.class);
    }

}
