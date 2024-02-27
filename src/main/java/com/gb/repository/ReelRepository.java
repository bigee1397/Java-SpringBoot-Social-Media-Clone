package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gb.entities.Reel;
import java.util.*;

public interface ReelRepository extends JpaRepository<Reel, Integer> {

	public List<Reel> findByUserId(int userId); 
	
}
