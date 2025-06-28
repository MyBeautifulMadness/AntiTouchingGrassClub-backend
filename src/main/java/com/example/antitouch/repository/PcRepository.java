package com.example.antitouch.repository;

import com.example.antitouch.entity.Pc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcRepository extends JpaRepository<Pc, Long> {
}
