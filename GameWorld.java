import java.util.HashMap;
import java.util.Map;

public class GameWorld {
    private GameMap gameMap;
    private Map<String, Room> rooms = new HashMap<>();
    private Room currentRoom;
    private Player player;
    private String command;
    private boolean displayHelp = true;
    private boolean playerHasEnteredNewRoom;

    public GameWorld() {
        gameMap = new GameMap(); // Initialize the GameMap
        currentRoom = gameMap.createGameWorld(); // Set the initial room
        this.player = new Player(100);  // Assuming 100 health as an example, adjust as needed
    }
    public Player getCurrentPlayer() {
        return player;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    private void dropItem(String itemName) {
        GameWorld controller = null;
        Player player = controller.getCurrentPlayer();
        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        // Find the item in the player's inventory
        Item itemToDrop = null;
        for (Item item : player.getInventory()) {
            if (item.getName() != null && item.getName().equalsIgnoreCase(itemName)) {
                itemToDrop = item;
                break;
            }
        }

        if (itemToDrop != null) {
            // Add the item to the current room's items
            Room currentRoom = controller.getCurrentRoom();
            currentRoom.addItem(itemToDrop);

            // Remove the item from the player's inventory
            player.removeItemFromInventory(itemToDrop);

            System.out.println("You dropped the " + itemName + ".");
        } else {
            System.out.println("You don't have " + itemName + " in your inventory.");
        }
    }


    public String takeItem(String itemName) {
        // If itemName is null
        if (itemName == null) {
            return "Invalid item name.";
        }

        // Special case for the Bathroom and the Painting
        if (currentRoom.getName().equals("Bathroom") && itemName.equalsIgnoreCase("Painting")) {
            // Remove the painting from the room
            Item painting = currentRoom.removeItem(itemName);

            // If the painting was found and removed
            if (painting != null) {
                // Open the hidden Easter egg room
                Room easterEggRoom = new Room("Hidden room", "Congratulations! You've found the room.");
                currentRoom.setExit("east", easterEggRoom);

                // Add the painting to the player's inventory
                addItemToInventory(painting);

                return "You took the painting and discovered a hidden passage to the east!";
            } else {
                // The painting was not found in the room
                return "There is no " + itemName + " here.";
            }
        }

        // Iterate through items in the current room
        for (Item item : currentRoom.getItems()) {
            if (item.getName() != null && item.getName().equalsIgnoreCase(itemName)) {
                // Add the item to the player's inventory
                addItemToInventory(item);

                // Remove the item from the room
                currentRoom.removeItem(item);

                return "You have taken the " + itemName + ".";
            }
        }

        // If itemName is not null and the item is not found
        return "There is no " + itemName + " here.";
    }

    public void addItemToInventory(Item item) {
        Player player = getCurrentPlayer();
        if (player != null) {
            player.addItemToInventory(item);
        } else {
            System.out.println("Player not found. Cannot add the item to inventory.");

            if (command.startsWith("take ")) {
                String itemName = command.substring(5).trim(); // Extract the item name after "take" and remove leading/trailing spaces
                String result = takeItem(itemName);
                System.out.println(result); // Print the result of the takeItem method
            }

        }
    }



    private void displayHealth() {
        int health = player.getHealth();
        String healthDescription = player.getHealthDescription();
        System.out.println("Health: " + health + " - " + healthDescription);
    }

    public Room move(String direction) {
        Room currentRoom = getCurrentRoom();
        // Check if the current room has an exit in the specified direction
        if (currentRoom.hasExit(direction)) {
            // Get the next room
            Room nextRoom = currentRoom.getExit(direction);
            setCurrentRoom(nextRoom); // Update the current room
            return nextRoom;
        } else {
            System.out.println("You can't go that way.");
            return null; // Movement in that direction is not allowed
        }
    }

    public void setLastRoomName(String name) {
    }

    public String getLastRoomName() {
        return null;
    }
        }




