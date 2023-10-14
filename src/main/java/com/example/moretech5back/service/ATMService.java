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

@Service
@AllArgsConstructor
public class ATMService {
    private final BranchRepository branchRepository;

    public List<Branch> listAll() {
        return branchRepository.findAll();
    }

    public List<Branch> findInRange(double xLeft, double xRight, double yUp, double yDown) {
        List<Branch> all = branchRepository.findInRange(xLeft, xRight, yUp, yDown);
        return new ArrayList<>(all.subList(0, Math.min(all.size(), 100)));
    }

    public List<Branch> findOptimal(double xMe, double yMe) {
        Comparator<Branch> comparator = (branch1, branch2) -> {
            double dist1 = distancePoints(branch1, xMe, yMe);
            double dist2 = distancePoints(branch2, xMe, yMe);

            double points1 = branch1.getLoad() + dist1;
            double points2 = branch2.getLoad() + dist2;

            return Double.compare(points1, points2);
        };
        List<Branch> all = branchRepository.findAll();
        all.sort(comparator);
        return new ArrayList<>(all.subList(0, Math.min(all.size(), 10)));
    }

    private double distancePoints(Branch branch, double xMe, double yMe) {
        double km = distanceDegrees(branch, xMe, yMe) * 111_139 / 1000;
        if (km < 3)
            return 1;
        return (km - 3) / 10 + 1;
    }

    private double distanceDegrees(Branch branch, double xMe, double yMe) {
        return Math.sqrt(Math.pow(branch.getX() - xMe, 2) + Math.pow(branch.getY() - yMe, 2));
    }

    public void save(Branch branch) {
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
