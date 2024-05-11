package com.kamp.votersearch;

import java.util.List;

public interface VoterService {
    List<Voter> findAllVoters(String keyword);
    List<Voter> findAllVoters();
    Voter findByFullName(String firstName);
//    List<Voter> findByLastName(String lastName);
//    List<Voter> findByFirstNameAndLastName(String firstName, String lastName);
    void addVoter(Voter voter);
    void exelUpload();
}
