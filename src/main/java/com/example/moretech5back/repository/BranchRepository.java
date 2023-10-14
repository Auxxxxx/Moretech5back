package com.example.moretech5back.repository;

import com.example.moretech5back.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    @Query(" select b from Branch b" +
            " where b.x >= :xLeft and b.x <= :xRight and b.y >= :yUp and b.y <= :yDown")
    List<Branch> findInRange(double xLeft,
                             double xRight,
                             double yUp,
                             double yDown);

    Optional<Branch> findByName(String name);
}
