package com.skyeng.demoSkyeng.service;

import com.skyeng.demoSkyeng.entity.PostOffice;
import com.skyeng.demoSkyeng.repository.PostOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    public PostOffice getPostOffice (String zipcode, String name) {
        Optional<PostOffice> postOffice = postOfficeRepository.findByZipcode(zipcode);

        if (postOffice.isPresent()) {
            return postOffice.get();
        } else {
            PostOffice postOfficeNew = new PostOffice();
            postOfficeNew.setName(name);
            postOfficeNew.setZipcode(zipcode);
            postOfficeRepository.save(postOfficeNew);

            return postOfficeNew;
        }

    }
}
