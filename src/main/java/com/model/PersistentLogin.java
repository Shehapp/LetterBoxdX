package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "persistent_logins")
@NoArgsConstructor
@Setter
@Getter
public class PersistentLogin{
    @Column(length = 250,nullable = false)
    private String username;
    @Id
    @Column(length = 64,nullable = false)
    private String series;
    @Column(length = 250,nullable = false)
    private String token;
    @Column(name = "last_used",nullable = false)
    private Timestamp lastUsed;
}
