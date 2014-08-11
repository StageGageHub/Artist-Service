package com.stagegage.services.repository;


import com.mongodb.*;
import com.stagegage.services.dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
@Configuration
public class ArtistRepository {

    @Autowired
    private Environment env;

    private MongoConfig dbConfig;
    private DB db;
    private DBCollection artists;

    public ArtistRepository() {
        dbConfig = new MongoConfig();

        this.db = dbConfig.createDB();
        if(db == null)
            System.out.println("Could not create DB");
        this.artists = db.getCollection("artists");

        // Make sure connection is ok
        db.getStats();
    }

    public List<ArtistDto> getAllArtists() {
        DBCursor cursor = artists.find();

        return getDtosFromCursor(cursor);
    }

    private List<ArtistDto> getDtosFromCursor(DBCursor cursor) {
        List<ArtistDto> artistDtos = new ArrayList<ArtistDto>();
        try {
            while(cursor.hasNext()) {
                DBObject artist = cursor.next();
                ArtistDto artistDto = ArtistDto.toArtistDto(artist);

                // Clean up any prior mistakes
                // TODO: make these asynch requests
                if(artistDto.getName() == null) {
                    artists.remove(artist);
                    continue;
                }

                artistDtos.add(artistDto);
            }
        } finally {
            cursor.close();
        }
        return artistDtos;
    }

    public ArtistDto createArtist(ArtistDto artistDto) {
        // True for upsert, false for multi. We dont need to update all, as only one will match anyway
        artists.update(artistDto.toDBO(), artistDto.toDBO(), true, false);

        return artistDto;
    }

    public ArtistDto addArtistGenre(String name, String genre) {
        Set<String> genres = new HashSet<String>();
        genres.add(genre);

        // TODO: Eliminate double query
        artists.update(new BasicDBObject("name", name), new ArtistDto(name, genres).toUpdateDBO());

        return ArtistDto.toArtistDto(artists.findOne(new BasicDBObject("name", name)));
    }

    public ArtistDto getArtist(String name) {
        return ArtistDto.toArtistDto(artists.findOne(new BasicDBObject("name", name)));
    }

    public ArtistDto deleteArtist(String artistName) {
        return ArtistDto.toArtistDto(artists.findAndRemove(new BasicDBObject("name", artistName)));
    }
}
