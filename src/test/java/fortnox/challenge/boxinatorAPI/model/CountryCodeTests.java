package fortnox.challenge.boxinatorAPI.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CountryCodeTests {

    @Test
    @DisplayName("Country Code String Gives Proper Country Code")
    void convertingCountryStringToCountryCodeEnum() {
        String swedenString = "SWE";
        String chinaString = "CHN";
        String brazilString = "BRA";
        String australiaString = "AUS";

        String emptyString = "";
        String nullString = null;

        CountryCode sweden = CountryCode.mapStringToCode(swedenString);
        CountryCode china = CountryCode.mapStringToCode(chinaString);
        CountryCode brazil = CountryCode.mapStringToCode(brazilString);
        CountryCode australia = CountryCode.mapStringToCode(australiaString);

        Assertions.assertNotNull(sweden);
        Assertions.assertNotNull(china);
        Assertions.assertNotNull(brazil);
        Assertions.assertNotNull(australia);

        Assertions.assertEquals(CountryCode.SWEDEN, sweden);
        Assertions.assertEquals(CountryCode.CHINA, china);
        Assertions.assertEquals(CountryCode.BRAZIL, brazil);
        Assertions.assertEquals(CountryCode.AUSTRALIA, australia);
    }

    @Test
    @DisplayName("Null or Empty String Returns Null Country Code")
    void convertNullOrEmptyCountryStringResultsInNullCountryCode() {
        String emptyString = "";
        String nullString = null;

        CountryCode nullFromEmptyString = CountryCode.mapStringToCode(emptyString);
        CountryCode nullFromNullString = CountryCode.mapStringToCode(nullString);

        Assertions.assertNull(nullFromEmptyString);
        Assertions.assertNull(nullFromNullString);
    }

}
