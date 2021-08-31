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
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserData implements Serializable {

@Id
@GeneratedValue
private Integer id;

@NotEmpty(message = "username can not be empty")
private String username;

@NotEmpty(message = "Password can not be empty")
private String password;

@NotEmpty
private String confirm;

//getter and setter
}
