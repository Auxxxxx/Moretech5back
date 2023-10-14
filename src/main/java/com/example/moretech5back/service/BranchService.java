package com.example.moretech5back.service;

import com.example.moretech5back.exception.BranchNotFoundException;
import com.example.moretech5back.model.Branch;
import com.example.moretech5back.model.DaySchedule;
import com.example.moretech5back.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.*;

@Service
@AllArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public List<Branch> listAll() {
        return branchRepository.findAll();
    }

    public List<Branch> findInRange(double xLeft, double xRight, double yUp, double yDown) {
        List<Branch> all = branchRepository.findInRange(xLeft, xRight, yUp, yDown);
        return new ArrayList<>(all.subList(0, Math.min(all.size(), 100)));
    }

    public void setLoad(String name, Long load) throws BranchNotFoundException {
        Branch existing = branchRepository.findByName(name).orElseThrow(BranchNotFoundException::new);
        existing.setLoad(load);
        branchRepository.save(existing);
    }


    // point = 4 min
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
        double km = distanceKm(branch.getX(), branch.getY(), xMe, yMe);
        return km / 4 + 1;
    }

    private double distanceKm(double branchX, double branchY, double xMe, double yMe){  // generally used geo measurement function
        var R = 6378.137; // Radius of earth in KM
        var dLat = xMe * Math.PI / 180 - branchX * Math.PI / 180;
        var dLon = yMe * Math.PI / 180 - branchY * Math.PI / 180;
        var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(branchX * Math.PI / 180) * Math.cos(xMe * Math.PI / 180) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        var d = R * c;
        return d; // km
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
