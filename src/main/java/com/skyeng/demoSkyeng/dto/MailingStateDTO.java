package com.skyeng.demoSkyeng.dto;

import com.skyeng.demoSkyeng.entity.MailingStateType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MailingStateDTO {
    private Long mailingIdentity;
    private Date dateState;
    private MailingStateType mailingStateType;
    private String postOfficeName;
}
