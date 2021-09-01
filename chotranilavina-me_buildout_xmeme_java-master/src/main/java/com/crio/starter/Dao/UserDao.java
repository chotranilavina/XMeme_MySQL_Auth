package com.crio.starter.Dao;

import com.crio.starter.data.*;
import java.util.*;

public interface UserDao {

    List<UserEntity> getAllUsers();

    UserEntity findByUsername(String username);

    UserEntity saveUser(UserEntity user);

    void deleteUserByUsername(String username);

}
