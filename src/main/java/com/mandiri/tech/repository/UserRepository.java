package com.mandiri.tech.repository;

import com.mandiri.tech.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    @Query(value ="select * from user where id=:idUser", nativeQuery = true)
    User findByIdUsr(String idUser);
}
