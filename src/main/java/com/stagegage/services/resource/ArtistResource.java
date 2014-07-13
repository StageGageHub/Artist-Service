package com.stagegage.services.resource;

import com.stagegage.services.dto.ArtistDto;
import com.stagegage.services.dto.response.ArtistResponse;
import com.stagegage.services.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
@RestController
@RequestMapping("/artists")
public class ArtistResource {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ArtistResponse> getArtists() {

        List<ArtistDto> artistDtos = artistService.getArtists();

        return ArtistResponse.getResponses(artistDtos);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ArtistResponse createArtist(@RequestParam(required = false) String name) {
        ArtistDto dto = artistService.createArtist(name);

        return ArtistResponse.getResponse(dto);
    }

    @RequestMapping(value = "/{artistName}", method = RequestMethod.GET)
    public ArtistResponse getArtistByName(@PathVariable("artistName") String name) {
        ArtistDto dto = artistService.getArtist(name);

        return ArtistResponse.getResponse(dto);
    }

    @RequestMapping(value = "/{artistName}/genres", method = RequestMethod.PUT)
    public ArtistResponse addGenreToArtist(@PathVariable("artistName") String artistName, @RequestParam String genre) {
        ArtistDto dto = artistService.addGenreToArtist(artistName, genre);

        return ArtistResponse.getResponse(dto);
    }
}
