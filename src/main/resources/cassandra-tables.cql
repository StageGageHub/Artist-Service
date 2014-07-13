CREATE KEYSPACE test WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor': 1
    };

USE test;



CREATE TABLE artists_by_name (
    id UUID,
    name text,
    genres Set<text>,
    PRIMARY KEY (name)
);



