package com.springboot.mealkart.repository;

import com.springboot.mealkart.domain.CustomerManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerManageRepository extends JpaRepository<CustomerManage, String> {
}
