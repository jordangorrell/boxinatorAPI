package fortnox.challenge.boxinatorAPI.model;

public class Box {

    private int id = -1;
    private String name;
    private double weight;
    private String color;
    private CountryCode country;
    private double shippingCost;

    public Box(String name, double weight, String color, CountryCode country) {
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.country = country;
        this.shippingCost = calculateShippingCost();
    }

    public Box(String name, double weight, String color, String countryCode) {
        this(name, weight, color, CountryCode.mapStringToCode(countryCode));
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public String getColor() { return color; }
    public CountryCode getCountry() { return country; }
    public double getShippingCost() { return shippingCost; }

    public void setId(int id) { this.id = id; }

    private double calculateShippingCost() {
        if (country == null) return -1;
        return weight * country.getCostMultiplier();
    }
}
