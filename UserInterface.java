import java.util.*;

public class UserInterface {
    private GameWorld controller;
    private static Scanner scanner;
    private Random random;
    private boolean isFirstGameOutput = true;
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_HELP = "help";
    private boolean gameIsRunning;
    private Player player = new Player(100);
    private Room currentRoom;
    private GameMap gameMap;
    private boolean hasUsedKey;


    public UserInterface(GameWorld controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void startProgram() {
        boolean hasDisplayedWelcomeMessage = false;
        boolean normalGame = false;

        while (true) {
            Room currentRoom = controller.getCurrentRoom();

            if (!hasDisplayedWelcomeMessage) {
                System.out.println("Welcome to the text-based adventure game!");
                hasDisplayedWelcomeMessage = true;

                displayAtmosphericDescription();

                System.out.print("Weapons in Kitchen: ");
                for (Weapon weapon : currentRoom.getWeapons()) {
                    System.out.print(weapon.getName() + ": " + weapon.getDescription());
                }

                System.out.print("\nFood in Kitchen: ");
                for (Food food : currentRoom.getFoodItems()) {
                    System.out.print(food.getName() + ": " + food.getDescription() + " ");
                }

                System.out.println("\nItems in Kitchen: ");
                for (Item item : currentRoom.getItems()) {
                    System.out.print(item.getName() + ": " + item.getDescription());
                }
            }

            if (!currentRoom.getName().equalsIgnoreCase(controller.getLastRoomName())) {
                displayGlitchyText(currentRoom.getRoomDescription());
                controller.setLastRoomName(currentRoom.getName());
            }

            if (normalGame) {
                for (Weapon weapon : currentRoom.getWeapons()) {
                    System.out.print("Weapons in " + currentRoom.getName() + ": ");
                    System.out.print(weapon.getName() + ": " + weapon.getDescription());
                }
                for (Food food : currentRoom.getFoodItems()) {
                    System.out.print("\nFood in " + currentRoom.getName() + ": ");
                    System.out.print(food.getName() + ": " + food.getDescription());
                }
                for (Item item : currentRoom.getItems()) {
                    if (item.getName() != null) {
                        System.out.print("\nItems in " + currentRoom.getName() + ": ");
                        System.out.print(item.getName() + ": " + item.getShortName() + "\n");
                    }
                }
            }

            //Check if enemy has entered the room
            if (currentRoom.hasEnemy()) {
                if (currentRoom.hasEnemyGhost()) {
                    currentRoom.triggerGhostEncounter(player);
                }
                if (currentRoom.hasEnemySerialKiller()) {
                    currentRoom.triggerSerialKillerEncounter(player);
                }
            }

            if (currentRoom.getName() == "Bedroom three") {
                interactWithOldWoman();
            }


            System.out.print("\nEnter a command: ");
            String input = scanner.nextLine().toLowerCase();
            normalGame = true;

            processCommand(input);
        }
    }

    private void displayAtmosphericDescription() {
        System.out.println("You stand before the house,\n" +
                " a real estate agent tasked with listing a property marred by a recent murder. \n" +
                "With a deep breath, you grasp the cold brass doorknob, \n" +
                "slowly turning it to allow the door to creak open. \n" +
                "The entryway unfolds, bathed in dim light, \n" +
                "revealing polished wooden floors that seem to whisper the house's history with each creak underfoot.\n " +
                "The silence within is broken only by faint rustling curtains and distant sounds from outside.\n" +
                " An eerie tension hangs in the air as you step inside,\n" +
                " acutely aware of the unsettling energy that lingers within the walls.\n");
    }

    public void interactWithOldWoman() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("As you enter the room, you see an old woman sitting in a chair. She looks at you and asks, 'Do you seek guidance, traveler? (yes/no)'");

        String response = scanner.nextLine().toLowerCase();

        if (response.equals("yes")) {
            System.out.println("The woman smiles and says, 'Seek the key in the hidden room, for it holds the power you need.'");
        } else if (response.equals("no")) {
            System.out.println("The woman nods and continues murmuring to herself.");
        } else {
            System.out.println("The woman looks puzzled and goes back to her murmuring.");
        }
    }

    private void processCommand(String input) {
        switch (input) {
            case COMMAND_EXIT:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            case COMMAND_HELP:
                displayHelp();
                break;
            case "look":
                displayDetailedDescription(controller.getCurrentRoom());
                break;
            case "eat":
                processEatCommand();
                break;
            case "take":
                processTakeCommand();
                break;
            case "inventory":
                displayInventory();
                break;
            case "go":
                System.out.print("Enter a direction: ");
                String inputDirection = scanner.nextLine().toLowerCase();
                move(inputDirection);
                break;
            case "use":
                processUseCommand(controller.getCurrentPlayer(), controller.getCurrentRoom(), gameMap);
                break;
            default:
                displayGlitchyText("Invalid command. Type 'help' for a list of commands.");
                break;
        }
    }

    public static void processUseCommand(Player player, Room currentRoom, GameMap gameMap) {
        Scanner scanner = new Scanner(System.in);
        String playerKey = null;

        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getName().equals("key")) {
                playerKey = player.getInventory().get(i).getName();
            }
        }

        if (currentRoom.getName().equals("Cage") && Objects.equals(playerKey, "key")) {
            System.out.println("You are in the 'Cage' room and have the key.");
            System.out.print("Do you want to use the key to open the cage? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("yes")) {
                System.out.println("You used the key to open the cage and won the game!");
                // Set a flag or condition to mark the game as won
                System.exit(0);
            } else if (response.equals("no")) {
                System.out.println("You chose not to use the key to open the cage.");
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        } else {
            System.out.println("You can't use the key here or don't have the key.");
        }
    }

    private void processEatCommand() {
        System.out.print("Enter the name of the food you want to eat: ");
        String foodName = scanner.nextLine();
        eatFood(foodName);
    }

    private void processTakeCommand() {
        System.out.println("Processing 'take' command...");
        System.out.print("Enter the name of the item you want to take: ");
        String itemName = scanner.nextLine();
        takeItem(itemName);
    }

    private void move(String direction) {
        Room nextRoom = controller.move(direction);

        if (nextRoom == null) {
            displayGlitchyText("You cannot go that way.");
        } else {
            displayGlitchyText("You move to the " + direction + ".");
            controller.setCurrentRoom(nextRoom);
        }
    }

    private void displayInventory() {
        Player player = controller.getCurrentPlayer();
        List<Item> inventory = player.getInventory();
        List<Weapon> weapons = player.getWeaponInventory();

        StringBuilder inventoryMessage = new StringBuilder();

        System.out.println("Inventory: ");
        if (inventory.isEmpty()) {
            inventoryMessage.append("Your item inventory is empty.");
        } else {
            for (Item item : inventory) {
                inventoryMessage.append("\n- ").append(item.getName());
            }
        }

        if (weapons.isEmpty()) {
            inventoryMessage.append("\nYour weapon inventory is empty.");
        } else {
            System.out.print("\nWeapons in inventory: ");
            for (Weapon weapon : weapons) {
                System.out.println(weapon.getName() + ": " + weapon.getDescription());
            }
        }

        displayGlitchyText(inventoryMessage.toString());
    }

    private void displayHelp() {
        displayGlitchyText("Commands:");
        System.out.println("  'exit': Exit the game");
        System.out.println("  'help': Display this help message");
        System.out.println("  'look': Look around the room");
        System.out.println("  'eat [food]': Eat food from your inventory");
        System.out.println("  'go [direction]': Move in a direction (e.g., 'go north')");
        System.out.println("  'take [item]': Take an item from the room");
        System.out.println("  'attack': Attack an enemy in the room");
        System.out.println("  'use weapon': Use your equipped weapon");
        System.out.println("  'inventory': View your inventory");
    }

    private void takeItem(String itemName) {
        Room currentRoom = controller.getCurrentRoom();
        Item foundItem = null;

        // Search for the item in the room's items and foodItems
        for (Item roomItem : currentRoom.getItems()) {
            if (isItemMatching(itemName, roomItem)) {
                foundItem = roomItem;
                break;
            }
        }

        // Also search in foodItems
        if (foundItem == null) {
            for (Food foodItem : currentRoom.getFoodItems()) {
                if (isItemMatching(itemName, foodItem)) {
                    foundItem = foodItem;
                    break;
                }
            }
        }

        // Also search in Weapons
        if (foundItem == null) {
                takeWeapon(itemName);
        }

        if (foundItem != null) {
            // You found the item, now you can take it
            currentRoom.removeItem(foundItem);

            // Add the item to the player's inventory
            Player player = controller.getCurrentPlayer();
            player.addItemToInventory(foundItem);

            // Display a message indicating that the item has been taken
            displayGlitchyText("You took the " + foundItem.getName() + ".");
        } else {
            // The item was not found in the room
            displayGlitchyText("There is no " + itemName + " here.");
        }
    }

    private boolean isItemMatching(String itemName, Item item) {
        return item.getName() != null && item.getName().trim().equalsIgnoreCase(itemName.trim());
    }

    private void displayDetailedDescription(Room room) {
        String detailedDescription = room.getDetailedDescription();
        displayGlitchyText(detailedDescription);
    }

    public static void displayGlitchyText(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                // Introduce a small delay between characters
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); // Move to the next line after printing the text
    }

    private void eatFood(String foodName) {
        Player player = controller.getCurrentPlayer();
        if (player == null) {
            displayGlitchyText("Player not found.");
            return;
        }

        // Convert input to lowercase for case-insensitive comparison
        foodName = foodName.toLowerCase();

        Food foodToEat = null;
        for (Item item : player.getInventory()) {
            if (item instanceof Food && isItemMatching(foodName, item)) {
                foodToEat = (Food) item;
                break;
            }
        }

        if (foodToEat != null) {
            int healthModifier = foodToEat.getHealthPoints();
            if (healthModifier > 0) {
                player.increaseHealth(healthModifier);
            } else {
                player.decreaseHealth(-healthModifier);
            }

            // Remove the food item from the player's inventory
            player.removeItemFromInventory(foodToEat);

            displayGlitchyText("You ate the " + foodName + ". " + foodToEat.getEatDescription());
        } else {
            displayGlitchyText("You don't have " + foodName + " in your inventory.");
        }
    }

    private String getPlayerInput() {
        // You can use your Scanner to get user input
        return scanner.nextLine();
    }

    private void displayGameState() {
        // Implement code to display the current game state
        // This could include displaying the player's status, room description, etc.
        // You can use System.out.println() to print information to the console.
    }

    private void updateGameState() {
        // Implement code to update the game state based on player actions
        // This could include checking for win/lose conditions, advancing the story, etc.
    }

    public void takeWeapon(String weaponName) {
        Room currentRoom = controller.getCurrentRoom();
        Weapon foundWeapon = null;

        // Search for the weapon in the room's weapons
        for (Weapon roomWeapon : currentRoom.getWeapons()) {
            if (roomWeapon.getName() != null && roomWeapon.getName().equalsIgnoreCase(weaponName)) {
                foundWeapon = roomWeapon;
                break;
            }
        }

        while (gameIsRunning) { // You should have a condition to exit the loop when the game ends
            // Get player input or take other game actions
            String playerInput = getPlayerInput(); // This is just a placeholder for getting player input

            // Process player input and update game state
            if (playerInput.equalsIgnoreCase("go to bunker")) {
                // Assuming "go to bunker" is how the player can enter the bunker room
                // Check if the player is in the bunker room
                if (player.getCurrentRoom().getName().equalsIgnoreCase("bunker")) {
                    // Trigger the serial killer encounter
                    player.getCurrentRoom().triggerSerialKillerEncounter(player);
                } else {
                    // Handle the case where the player tries to go to the bunker but is not in the bunker room
                    System.out.println("You cannot go to the bunker from this room.");
                }
            } else if (playerInput.equalsIgnoreCase("quit")) {
                // Handle the player quitting the game
                gameIsRunning = false; // Set the condition to exit the game loop
            } else {
                // Handle other game actions and commands
                // ...
            }

            // Update and display game state
            updateGameState(); // Update the game state based on player actions
            displayGameState(); // Display the current game state to the player
        }

// Game loop ends when the gameIsRunning condition is false


        if (foundWeapon != null) {
            // You found the weapon, now you can take it
            currentRoom.removeWeapon(foundWeapon);
            // Add the weapon to the player's inventory (you may need to implement this method in the Player class)
            player.addWeaponToInventory(foundWeapon);
            // Display a message indicating that the weapon has been taken
            displayGlitchyText("You took the " + foundWeapon.getName() + ".");
        } else {
            // The weapon was not found in the room
            displayGlitchyText("There is no " + weaponName + " here.");
        }

    }
}
