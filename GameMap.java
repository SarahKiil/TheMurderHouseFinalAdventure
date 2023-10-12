import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class GameMap {
    private Room currentRoom;
    private Room kitchenRoom;
    private Player player;
    private LinkedList<Weapon> weapons;
    private Room diningRoom;
    private String itemName;
    private Room atticRoom;
    private Enemy.Ghost Ghost;
    private boolean hasUsedKey;
    private boolean gameWon;

    public GameMap(Player player) {
        this.player = player;
        this.weapons = new LinkedList<>();
    }

    public GameMap() {
        this.weapons = new LinkedList<>();
    }

    public Room createGameWorld() {

        kitchenRoom = new Room("Kitchen", "");
        Room livingRoom = new Room("Living Room", "");
        Room diningRoom = new Room("Dining Room", "You are in the dining room.");
        Room secretRoom = new Room("Secret Room", "You are in a hidden secret room.");
        Room atticRoom = new Room("Attic", "You are in the attic.");
        Room gardenRoom = new Room("Garden", "You are in the garden.");
        Room basementRoom = new Room("Basement", "You are in the basement.");
        Room bedRoom = new Room("Bedroom", "You are in the bedroom.");
        Room bathRoom = new Room("Bathroom", "You are in the bathroom.");
        Room bedRoomtwo = new Room("Second bedroom", "You are in the second bedroom");
        Room closetRoom = new Room("Closet", "Yoi are in the closet");
        Room bunkerRoom = new Room("Bunker", "You are in an underground bunker");
        Room underGround = new Room("Underground", "You are underground");
        Room easterEggRoom = new Room("Hidden room", "Congratulations! You've found the hidden room.");
        Room cage = new Room("Cage", "Behind the secret door revealed by an old painting," +
                " a surprising sight awaits—a small cage, its bars enclosing" +
                " a person who meets your gaze with silent urgency.");
        Room womanroom = new Room("Bedroom three", "");

        // Connect rooms
        kitchenRoom.setExit("north", secretRoom);
        secretRoom.setExit("south", kitchenRoom);
        secretRoom.setExit("north", bathRoom);
        bathRoom.setExit("south", secretRoom);
        bathRoom.setExit("up", atticRoom);
        atticRoom.setExit("down", bathRoom);
        secretRoom.setExit("west", bedRoom);
        bedRoom.setExit("east", secretRoom);
        bedRoom.setExit("south", gardenRoom);
        gardenRoom.setExit("north", bedRoom);
        bedRoom.setExit("down", basementRoom);
        basementRoom.setExit("up", bedRoom);
        gardenRoom.setExit("north", bedRoom);
        gardenRoom.setExit("down", bunkerRoom);
        bunkerRoom.setExit("up", gardenRoom);
        bunkerRoom.setExit("down", underGround);
        underGround.setExit("up", bunkerRoom);
        basementRoom.setExit("up", bedRoom);
        bedRoom.setExit("west", womanroom);
        womanroom.setExit("east", bedRoom);
        kitchenRoom.setExit("south", livingRoom);
        livingRoom.setExit("north", kitchenRoom);
        livingRoom.setExit("south", diningRoom);
        diningRoom.setExit("west", livingRoom);
        diningRoom.setExit("south", bedRoomtwo);
        bedRoomtwo.setExit("north", diningRoom);
        bedRoomtwo.setExit("west", closetRoom);
        closetRoom.setExit("east", bedRoomtwo);
        bathRoom.setExit("east", easterEggRoom);
        easterEggRoom.setExit("north", cage);
        cage.setExit("south", easterEggRoom);
        kitchenRoom.setExit("north", secretRoom);
        gardenRoom.setExit("east", kitchenRoom);
        kitchenRoom.setExit("west", gardenRoom);

        secretRoom.addItem(new Item("candle", ", a partially burned candle"));
        atticRoom.addItem(new Item("photo", ", an old, creepy family photo"));
        atticRoom.addItem(new Item("paper", ", paper with weird scribbles on"));
        gardenRoom.addItem(new Item("flower", ", a decaying flower"));
        basementRoom.addItem(new Item("hammer", ", an old rusty hammer, with blood on it"));
        bathRoom.addItem(new Item("shoe", " , One male shoe"));
        livingRoom.addItem(new Item("coins", " , some old coins"));
        diningRoom.addItem(new Item("pen"));
        bunkerRoom.addItem(new Item("bullets for semi-automatic pistol"));
        diningRoom.addItem(new Item("tape", "an old tape"));
        bedRoomtwo.addItem(new Item("coins", "some ond coins"));
        bedRoomtwo.addItem(new Item("book", "book with scribbles"));
        closetRoom.addItem(new Item("scribble translator", "a translator for the scribbles"));
        bathRoom.addItem(new Item("emergency kit"));
        gardenRoom.addItem(new Item("photo"));
        Item painting = new Item("Painting", "An intriguing painting on the wall.");
        underGround.addItem(new Item("key", "key for cage"));
        bathRoom.addItem(painting);



        Food apple = new Food("Apple", "A fresh red apple.", 10, "It's crisp and delicious.");
        Food sandwich = new Food("Sandwich", "A delicious-looking sandwich.", 20, "It's a hearty sandwich.");
        Food cake = new Food("Cake", "A slice of chocolate cake.", -15, "It's rich and decadent.");
        Food stalebread = new Food("stale bread", 10, "A loaf of stale bread");
        Food cannedfood = new Food("Canned food", 5, "An old can of canned food");
        Food oldfruit = new Food("Old fruit", -20);
        Food rottenfood = new Food("Rotten fruit", -30);

        Weapon knife = new Weapon("Knife", "sharp old knife", 30, 20);
        Weapon pistol = new Weapon("Pistol", "semi automatic pistol", 40, 20);

        Enemy ghost = new Enemy("Ghost");
        atticRoom.addEnemy(ghost);

        Enemy serialkiller = new Enemy("Serialkiller");
        bunkerRoom.addEnemy(serialkiller);


        // Assuming weapons is declared somewhere in your class as List<Weapon>
        weapons = new LinkedList<>();

        kitchenRoom.addFood(new Food("apple", "a fresh apple"));
        kitchenRoom.addFood(new Food("sandwich", "a delicious sandwich"));
        basementRoom.addFood(new Food("cake", "a birthday cake"));
        diningRoom.addFood(new Food("stale bread", "stale, old bread"));
        bunkerRoom.addFood(new Food("canned food", "canned food, that looks like dog food"));
        bedRoom.addFood(new Food("old fruit", ""));
        secretRoom.addFood(new Food("rotten food", ""));


        diningRoom.addWeapon(new Knife());
        //System.out.println("New weapon (Knife) added to the room.");

        kitchenRoom.addWeapon(new Pistol());
        //System.out.println("New weapon (Pistol) added to kitchen.");

        return kitchenRoom;
    }
}





