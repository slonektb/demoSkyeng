package com.skyeng.demoSkyeng.dto;

import com.skyeng.demoSkyeng.entity.TypeMailing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMailingDTO {
    private TypeMailing typeMailing;
    private String zipcodeRecipient;
    private String addressRecipient;
    private String nameRecipient;
}
