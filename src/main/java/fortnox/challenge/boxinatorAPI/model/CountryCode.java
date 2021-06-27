package fortnox.challenge.boxinatorAPI.model;

public enum CountryCode {
    // Country values are IBAN Alpha-3 Codes: https://www.iban.com/country-codes

    SWEDEN("SWE", 1.3),
    CHINA("CHN", 4.0),
    BRAZIL("BRA", 8.6),
    AUSTRALIA("AUS", 7.2)
    ;

    private final String code;
    private final double costMultiplier;

    private CountryCode(String code, double costMultiplier) {
        this.code = code;
        this.costMultiplier = costMultiplier;
    }

    public String getCode() { return this.code; }
    public double getCostMultiplier() { return this.costMultiplier; }

    public static CountryCode mapStringToCode(String countryCode) {
        if (countryCode == null) return null;

        CountryCode[] countries = CountryCode.values();

        for (CountryCode country : countries) {
            if (country.code.equalsIgnoreCase(countryCode.trim())) {
                return country;
            }
        }

        return null;
    }
}
