import java.util.Random;

public class Enemy {
    private final String name;
    private int health;
    private final int damage = 0;

    private static final int DEFAULT_HEALTH = 50;
    private static final int DEFAULT_DAMAGE = 10;
    private boolean alive;

    public Enemy(String name) {
        if (health < 0 || damage < 0) {
            throw new IllegalArgumentException("Health and damage must be non-negative values.");
        }

        this.name = name;
        this.health = health;
    }

    // Static factory methods
    public static Enemy createSerialKiller() {
        return new Enemy("Serial Killer");
    }

    public static Enemy createGhost() {
        return new Enemy("Enemy.Ghost");
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int amount) {
        if (amount > 0) {
            health -= amount;
            if (health < 0) {
                health = 0;
            }
        }
    }

    private int calculateDamage() {
        // You can add more complex logic for calculating enemy damage based on enemy properties
        return damage;
    }

    public String attack(Player player) {
        int damageDealt = calculateDamage();
        player.takeDamage(damageDealt);
        return "The " + getName() + " attacked you and dealt " + damageDealt + " damage.";
    }

    public boolean isAlive() {
        return alive;
    
    }

    public static class Ghost {

        private String name;
        private int health;
        private Random random = new Random();
        private Weapon weapon;

        public Ghost(String name, int health, Weapon weapon) {
            this.name = name;
            this.health = health;
            this.weapon = weapon;
        }

        public Ghost(String Ghost) {
        }

        public String getWeaponName() {
            return weapon.getName();
        }

        public String getWeaponDescription() {
            return weapon.getDescription();
        }

        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public void takeDamage(int damage) {
            health -= damage;
        }

        public boolean isAlive() {
            return health > 0;
        }


        public int attack() {
            // Define the logic for the killer's attack here.
            // This can involve generating a random attack damage or following a specific pattern.

            int minDamage = 5;
            int maxDamage = 15;
            int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;

            return damage;
        }
    }

    public static class SerialKiller {
        private String name;
        private int health;
        private Random random = new Random();
        private Weapon weapon;

        public SerialKiller(String name, int health, Weapon weapon) {
            this.name = name;
            this.health = health;
            this.weapon = weapon;
        }

        public String getName() {
            return name;
        }

        public String getWeaponName() {
            return weapon.getName();
        }

        public String getWeaponDescription() {
            return weapon.getDescription();
        }

        public int getHealth() {
            return health;
        }

        public void takeDamage(int damage) {
            health -= damage;
        }

        public boolean isAlive() {
            return health > 0;
        }


            public int attack() {
                // Define the logic for the killer's attack here.
                // This can involve generating a random attack damage or following a specific pattern.

                int minDamage = 5;
                int maxDamage = 15;
                int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;

                return damage;
            }
        }
}
