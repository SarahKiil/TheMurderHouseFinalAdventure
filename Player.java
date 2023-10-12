import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.String;



public class Player {
    private int health;
    private Room currentRoom;
    private List<Item> inventory;
    private Weapon equippedWeapon;
    List<Weapon> weaponInventory;
    private Set<String> eatenFoods;  // Added the set to keep track of eaten foods
    private Inventory playerInventory;
    List<Weapon> weapons;
    private int healthPoints;
    private Player player;
    private Scanner scanner;


    public Player(int health) {
        this.health = health;
        this.inventory = new ArrayList<>();
        this.weaponInventory = new ArrayList<>();
        this.eatenFoods = new HashSet<>();  // Initialize the set
    }

    public int getHealth() {
        return health;
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
        System.out.println("You have equipped the " + weapon.getName() + ".");
    }

    public List<Weapon> getWeaponInventory() {
        return weaponInventory;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void addWeaponToInventory(Weapon weapon) {
        weaponInventory.add(weapon);
    }

    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    public void increaseHealth(int amount) {
        health += amount;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        health = Math.max(0, health); // Ensure health doesn't go below 0
    }

    public Room move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
        }
        return currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getHealthDescription() {
        if (health >= 70) {
            return "Healthy";
        } else if (health >= 30) {
            return "Injured";
        } else if (health > 0) {
            return "Badly Injured";
        } else {
            return "Dead";
        }
    }

    public Set<String> getEatenFoods() {
        return eatenFoods;
    }


    public boolean isAlive() {
        return health > 0; // Assuming health is a player attribute that determines their state
    }

    public Weapon chooseWeapon() {
        Scanner scanner = new Scanner(System.in);
        List<Weapon> weapons = getWeaponInventory(); // Assuming you have a method to get the player's weapons

        if (weapons.isEmpty()) {
            System.out.println("You don't have any weapons.");
            System.exit(0);
            return null;
        }

        System.out.println("Select a weapon:");

        for (int i = 0; i < weaponInventory.size(); i++) {
            System.out.println((i + 1) + ". " + weaponInventory.get(i).getName() + " (" + weaponInventory.get(i).getAmmunition() + ")");
        }

        int choice = -1;
        while (choice < 1 || choice > weaponInventory.size()) {
            System.out.print("Enter the number of the weapon you want to use: ");
            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                scanner.next(); // Consume the invalid input
            }

            if (choice < 1 || choice > weaponInventory.size()) {
                System.out.println("Invalid choice. Please select a valid weapon.");
            }
        }

        return weaponInventory.get(choice - 1);
    }

    public void takeDamage(int amount) {
        health -= amount;
        health = Math.max(0, health); // Ensure health doesn't go below 0

        if (health == 0) {
            System.out.println("You have died. Game over!");
            // You might want to end the game or take other actions when the player dies
        }
    }
}


