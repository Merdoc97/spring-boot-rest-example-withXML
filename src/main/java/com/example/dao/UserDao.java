package com.example.dao;

import com.example.model.Users;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Igor
 */

@Repository
public interface UserDao extends CrudRepository<Users, Long> {
    @Query("from Users where name=:name")
    Users getByName(@Param("name") String name);

    @Query("from Users")
    List<Users> getAll();

    @Query("from Users where id=:id")
    Users getById(@Param("id") Integer id);

}
