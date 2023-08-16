package com.skyeng.demoSkyeng.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailingState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "mailing_id")
    private Mailing mailing;

    @ManyToOne
    @JoinColumn(columnDefinition = "postoffice_id")
    private PostOffice postOffice;

    @Enumerated(EnumType.STRING)
    private MailingStateType mailingStateType;

    private Date date = new Date();
}
