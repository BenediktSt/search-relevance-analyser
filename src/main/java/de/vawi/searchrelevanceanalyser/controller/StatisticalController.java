package de.vawi.searchrelevanceanalyser.controller;

import de.vawi.searchrelevanceanalyser.analyser.KlickAnalyser;
import de.vawi.searchrelevanceanalyser.dao.TrackingEntryRepository;
import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class StatisticalController {

    @Autowired
    private TrackingEntryRepository trackingEntryRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/statistics/all")
    public Map<String, List<StatisticalEntry>> getAll() {
        KlickAnalyser statistics = new KlickAnalyser(trackingEntryRepository.findAll());
        return statistics.getSearchTermValues();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/statistics/searchterms")
    public Map<String, Integer> allTerms() {
        return trackingEntryRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        TrackingEntry::getSearchTerm,
                        Collectors.summingInt(TrackingEntry::getCount)));
    }
}
