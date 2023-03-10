package me.teenageorge.daily.flatmapjson.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.teenageorge.daily.flatmapjson.domain.Opinion;
import me.teenageorge.daily.flatmapjson.domain.Opinions;
import me.teenageorge.daily.flatmapjson.exception.OpinionException;
import me.teenageorge.daily.flatmapjson.json.OpinionJsonDeserialiser;

import java.util.Map;

public class FlattenMap {

    private final OpinionJsonDeserialiser jsonDeserialiser = new OpinionJsonDeserialiser();

    public static void main(String[] args) {
        FlattenMap main = new FlattenMap();
        Map<String, Opinion> opinionsMap = main.deserialiseInput();
        var opinions = new Opinions(opinionsMap);
        var flattenedOpinions = opinions.getLocalisedOpinions();
        System.out.println(main.serialiseOutput(flattenedOpinions));
    }

    private Map<String, Opinion> deserialiseInput() {
        OpinionJsonDeserialiser jsonDeserialiser = new OpinionJsonDeserialiser();
        var opinionDto = jsonDeserialiser.deserialise("flatten-map-input.json");
        return opinionDto.getOpinionMap();
    }

    private String serialiseOutput(Map<String, String> flattenedOpinions) {
        try {
            return jsonDeserialiser.getObjectMapper().writeValueAsString(flattenedOpinions);
        } catch (JsonProcessingException e) {
            throw new OpinionException("Error attempting to convert flattened map to JSON string", e);
        }
    }
}
