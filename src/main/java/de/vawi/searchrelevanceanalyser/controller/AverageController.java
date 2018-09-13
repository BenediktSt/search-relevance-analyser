package de.vawi.searchrelevanceanalyser.controller;

import de.vawi.searchrelevanceanalyser.analyser.AverageAnalyser;
import de.vawi.searchrelevanceanalyser.dao.TrackingEntryRepository;
import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AverageController {

    @Autowired
    private TrackingEntryRepository trackingEntryRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/averageList")
    public List<StatisticalEntry> averageValues() {
        AverageAnalyser average = new AverageAnalyser(trackingEntryRepository.findAll());
        return average.getAverageValues();
    }
}
