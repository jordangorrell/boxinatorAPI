package fortnox.challenge.boxinatorAPI.model;

public class BoxDTO {

    private String name;
    private double weight;
    private String color;
    private String countryCode;

    public BoxDTO() {}

    public BoxDTO(String name, double weight, String color, String countryCode) {
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.countryCode = countryCode;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public String getColor() { return color; }
    public String getCountryCode() { return countryCode; }

    public void setName(String name) { this.name = name; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setColor(String color) { this.color = color; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
}
