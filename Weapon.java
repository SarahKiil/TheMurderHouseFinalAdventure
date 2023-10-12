public class Weapon {
    protected String name;
    protected String description;
    protected int damage;
    protected int ammunition;

    public Weapon(String name, String description, int damage, int ammunition) {
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.ammunition = ammunition;
    }

    public Weapon(String name) {
        this(name, "Default description", 0, 0);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public String getDescription() {
        return description;
    }

    public void decrementAmmunition() {
        if (ammunition > 0) {
            ammunition--;
        }
    }

    public boolean isOutOfAmmunition() {
        return ammunition <= 0;
    }

    public void useAmmunitionIfAny() {
        if (!isOutOfAmmunition()) {
            decrementAmmunition();
        }
        if (isOutOfAmmunition()) {
            System.out.println("You tried to attack with " + name + " but it's out of use. You lost and got killed.");
            System.exit(0);
        }
    }

    public String attack(Enemy enemy) {
        if (!isOutOfAmmunition()) {
            decrementAmmunition();
            int damageDealt = calculateDamage();
            enemy.takeDamage(damageDealt);
            return "You attacked " + enemy.getName() + " with " + name + " and dealt " + damageDealt + " damage.";
        } else {
            return "You tried to attack with " + name + " but it's out of ammunition.";
        }
    }

    protected int calculateDamage() {
        return damage;
    }
}

// Knife class
class Knife extends Weapon {
    // Constructor
    public Knife() {
        super("Knife", "A sharp blade for close combat", 10, 100);
    }
}

// Pistol class
class Pistol extends Weapon {
    // Constructor
    public Pistol() {
        super("Pistol", "A ranged firearm", 20, 15);
    }
}
