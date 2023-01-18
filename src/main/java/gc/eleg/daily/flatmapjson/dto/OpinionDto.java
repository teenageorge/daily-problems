package gc.eleg.daily.flatmapjson.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import gc.eleg.daily.flatmapjson.domain.Opinion;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class OpinionDto {

    private Map<String, Opinion> opinionMap = new LinkedHashMap<>();

    @JsonAnySetter
    public void anySetter(String id,  Map<String, String> map) {
        var opinion = new Opinion();
        opinion.setLocalisedOpinions(map);
        opinionMap.put(id, opinion);
    }
}
