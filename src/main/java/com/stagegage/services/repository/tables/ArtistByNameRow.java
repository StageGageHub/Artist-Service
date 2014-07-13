package com.stagegage.services.repository.tables;

import com.stagegage.services.dto.ArtistDto;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
@Table(value = "artists_by_name")
public class ArtistByNameRow {

    @PrimaryKey
    private final String name;

    @Column
    private final UUID id;
    @Column
    private final Set<String> genres;

    public ArtistByNameRow(final UUID id, final String name, final Set<String> genres) {
        this.id = id;
        this.name = name;
        this.genres = genres;
    }

    public static List<ArtistDto> toDto(List<ArtistByNameRow> artistRows) {
        if (artistRows == null) return null;

        List<ArtistDto> artistDtos = new ArrayList<ArtistDto>();
        for (ArtistByNameRow row : artistRows) {
            artistDtos.add(toDto(row));
        }

        return artistDtos;
    }

    public static ArtistDto toDto(ArtistByNameRow row) {
        return new ArtistDto(row.getId(), row.getName(), row.getGenres());
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getGenres() {
        return genres;
    }
}
