package com.order.system.integration.repository;

import com.order.system.integration.entity.OrderTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<OrderTest,Long> {
}
