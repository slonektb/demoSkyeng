package com.skyeng.demoSkyeng.controller;

import com.skyeng.demoSkyeng.dto.MailingChangeStateDTO;
import com.skyeng.demoSkyeng.dto.MailingIdentityDTO;
import com.skyeng.demoSkyeng.dto.MailingStateDTO;
import com.skyeng.demoSkyeng.dto.RegisterMailingDTO;
import com.skyeng.demoSkyeng.service.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class MailingController {
    private final MailingService mailingService;

    @PostMapping(value = "/register_mailing")
    public MailingIdentityDTO registerMailing (@RequestBody RegisterMailingDTO registerMailingDTO) {
        return mailingService.register(registerMailingDTO);
    }

    @PostMapping(value = "/incoming_to_office")
    public void mailingIncomingToOffice(@RequestBody MailingChangeStateDTO mailingChangeStateDTO) {
        mailingService.incomingPostOffice(mailingChangeStateDTO);
    }

    @PostMapping(value = "/outgoing_from_office")
    public void mailingOutgoingFromOffice(@RequestBody MailingChangeStateDTO mailingChangeStateDTO) {
        mailingService.outgoingPostOffice(mailingChangeStateDTO);
    }

    @PostMapping(value = "/delivery")
    public void mailingDelivery(@RequestBody MailingIdentityDTO mailingIdentityDTO) {
        mailingService.mailingDelivery(mailingIdentityDTO);
    }

    @GetMapping(value = "/get_mailing_states")
    public Set<MailingStateDTO> getMailingStates (@RequestBody MailingIdentityDTO mailingIdentityDTO) {
        return mailingService.getAllStatesMailing(mailingIdentityDTO);
    }
}
