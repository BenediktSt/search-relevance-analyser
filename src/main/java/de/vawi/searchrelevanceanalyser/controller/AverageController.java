package de.vawi.searchrelevanceanalyser.controller;

import de.vawi.searchrelevanceanalyser.analyser.AverageAnalyser;
import de.vawi.searchrelevanceanalyser.dao.CsvDeserializer;
import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import de.vawi.searchrelevanceanalyser.processor.TrackingEntryProcessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AverageController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/averageList")
    public List<StatisticalEntry> averageValues() {
        CsvDeserializer importer = new CsvDeserializer();
        TrackingEntryProcessor processor = new TrackingEntryProcessor();
        List<TrackingEntry> entryList = importer.readData("sample-data-piwik.csv");

        List<TrackingEntry> saveList = new ArrayList<>();
        entryList.forEach(entry -> {
            try {
                saveList.add(processor.process(entry));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AverageAnalyser average = new AverageAnalyser(saveList);
        return average.getAverageValues();
    }
}
