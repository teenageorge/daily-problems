package gc.eleg.daily.flatmapjson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Opinions {

    private Map<String, Opinion> opinions;

    public Map<String, String> getLocalisedOpinions() {
        return opinions.entrySet().stream()
                .flatMap(opinionEntry ->
                        opinionEntry.getValue().getLocalisedOpinions()
                                .entrySet().stream()
                                .map(localeOpinionEntry ->
                                        Map.entry(
                                                StringUtils.joinWith(" - ", opinionEntry.getKey(), localeOpinionEntry.getKey()),
                                                localeOpinionEntry.getValue())
                                )
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new));
    }
}
