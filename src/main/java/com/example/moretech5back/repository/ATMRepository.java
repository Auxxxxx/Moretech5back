package com.example.moretech5back.repository;

import com.example.moretech5back.model.ATM;
import com.example.moretech5back.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ATMRepository extends JpaRepository<ATM, Long> {

}
