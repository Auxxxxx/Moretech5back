package com.example.moretech5back.service;

import com.example.moretech5back.exception.BranchNotFoundException;
import com.example.moretech5back.model.Branch;
import com.example.moretech5back.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public List<Branch> listAll() {
        return branchRepository.findAll();
    }

    public List<Branch> findInRange(double xLeft, double xRight, double yUp, double yDown) {
        List<Branch> all = branchRepository.findInRange(xLeft, xRight, yUp, yDown);
        return new ArrayList<>(all.subList(0, 100));
    }

    public List<Branch> findOptimal(double xMe, double yMe) {
        Comparator<Branch> comparator = (branch1, branch2) -> {
          int result = 0;
          double dist1 = distance(branch1, xMe, yMe);
          double dist2 = distance(branch2, xMe, yMe);
          return result;
        };
        return null;
    }

    private double distance(Branch branch, double xMe, double yMe) {
        return Math.sqrt(Math.pow(branch.getX() - xMe, 2) + Math.pow(branch.getY() - yMe, 2));
    }

    public void save(String name,
                     String address,
                     Long load,
                     Double x,
                     Double y) {
        Branch branch = Branch.builder()
                    .name(name)
                    .address(address)
                    .load(load)
                    .x(x)
                    .y(y)
                    .build();
        branchRepository.save(branch);
    }

    public Branch get(Long id) throws BranchNotFoundException {
        return branchRepository.findById(id).orElseThrow(BranchNotFoundException::new);
    }

    @Transactional
    public void delete(Long id) {
        branchRepository.deleteById(id);
    }
}
