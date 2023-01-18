package me.teenageorge.daily.flatmapjson.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.teenageorge.daily.flatmapjson.dto.OpinionDto;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Objects;

public class OpinionJsonDeserialiser {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    @SneakyThrows(IOException.class)
    @JsonAnySetter
    public OpinionDto deserialise(String fileName) {
        Objects.requireNonNull(fileName, "File name shouldn't be empty");
        var inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        Objects.requireNonNull(inputStream, "It appears the file is not where it is supposed to be. Is the file in resources folder?");
        return OBJECT_MAPPER.readValue(inputStream, OpinionDto.class);
    }
}