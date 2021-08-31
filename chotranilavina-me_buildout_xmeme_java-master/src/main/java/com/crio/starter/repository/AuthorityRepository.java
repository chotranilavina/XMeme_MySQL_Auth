package com.crio.starter.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.starter.data.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Long> {
public List<Authorities> findAll();
public Optional<Authorities> findByUsername(String username);
//public Optional<UserEntity> findByEmail(String email);
}
