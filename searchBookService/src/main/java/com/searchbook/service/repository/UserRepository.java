package com.searchbook.service.repository;
import com.searchbook.service.entity.*;
import org.springframework.data.repository.*;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findById(String Id);
}