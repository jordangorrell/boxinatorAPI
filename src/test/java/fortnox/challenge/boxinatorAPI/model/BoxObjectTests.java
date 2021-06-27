package fortnox.challenge.boxinatorAPI.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoxObjectTests {

    private String validName = "validName";
    private String validColor = "#000000";

    @Test
    @DisplayName("Calculated Shipping Cost is Correct")
    void instantiateNewBoxObject_withValidCountry_resultsInCorrectShippingCost() {
        double weight = 2;
        Box box = new Box(validName, weight, validColor, CountryCode.AUSTRALIA);
        Assertions.assertEquals(weight * CountryCode.AUSTRALIA.getCostMultiplier(), box.getShippingCost());
    }

    @Test
    @DisplayName("Calculated Shipping Cost is -1 on Null Country")
    void instantiateNewBoxObject_withInvalidCountry_resultsInNegativeShippingCost() {
        double weight = 2;
        Box box = new Box(validName, weight, validColor, "invalid country");
        Assertions.assertEquals(-1, box.getShippingCost());
    }
}
