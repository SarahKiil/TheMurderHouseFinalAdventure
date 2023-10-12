public class Food extends Item {
    private final String name;
    private final int healthPoints;
    private final String eatDescription;

    // Constructor for specifying name, description, healthPoints, and eatDescription
    public Food(String name, String description, int healthPoints, String eatDescription) {
        super(description);
        this.name = name;
        this.healthPoints = healthPoints;
        this.eatDescription = eatDescription;
    }

    // Constructor for specifying name, healthPoints, and eatDescription
    public Food(String name, int healthPoints, String eatDescription) {
        super(" " + name + ".");
        this.name = name;
        this.healthPoints = healthPoints;
        this.eatDescription = eatDescription;
    }

    // Constructor for specifying name and eatDescription, with default healthPoints
    public Food(String name, String eatDescription) {
        super(" " + name + ".");
        this.name = name;
        this.healthPoints = 20; // Default healthPoints for a sandwich
        this.eatDescription = eatDescription;
    }

    // Constructor for specifying name and healthPoints, with default eatDescription
    public Food(String name, int healthPoints) {
        super( "" + name + ".");
        this.name = name;
        this.healthPoints = healthPoints;
        this.eatDescription = "It tastes great!";
    }


    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String getEatDescription() {
        return eatDescription;
    }

}
