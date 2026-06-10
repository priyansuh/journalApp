package com.demoLearning.journalApp.repository;

import com.demoLearning.journalApp.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<journalEntry, ObjectId> {

}
