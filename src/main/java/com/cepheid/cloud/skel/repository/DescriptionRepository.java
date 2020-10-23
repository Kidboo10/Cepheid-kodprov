package com.cepheid.cloud.skel.repository;

import com.cepheid.cloud.skel.model.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
