package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
@Setter
@Getter
@NoArgsConstructor
public class UserLogMovie extends UserLog{

    @ManyToOne
    Movie movie;
}
