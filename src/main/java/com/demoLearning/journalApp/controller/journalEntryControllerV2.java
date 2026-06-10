package com.demoLearning.journalApp.controller;

import com.demoLearning.journalApp.entity.journalEntry;
import com.demoLearning.journalApp.repository.JournalEntryRepository;
import com.demoLearning.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<journalEntry> all = journalEntryService.getAll();
        if(all!=null &&!all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry){
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<journalEntry> journalEtry = journalEntryService.findById(myId);
        if(journalEtry.isPresent()){
            return new ResponseEntity<>(journalEtry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id,@RequestBody journalEntry newEntry){
        journalEntry old=journalEntryService.findById(id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            journalEntryRepository.save(newEntry);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
