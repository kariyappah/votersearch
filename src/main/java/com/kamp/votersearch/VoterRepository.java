package com.kamp.votersearch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VoterRepository extends JpaRepository<Voter, Long> {
    List<Voter> findAllByFullName(String firstName);

    Voter findByFullName(String firstName);

//    @Query("SELECT voter FROM Voter voter WHERE CONCAT(voter.firstName, ' ', voter.relationName) LIKE %?1%")
    @Query("SELECT voter FROM Voter voter WHERE CONCAT(voter.fullName, ' ', voter.relationName) LIKE %?1%")
    List<Voter> search(String keyword);
}
