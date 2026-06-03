package com.demoLearning.journalApp.service;

import com.demoLearning.journalApp.entity.journalEntry;
import com.demoLearning.journalApp.repository.JournalEntryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    public void saveEntry(journalEntry journalEntry) {
        journalEntry saved = journalEntryRepository.save(journalEntry);


    }
    @Autowired
    private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;

    @PostConstruct
    public void printDb() {
        System.out.println(">>> ACTUAL DB IN USE = " + mongoTemplate.getDb().getName());
    }

}
