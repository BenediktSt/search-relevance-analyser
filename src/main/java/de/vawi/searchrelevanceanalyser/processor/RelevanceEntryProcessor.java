package de.vawi.searchrelevanceanalyser.processor;

import de.vawi.searchrelevanceanalyser.model.RelevanceEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class RelevanceEntryProcessor implements ItemProcessor<RelevanceEntry, RelevanceEntry> {

    private static final Logger log = LoggerFactory.getLogger(RelevanceEntry.class);

    @Override
    public RelevanceEntry process(RelevanceEntry relevanceEntry) throws Exception {
        final String searchTerm = relevanceEntry.getSearchTerm().toLowerCase();
        final String result = relevanceEntry.getResult().toLowerCase();

        final RelevanceEntry transformedEntry = new RelevanceEntry(searchTerm, result, relevanceEntry.getRank());

        log.info("Converting (" + relevanceEntry + ") into (" + transformedEntry + ")");

        return transformedEntry;
    }
}
