package de.vawi.searchrelevanceanalyser.dao;

import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CassandraSerializer {

    private static final Logger logger = LoggerFactory.getLogger(CassandraSerializer.class);
    private TrackingEntryRepository trackingEntryRepository;

    public CassandraSerializer(TrackingEntryRepository trackingEntryRepository) {
        this.trackingEntryRepository = trackingEntryRepository;
    }

    public void serialize (List<TrackingEntry> trackingEntries) {
        int id = trackingEntryRepository.getToalCount() != 0 ? trackingEntryRepository.findHighestId() + 1 : 0;
        for (TrackingEntry entry: trackingEntries) {
            entry.setId(id);
            trackingEntryRepository.save(entry);
            id ++;
        }
        logger.info("Serialized " + trackingEntries.size() + " tracking entries into database");
    }
}
