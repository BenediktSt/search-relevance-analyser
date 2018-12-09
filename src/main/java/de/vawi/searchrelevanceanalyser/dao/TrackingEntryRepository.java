package de.vawi.searchrelevanceanalyser.dao;

import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingEntryRepository extends CassandraRepository<TrackingEntry, String> {

    @Query("SELECT max(id) FROM trackingentry")
    public int findHighestId();

    @Query("SELECT count(id) FROM trackingentry")
    public int getToalCount();

    @AllowFiltering
    public List<TrackingEntry> findBySearchTerm(String searchTerm);
}
