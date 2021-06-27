package fortnox.challenge.boxinatorAPI.services;

import fortnox.challenge.boxinatorAPI.model.Box;
import fortnox.challenge.boxinatorAPI.model.BoxDTO;

public interface IBoxService {
    Object getAllBoxes();

    Box addBox(BoxDTO boxDTO);
}
