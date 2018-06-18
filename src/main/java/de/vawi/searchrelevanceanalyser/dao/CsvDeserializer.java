package de.vawi.searchrelevanceanalyser.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class CsvDeserializer {

    private static final Logger logger = LoggerFactory.getLogger(CsvDeserializer.class);

    public CsvDeserializer() {}

    public List<TrackingEntry> readData (String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<TrackingEntry> readValues =
                    mapper.readerFor(TrackingEntry.class).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
