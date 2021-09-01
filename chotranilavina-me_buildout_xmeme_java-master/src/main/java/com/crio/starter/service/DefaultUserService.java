package com.crio.starter.service;

import lombok.extern.log4j.Log4j2;

import com.crio.starter.exceptions.*;
import com.crio.starter.data.UserEntity;
import com.crio.starter.repository.*;
import com.crio.starter.data.*;
import com.crio.starter.Dao.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

@Log4j2
@Service("userService")
public class DefaultUserService implements UserService {

@Autowired
private UserRepository userRepository;

@Autowired
private AuthorityRepository authorityRepository;

@Autowired
private UserDao userDao;

@Autowired
PasswordEncoder passwordEncoder;

@Transactional
@Override
public void register(UserData user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user);
        userDao.saveUser(userEntity);
        //userRepository.save(userEntity);
        Authorities authorities = new Authorities();
        authorities.setUsername(userEntity.getUsername());
        authorities.setAuthority("ROLE_USER");
        authorityRepository.save(authorities);
}


@Override
public boolean checkIfUserExist(String username) {
        return userDao.findByUsername(username) !=null ? true : false;
}

private void encodePassword( UserEntity userEntity, UserData user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
}
}
