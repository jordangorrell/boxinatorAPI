package fortnox.challenge.boxinatorAPI.services;

import fortnox.challenge.boxinatorAPI.model.Box;

import java.util.List;
import java.util.Map;

public interface IBoxRepository {
    List<Map<String, Object>> getAllBoxes();

    Box addBox(Box box);
}
