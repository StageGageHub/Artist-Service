package com.stagegage.services.service;

import com.stagegage.services.dto.ArtistDto;
import com.stagegage.services.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
@Component
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;


    public List<ArtistDto> getArtists() {

        return artistRepository.getAllArtists();
    }


    public ArtistDto getArtist(String name) {

        return artistRepository.getArtist(name);
    }


    public ArtistDto createArtist(String name) {

        return artistRepository.createArtist(new ArtistDto(name));
    }


    public ArtistDto deleteArtist(String artistName) {

        return artistRepository.deleteArtist(artistName);
    }


    public ArtistDto addGenreToArtist(String name, String genre) {

        return artistRepository.addArtistGenre(name, genre);
    }
}
