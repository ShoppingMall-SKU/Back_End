package com.springboot.mealkart.repository;

import com.springboot.mealkart.domain.OrderingDetail;
import com.springboot.mealkart.domain.OrderingDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderingDetailRepository extends JpaRepository<OrderingDetail, OrderingDetailPK> {
}
