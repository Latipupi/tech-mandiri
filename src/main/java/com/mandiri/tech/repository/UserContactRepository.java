package com.mandiri.tech.repository;

import com.mandiri.tech.dto.DetailUser;
import com.mandiri.tech.model.UserContact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserContactRepository extends CrudRepository<UserContact, Integer> {

    List<UserContact> findAll();

    @Query(value ="select u.name as Name, u.age as Age, uc.address as Address from user u" +
            " inner join user_contact uc" +
            " on u.id=uc.user_id", nativeQuery = true)
    List<DetailUser> findAllUserContact();
}
