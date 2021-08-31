package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@Table(name = "authorities")
@EntityListeners(AuditingEntityListener.class)
public class Authorities implements Serializable {

@Id
@GeneratedValue
private Integer user_id;

@NotEmpty(message = "username can not be empty")
private String username;

// @NotEmpty(message = "Email can not be empty")
// @Email(message = "Please provide a valid email id")
// private String email;

@NotEmpty
private String authority;

//getter and setter
}
