package com.accenture.asiggnment.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String value;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;
}
