package com.belgray.flashcrm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        NEW,
        PROCESS,
        DONE,
        CANCELLED
    }

}
