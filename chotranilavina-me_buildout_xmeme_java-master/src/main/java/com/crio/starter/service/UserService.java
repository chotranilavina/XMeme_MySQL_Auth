package com.crio.starter.service;

import com.crio.starter.data.*;
import com.crio.starter.exchange.*;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface UserService {

public void register(UserData user);

public boolean checkIfUserExist(String email);
}
