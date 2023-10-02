package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Medicine;

@Repository
public interface MedicineRepo extends CrudRepository<Medicine, Integer>{

	List<Medicine> findByDow(String dow);
	
}
