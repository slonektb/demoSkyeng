package com.skyeng.demoSkyeng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailingChangeStateDTO {
    private Long identityMailing;
    private String zipcodePostOffice;
    private String namePostOffice;
}
