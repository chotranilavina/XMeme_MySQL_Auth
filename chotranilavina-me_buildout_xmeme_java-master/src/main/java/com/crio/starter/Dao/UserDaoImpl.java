package com.crio.starter.Dao;

import com.crio.starter.data.*;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;

    }


    @Override
    public List<UserEntity> getAllUsers() {
        Query theQuery= (Query) entityManager.createQuery("from Users");
        List<UserEntity> users = theQuery.getResultList();

        return users;
    }


    @Override
    public UserEntity findByUsername(String username) {
        UserEntity user = entityManager.find(UserEntity.class,username);
        return user;
    }


    @Override
    public UserEntity saveUser(UserEntity user) {
        UserEntity dbUser = entityManager.merge(user);
        user.setId(dbUser.getId());
        return user;
    }


    @Override
    public void deleteUserByUsername(String username) {
        Query theQuery = (Query) entityManager.createQuery("delete from users where Username=:username");
        theQuery.setParameter("username", username);
        theQuery.executeUpdate();
    }
}
