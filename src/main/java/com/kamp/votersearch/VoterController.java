package com.kamp.votersearch;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VoterController {
    private VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping("/")
    public String getHomePage(Model model, @Param("keyword") String keyword) {
        //    public String getHomePage(@ModelAttribute("voterSearchFormData") Voter formData, Model model) {
        voterService.findAllVoters();
        List<Voter> voters = voterService.findAllVoters(keyword);
        model.addAttribute("voters", voters);
        model.addAttribute("keyword", keyword);
//        model.addAttribute("voterlist", voterService.findAllVoters());
//        model.addAttribute("voter", new Voter());


        /*String name = formData.getFullName();
        Voter voter = voterService.findByFullName(name.substring(0, name.length()-1));
        if(voter==null){
            voter = new Voter();
        }
        model.addAttribute("voterlist", new ArrayList<Voter>().add(voter));
        model.addAttribute("voter", voter);*/
        return "home";
    }

    @PostMapping("/search")
    public String searchVoter(@ModelAttribute("voterSearchFormData") Voter formData, Model model) {
//        List<Voter> voters = voterService.findByFullName(formData.getFullName());
        Voter voter = voterService.findByFullName(formData.getFullName());
        model.addAttribute("voter", voter);
        return "redirect:/";
    }

    @GetMapping("/voters")
    public ResponseEntity<List<Voter>> getAllVoters() {
        return new ResponseEntity<>(voterService.findAllVoters(), HttpStatus.OK);
    }

    @GetMapping("/voters/{firstName}")
    public ResponseEntity<Voter> getVoterByFirstName(@RequestParam String firstName) {
        return new ResponseEntity<>(voterService.findByFullName(firstName), HttpStatus.OK);
    }

    @PostMapping("/voters")
    public void addVoter(@RequestBody Voter voter) {
        voterService.addVoter(voter);
    }
}
