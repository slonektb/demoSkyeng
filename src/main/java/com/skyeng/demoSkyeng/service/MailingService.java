package com.skyeng.demoSkyeng.service;

import com.skyeng.demoSkyeng.dto.MailingChangeStateDTO;
import com.skyeng.demoSkyeng.dto.MailingIdentityDTO;
import com.skyeng.demoSkyeng.dto.MailingStateDTO;
import com.skyeng.demoSkyeng.dto.RegisterMailingDTO;
import com.skyeng.demoSkyeng.entity.Mailing;
import com.skyeng.demoSkyeng.entity.MailingState;
import com.skyeng.demoSkyeng.entity.MailingStateType;
import com.skyeng.demoSkyeng.entity.PostOffice;
import com.skyeng.demoSkyeng.repository.MailingRepository;
import com.skyeng.demoSkyeng.repository.MailingStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MailingService {
    private final MailingRepository mailingRepository;
    private final MailingStateRepository mailingStateRepository;
    private final PostOfficeService postOfficeService;

    public MailingIdentityDTO register (RegisterMailingDTO registerMailingDTO) {
        System.out.println(registerMailingDTO);

        Mailing mailing = new Mailing();
        MailingState mailingState = new MailingState();
        try {
            mailing.setTypeMailing(registerMailingDTO.getTypeMailing());
            mailing.setAddressRecipient(registerMailingDTO.getAddressRecipient());
            mailing.setZipcodeRecipient(registerMailingDTO.getZipcodeRecipient());
            mailing.setNameRecipient(registerMailingDTO.getNameRecipient());
            mailingRepository.save(mailing);

            mailingState.setMailing(mailing);
            mailingState.setMailingStateType(MailingStateType.SENDING);
            mailingStateRepository.save(mailingState);

            MailingIdentityDTO mailingIdentityDTO = new MailingIdentityDTO();
            mailingIdentityDTO.setIdentity(mailing.getId());
            return mailingIdentityDTO;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean incomingPostOffice (MailingChangeStateDTO mailingChangeStateDTO) {
        Mailing mailing = mailingRepository.findAllById(mailingChangeStateDTO.getIdentityMailing());
        if (mailing != null) {
            MailingState mailingState = new MailingState();

            PostOffice postOffice = postOfficeService.getPostOffice(
                    mailingChangeStateDTO.getZipcodePostOffice(),
                    mailingChangeStateDTO.getNamePostOffice());

            mailingState.setMailing(mailing);
            mailingState.setPostOffice(postOffice);
            mailingState.setMailingStateType(MailingStateType.INCOMING);

            mailingStateRepository.save(mailingState);

            return true;
        } else {
            return false;
        }
    }

    public boolean outgoingPostOffice (MailingChangeStateDTO mailingChangeStateDTO) {
        Mailing mailing = mailingRepository.findAllById(mailingChangeStateDTO.getIdentityMailing());
        if (mailing != null) {
            MailingState mailingState = new MailingState();

            PostOffice postOffice = postOfficeService.getPostOffice(
                    mailingChangeStateDTO.getZipcodePostOffice(),
                    mailingChangeStateDTO.getNamePostOffice());

            mailingState.setMailing(mailing);
            mailingState.setPostOffice(postOffice);
            mailingState.setMailingStateType(MailingStateType.OUTGOING);

            mailingStateRepository.save(mailingState);

            return true;
        } else {
            return false;
        }
    }

    public boolean mailingDelivery (MailingIdentityDTO mailingIdentityDTO) {
        Mailing mailing = mailingRepository.findAllById(mailingIdentityDTO.getIdentity());
        if (mailing != null) {
            MailingState mailingState = new MailingState();
            mailingState.setMailing(mailing);
            mailingState.setMailingStateType(MailingStateType.DELIVERY);

            mailingStateRepository.save(mailingState);

            return true;
        } else {
            return false;
        }
    }

    public Set<MailingStateDTO> getAllStatesMailing(MailingIdentityDTO mailingIdentityDTO) {

        Mailing mailing = mailingRepository.findAllById(mailingIdentityDTO.getIdentity());
        if (mailing !=null) {
            Set<MailingState> mailingStates = mailingStateRepository.findByMailing(mailing);

            Set<MailingStateDTO> mailingStateDTOS = new HashSet<>();

            for (MailingState ms : mailingStates) {
                String poName = "";
                if (ms.getPostOffice() != null) poName = ms.getPostOffice().getName();
                mailingStateDTOS.add(new MailingStateDTO(mailing.getId(), ms.getDate(), ms.getMailingStateType(), poName));
            }
            return mailingStateDTOS;
        }

        return null;
    }
}
