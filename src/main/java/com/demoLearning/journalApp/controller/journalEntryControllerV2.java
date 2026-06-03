package com.demoLearning.journalApp.controller;

import com.demoLearning.journalApp.entity.journalEntry;
import com.demoLearning.journalApp.repository.JournalEntryRepository;
import com.demoLearning.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<journalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @PostMapping
    public boolean createEntry(@RequestBody journalEntry myEntry){
        System.out.println("Received: " + myEntry.getTitle());
        // Bypass the service layer and save directly using the repository!
        journalEntryRepository.save(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public journalEntry getJournalEntryById(@PathVariable Long myId){
        return null;
    }

    @DeleteMapping("id/{myId}")
    public journalEntry deleteJournalEntryById(@PathVariable Long myId){
        return null;
    }

    @PutMapping("id/{id}")
    public journalEntry updateJournalById(@PathVariable Long id,@RequestBody journalEntry myEntry){
       return null;
    }

}
