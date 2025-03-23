package com.springboot.mealkart.repository;

import com.springboot.mealkart.domain.CartDetail;
import com.springboot.mealkart.domain.CartDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailPK> {
}
