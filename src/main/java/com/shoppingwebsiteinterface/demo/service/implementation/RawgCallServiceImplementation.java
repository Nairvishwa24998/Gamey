package com.shoppingwebsiteinterface.demo.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.shoppingwebsiteinterface.demo.dto.GameInfoDto;
import com.shoppingwebsiteinterface.demo.service.RawgCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class RawgCallServiceImplementation implements RawgCallService {



    private final RestTemplate restTemplate;

    @Autowired
    public RawgCallServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GameInfoDto getGameInfoDto(GameInfoDto partialGameInfoDto) {
        GameInfoDto completeGameInfoDto = getGameDescriptionAndPreview(partialGameInfoDto);
        return completeGameInfoDto;
    }


    public GameInfoDto getGameDescriptionAndPreview(GameInfoDto partialGameInfoDto) {
        String apiKey = "0191586e29d04fa09769937e35accbbb";
        String id = partialGameInfoDto.getId();
        String urlDescription = "https://api.rawg.io/api/games/{id}";
        //      To add query params
        UriComponentsBuilder builderDescription = UriComponentsBuilder.fromHttpUrl(urlDescription).queryParam("key", apiKey);
//      To replace variables
//        short_screenshots
        String urlFinalDescription = builderDescription.buildAndExpand(id).toUriString();
        JsonNode descriptionNode = restTemplate.getForObject(urlFinalDescription, JsonNode.class);
        assert descriptionNode != null;
        String description = descriptionNode.get("description_raw").asText();
        partialGameInfoDto.setDescription(description);
        GameInfoDto completeGameInfoDto = partialGameInfoDto;
        return completeGameInfoDto;
    }


}
