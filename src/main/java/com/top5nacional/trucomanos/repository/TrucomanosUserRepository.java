package com.top5nacional.trucomanos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.top5nacional.trucomanos.model.TrucomanosUser;

@Repository
public interface TrucomanosUserRepository extends JpaRepository<TrucomanosUser, Long> {

	Optional<TrucomanosUser> findByUsername(String username);
	
	Optional<TrucomanosUser> findByEmail(String email);

	Optional<TrucomanosUser> findByUsernameAndEmail(String username, String email);
}
