package gc.eleg.daily.flatmapjson.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class Opinion {
    private Map<String, String> localisedOpinions;
}
