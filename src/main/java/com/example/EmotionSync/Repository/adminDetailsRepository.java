package com.example.EmotionSync.Repository;

import com.example.EmotionSync.Entity.admin.adminDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminDetailsRepository extends JpaRepository<adminDetails,Integer> {
    adminDetails findByName(String usr);

    //Optional<Object> findByDoctorid(Integer doctorId);
}
