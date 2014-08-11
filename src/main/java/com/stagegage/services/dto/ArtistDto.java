package com.stagegage.services.dto;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
public class ArtistDto {

    private final String name;
    private final Set<String> genres;

    public ArtistDto(String name, Set<String> genres) {
        this.name = name;
        this.genres = genres;
    }

    public ArtistDto(String name) {
        this.name = name;
        genres = null;
    }

    public String getName() {
        return name;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public static ArtistDto toArtistDto(DBObject artistDBO) {
        if(artistDBO == null)
            return new ArtistDto(null, null);

        String name = (String) artistDBO.removeField("name");
        ArrayList<DBObject> genreDBOs = (ArrayList<DBObject>) artistDBO.removeField("genres");

        Set<String> genres = new HashSet<String>();
        if(genreDBOs != null) {
            for (DBObject dbo : genreDBOs) {
                genres.add((String) dbo.removeField("genre"));
            }
        }

        return new ArtistDto(name, genres);
    }

    public DBObject toDBO() {
        return new BasicDBObject()
                .append("name", name)
                .append("genres", getGenreArrayList());
    }

    public DBObject toUpdateDBO() {
        // Recall updates must start ALL with $ modifiers if you don't wanna overwrite
        DBObject genreDBO = new BasicDBObject("genres", new BasicDBObject("$each", getGenreArrayList()));
        DBObject update = new BasicDBObject("$setOnInsert", new BasicDBObject("name", name));
        update.put("$push", genreDBO);

        return update;
    }

    private ArrayList<DBObject> getGenreArrayList() {
        ArrayList<DBObject> genreDBOArray = new ArrayList<DBObject>();
        if(genres != null) {
            for (String genre : genres) {
                genreDBOArray.add(new BasicDBObject("genre", genre));
            }
        }

        return genreDBOArray;
    }

}
