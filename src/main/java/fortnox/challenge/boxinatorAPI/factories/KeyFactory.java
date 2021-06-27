package fortnox.challenge.boxinatorAPI.factories;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class KeyFactory implements IKeyFactory {

    @Override
    public KeyHolder generateKeyHolder() {
        return new GeneratedKeyHolder();
    }

}
