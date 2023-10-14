package com.example.moretech5back.service;

import com.example.moretech5back.exception.ATMNotFoundException;
import com.example.moretech5back.model.ATM;
import com.example.moretech5back.repository.ATMRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ATMService {
    private final ATMRepository atmRepository;

    public List<ATM> listAll() {
        return atmRepository.findAll();
    }

    public void save(ATM branch) {
        atmRepository.save(branch);
    }

    public ATM get(Long id) throws ATMNotFoundException {
        return atmRepository.findById(id).orElseThrow(ATMNotFoundException::new);
    }

    @Transactional
    public void delete(Long id) {
        atmRepository.deleteById(id);
    }
}
