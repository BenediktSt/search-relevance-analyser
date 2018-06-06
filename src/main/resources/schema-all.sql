DROP TABLE relevance IF EXISTS;

CREATE TABLE relevance  (
    searchterm VARCHAR(20),
    result VARCHAR(20),
    rank INTEGER
);