package de.vawi.searchrelevanceanalyser.dao;

import de.vawi.searchrelevanceanalyser.model.RelevanceEntry;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelevanceEntryRepository extends CassandraRepository<RelevanceEntry, String> {
    @Query("SELECT max(id) FROM relevanceentry")
    public int findHighestId();

    @Query("SELECT count(id) FROM relevanceentry")
    public int getToalCount();
}
