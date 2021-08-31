package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

private String username;
@Column(unique = true)
private String password;
}
