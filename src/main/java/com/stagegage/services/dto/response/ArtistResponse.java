package com.stagegage.services.dto.response;

import com.stagegage.services.dto.ArtistDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
public class ArtistResponse {

    private final String name;
    private final Set<String> genres;


    public static List<ArtistResponse> getResponses(List<ArtistDto> artistDtos) {
        if (artistDtos == null) return null;

        List<ArtistResponse> responses = new ArrayList<ArtistResponse>();
        for (ArtistDto dto : artistDtos) {
            responses.add(ArtistResponse.getResponse(dto));
        }

        return responses;
    }

    public static ArtistResponse getResponse(ArtistDto dto) {
        return new ArtistResponse(dto.getName(),
                                  dto.getGenres());
    }


    public ArtistResponse(String name, Set<String> genres) {
        this.name = name;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public Set<String> getGenres() {
        return genres;
    }
}
