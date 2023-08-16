package com.skyeng.demoSkyeng.dto;

import lombok.Data;

@Data
public class MailingChangeStateDTO {
    private Long identityMailing;
    private String zipcodePostOffice;
    private String namePostOffice;
}
