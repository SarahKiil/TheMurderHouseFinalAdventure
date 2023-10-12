public class Item {
    private String longName;       // Full name of the item
    private String shortName;      // Short name or alias of the item
    private boolean isEdible;      // Flag indicating if the item is edible
    private int healthPoints;       // Health points associated with the item
    private String description;     // Description of the item

    // Constructor for items without names (using only description)
    public Item(String description) {
        this.description = description;
    }

    // Constructor for items with names
    public Item(String longName, String shortName) {
        this.longName = longName;
        this.shortName = shortName;
        this.isEdible = false;       // Items are not edible by default
        this.healthPoints = 0;       // Default health points
    }

    // Getter for the short name or alias of the item
    public String getShortName() {
        return shortName;
    }

    // Getter for the health points associated with the item
    public int getHealthPoints() {
        return healthPoints;
    }

    // Setter for the health points associated with the item
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    // Getter for the description of the item
    public String getDescription() {
        return description;
    }

    // Getter for the name of the item (either longName or shortName based on availability)
    public String getName() {
        return (longName != null) ? longName : shortName;
    }


}
