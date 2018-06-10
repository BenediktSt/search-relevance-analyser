package de.vawi.searchrelevanceanalyser.dao;

import de.vawi.searchrelevanceanalyser.model.RelevanceEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CassandraSerializer {

    private static final Logger logger = LoggerFactory.getLogger(CassandraSerializer.class);
    private RelevanceEntryRepository relevanceEntryRepository;

    public CassandraSerializer(RelevanceEntryRepository relevanceEntryRepository) {
        this.relevanceEntryRepository = relevanceEntryRepository;
    }

    public void serialize (List<RelevanceEntry> relevanceEntries) {
        int id = relevanceEntryRepository.getToalCount() != 0 ? relevanceEntryRepository.findHighestId() + 1 : 0;
        for (RelevanceEntry entry: relevanceEntries) {
            entry.setId(id);
            relevanceEntryRepository.save(entry);
            id ++;
        }
        logger.info("Serialized " + relevanceEntries.size() + " relevance entries into database");
    }
}
