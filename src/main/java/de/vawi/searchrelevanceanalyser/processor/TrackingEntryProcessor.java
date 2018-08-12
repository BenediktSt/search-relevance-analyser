package de.vawi.searchrelevanceanalyser.processor;

import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingEntryProcessor {

    private static final Logger log = LoggerFactory.getLogger(TrackingEntry.class);

    public TrackingEntry process(TrackingEntry trackingEntry) throws Exception {
        final String searchTerm = trackingEntry.getSearchTerm().toLowerCase();
        final String result = trackingEntry.getResult().toLowerCase();

        final TrackingEntry transformedEntry = new TrackingEntry(searchTerm, result, trackingEntry.getRank(), trackingEntry.getCount());

        log.info("Converting (" + trackingEntry + ") into (" + transformedEntry + ")");

        return transformedEntry;
    }
}
