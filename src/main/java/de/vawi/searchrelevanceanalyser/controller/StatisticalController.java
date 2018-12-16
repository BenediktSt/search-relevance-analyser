package de.vawi.searchrelevanceanalyser.controller;

import de.vawi.searchrelevanceanalyser.analyser.KlickAnalyser;
import de.vawi.searchrelevanceanalyser.dao.TrackingEntryRepository;
import de.vawi.searchrelevanceanalyser.model.SearchRankins;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class StatisticalController {

    @Autowired
    private TrackingEntryRepository trackingEntryRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/statistics/all")
    public List<SearchRankins> getAll() {
        KlickAnalyser statistics = new KlickAnalyser(trackingEntryRepository.findAll());
        return statistics
                .generateList(false)
                .getSearchRankings();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/statistics/{searchTerm}")
    public SearchRankins getBySearchTerm(@PathVariable String searchTerm) {
        KlickAnalyser statistics = new KlickAnalyser(trackingEntryRepository.findBySearchTerm(searchTerm));
        return statistics
                .generateList(true)
                .getSearchRankings().get(0);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/statistics/searchterms")
    public List<TrackingEntry> allTerms() {
        List<TrackingEntry> resultList =new ArrayList<>();
        trackingEntryRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        TrackingEntry::getSearchTerm,
                        Collectors.summingInt(TrackingEntry::getCount)))
                .forEach((seachTerm, count) -> resultList.add(new TrackingEntry(seachTerm, count)));
        resultList.sort(Comparator.comparing(TrackingEntry::getCount).reversed());
        return resultList;
    }
}
