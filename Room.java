import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Room {

    private String name;
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;
    private List<Food> foodItems;
    private List<Enemy> enemies;
    private List<Weapon> weapons;
    private Enemy enemy;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        this.foodItems = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRoomDescription() {
        return "You are in " + getName() + ".";
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Food> getFoodItems() {
        return foodItems;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public Enemy findEnemy(String name) {
        for (Enemy enemy : enemies) {
            if (enemy.getName().equalsIgnoreCase(name)) {
                return enemy; // Return the enemy if found
            }
        }
        return null; // Return null if no enemy with the given name is found
    }


    public String getDetailedDescription() {

        switch (getName().toLowerCase()) {
            case "kitchen":
                return "The kitchen is in a state of disarray.\n" +
                        " Broken dishes and shattered glass cover the floor.\n" +
                        "A flickering fluorescent light casts eerie shadows on the cracked walls.\n" +
                        " The air is heavy with \n" +
                        "the acrid smell of burnt food. A rusty refrigerator stands in one corner, " +
                        "its door slightly ajar. \n" +
                        "You sense a faint presence in the room, as if you are not alone.";

            case "secret room":
                return "An unexpected discovery sends a shiver down your spine. \n" +
                        " Hidden behind a bookshelf in the dimly lit study, \n" +
                        "you stumble upon a secret room—a clandestine chamber known to only a few.";

            case "living room":
                return "The living room exudes an odd sense of nostalgia.\n" +
                        " Faded photographs of long-forgotten faces adorn \n" +
                        "the walls. An old, moth-eaten sofa sits in the center of the room, \n" +
                        "facing a dusty television. \n" +
                        "A threadbare rug covers the creaky wooden floor. \n" +
                        "The atmosphere is both cozy and eerie, as if \n" +
                        "memories of past gatherings linger in the air.";

            case "dining room":
                return "You step into the dining room, and the atmosphere is thick with history. \n" +
                        "The centerpiece of the room is a grand wooden table,\n" +
                        " its surface obscured by a heavy layer of dust. \n" +
                        "A single chair, slightly pulled back, hints at a recent presence. \n" +
                        "As you explore further, you come across a family portrait on the wall. \n" +
                        "The portrait depicts a happy family, their smiles frozen in time, \n" +
                        "surrounded by the opulence of a bygone era. \n" +
                        "Dust motes dance in the dim light, and the room seems to hold its breath,\n" +
                        " waiting for you to uncover its secrets.";

            case "basement":
                return "Descending further into the basement, the cold and dampness intensify, \n" +
                        "and the eerie stillness seems to envelop you like a shroud.\n" +
                        " The sound of water droplets falling onto unseen surfaces \n" +
                        "creates an unsettling rhythm in the dim light.\n" +
                        " You notice an old wooden crate in the corner, \n" +
                        "half-buried under a pile of forgotten belongings. \n" +
                        "Curiosity compels you to investigate.\n" +
                        "As you move closer, you discover a hidden compartment beneath the crate. \n" +
                        "Brushing away the dust and cobwebs, you reveal a vintage leather suitcase. \n" +
                        "It's covered in years of grime but remains remarkably intact.\n" +
                        " The suitcase exudes an air of mystery, as if it holds long-forgotten \n" +
                        "secrets and memories from the past. \n" +
                        "Could this be the key to unraveling the enigma of this place? \n" +
                        "The dripping noise continues, almost as if the basement itself is whispering its secrets to you.";

            case "attic":
                return "You cautiously ascend the creaking wooden stairs leading to the attic. \n" +
                        "The air grows colder and heavier with each step, \n" +
                        "as if a shroud of darkness awaits you above. \n" +
                        "Finally, you push open the attic door,\n" +
                        " and dim, dusty light filters in, revealing an unsettling sight. \n" +
                        "The attic is filled with old furniture draped in tattered sheets, \n" +
                        "casting eerie, ghost-like shapes in the half-light. \n" +
                        "A single window, long forgotten and covered in grime," +
                        " lets in only a feeble glimmer of the outside world. " +
                        "Cobwebs hang like spectral curtains, and the air is thick with an indefinable sense of dread.\n" +
                        "You feel as though you are being watched by something unseen, and the oppressive atmosphere presses down on you.";

            case "bedroom":
                return "The bedroom is shrouded in darkness,\n" +
                        " with only faint moonlight seeping through the tattered curtains. \n" +
                        "The air is heavy with an oppressive stillness," +
                        " broken only by the occasional creaking of the old wooden floorboards beneath your feet. \n" +
                        "A large, dusty mirror hangs on one wall, \n" +
                        "its glass cracked and reflecting distorted images that \n" +
                        "send shivers down your spine. An unmade bed, its sheets twisted and stained,\n" +
                        " sits against the far \n" +
                        "wall, giving the impression that someone left it in a hurry. \n" +
                        "You can't shake the feeling that \n" +
                        "you're not alone in this room, and a sense of dread gnaws at the edges of your mind.";

            case "garden":
                return "You find yourself in a sinister garden that seems to have withered and decayed with time. \n" +
                        "Overgrown thorny bushes, twisted and blackened, \n" +
                        "claw their way towards the sky, blocking out most of the moonlight.\n" +
                        "Skeletal trees with gnarled branches reach out as if trying to snatch your very soul. \n" +
                        "The ground is covered with a thick carpet of dead leaves, \n" +
                        "their rustling sounds eerily amplified in the silence. \n" +
                        "In the center of this nightmarish garden, you spot a shallow grave,\n" +
                        " freshly dug and barely concealed. \n" +
                        "The earth is still loose and moist, and a sense of dread fills the air. \n" +
                        "An unsettling feeling washes over you as you wonder what might be buried here,\n" +
                        " and you can't help but feel watched, \n" +
                        "as if the very garden itself holds secrets too dark to be uncovered.";

            case "bedroom three":
                    return "\n" +
                            "In a shadowy room, you stumble upon an elderly woman seated in an antique chair.\n" +
                            " The room itself seems frozen in time, adorned with relics and ancient tapestries.\n" +
                            " As you approach, you realize she's murmuring cryptic words that feel like a whispered secret meant only for you.\n" +
                            " Her presence is both captivating and eerie, drawing you into a mysterious realm where the past and present converge,\n" +
                            " leaving you with an unsettling sense of curiosity and wonder.\n";

            case "bedroom two":
                return "In a child's bedroom with open windows,\n" +
                        "a pale moonlight casts eerie shadows on faded posters and scattered toys.\n" +
                        " The room feels strangely cold, \n" +
                        "and a chilling breeze carries faint whispers that make the hair on your neck stand on end. \n" +
                        "It's as if unseen eyes are watching, and an unsettling sense of otherworldly presence lingers in the air, \n" +
                        "leaving you with an unsettling feeling that you're not alone in the room.\n";

            case "closet":
                return "In a dimly lit closet,\n" +
                        " the air is thick with an eerie stillness.\n" +
                        "  The walls, cloaked in shadows,\n" +
                        "  seem to close in on you,\n " +
                        " and the sparse shelves are cluttered with forgotten items.\n" +
                        "  What draws your attention most, however,\n" +
                        "  is a single, ominous sight: a frayed rope dangles ominously from the ceiling,\n" +
                        "  its coarse fibers suggesting a history of unknown purpose.\n" +
                        "  The closet exudes an unsettling aura,\n" +
                        "  leaving you with an undeniable feeling of unease as you contemplate the mysteries concealed within its gloomy confines.";

            case "bathroom":
                return "\n" +
                        "In this bathroom, a chilling ambiance prevails.\n" +
                        " Dim, flickering light and cracked mirrors lend an unsettling air. \n" +
                        "An eerie, rhythmic faucet drip echoes through the silence.\n" +
                        " The bathtub's cryptic markings, stained with an inexplicable pool of blood,\n "+
                        "add to the sense of foreboding, leaving you with an eerie feeling that something is deeply amiss.";

            case "hidden room":
                return "Concealed behind an unassuming painting,\n" +
                        " a hidden room unveils a chilling tableau. \n" +
                        "Every inch of the walls is covered in photographs,\n" +
                        " capturing generations of moments. The unsettling part? \n" +
                        "It seems as though someone meticulously arranged the pictures,\n " +
                        "as if they were watching, silently preserving the family's history in this eerie gallery of memories.";

            case "cage":
                    return "In a desolate chamber, you stumble upon a cage, \n" +
                            "its iron bars weathered and ominous. \n" +
                            "Inside, a frail figure cowers, their eyes pleading for help amidst palpable fear.\n" +
                            " The room's oppressive aura sends shivers down your spine, \n" +
                            "and the captive's desperate gaze conveys an urgent need for rescue,\n" +
                            " stirring a deep sense of compassion and determination within you.\n" +
                            " Perhaps you should 'use' a key to unlock the cage and free the hostage.\n";
            case "bunker":
                return "As you cautiously descend into the depths of the underground bunker,\n" +
                        " an eerie stillness envelops you. The air is heavy with the scent of dust and neglect,\n" +
                        " and cobwebs drape across forgotten corners like ancient tapestries.\n" +
                        "The bunker, a relic of a bygone era, appears as though it hasn't seen human activity in years.\n" +
                        " Rusted machinery, long silent, stands as mute witnesses to the passage of time.\n " +
                        "Faded maps and obsolete equipment line the shelves, frozen in a state of disuse.\n" +
                        "The sense of abandonment is palpable,\n" +
                        " and the silence is broken only by the distant echoes of your own footsteps.\n" +
                        " As you explore the forgotten chambers, you can't help but wonder what secrets lie buried within the cold,\n" +
                        " lifeless walls of this forsaken sanctuary.\n";

            case "underground":
                return "Beneath the eerie bunker, a chilling underworld awaits.\n " +
                        "A narrow, damp passage descends into darkness, \n" +
                        "revealing abandoned tunnels and silent, motionless mining carts.\n" +
                        " The oppressive stillness and distant echoes create an unsettling atmosphere,\n" +
                        " hinting at forgotten secrets lurking in the depths below.\n";


            default:
                return "You are in " + getName() + ". It's a room with distinct features.";
        }
    }

    public void triggerSerialKillerEncounter(Player player) {
        Weapon weapon = new Weapon("Axe", "used to kill his family", 10, 10); // Create a weapon
        Enemy.SerialKiller serialKiller = new Enemy.SerialKiller("The Serial Killer", 50, weapon); // Create a serial killer with health

        System.out.println("You enter the bunker and encounter " + serialKiller.getName() + "!");
        System.out.println("You can use your weapons to defeat " + serialKiller.getName() + ".");

        while (serialKiller.isAlive() && player.isAlive()) {
            // Let the player choose a weapon to use (you can implement a method for this)
            Weapon selectedWeapon = player.chooseWeapon();

            // Calculate and apply damage
            int damageDealt = selectedWeapon.calculateDamage();
            serialKiller.takeDamage(damageDealt);

            if (serialKiller.isAlive()) {
                // Serial killer attacks back (you can implement their attack logic)
                int killerDamage = serialKiller.attack();
                player.takeDamage(killerDamage);

                //Take ammo from weapon
                selectedWeapon.useAmmunitionIfAny();

                System.out.println(serialKiller.getName() + " attacks you with " + serialKiller.getWeaponName() + ": " + serialKiller.getWeaponDescription() + " and deals " + killerDamage + " damage.");
            }
            System.out.println("You attacked " + serialKiller.getName() + " with " + selectedWeapon.getName() +
                    " and dealt " + damageDealt + " damage.");
        }

        if (!serialKiller.isAlive()) {
            System.out.println("You defeated " + serialKiller.getName() + "!");
            enemies.remove(serialKiller);
        } else {
            System.out.println(serialKiller.getName() + " has defeated you.");
            // Handle game over or other logic when the player loses
        }
    }

    public void triggerGhostEncounter(Player player) {
        Weapon weapon = new Weapon("Devils Trident", "a weapon of mystical energies.", 10, 10); // Create a weapon
        Enemy.Ghost ghost = new Enemy.Ghost("Ghost", 100, weapon); // Create a ghost

        System.out.println("You enter the attic and encounter " + ghost.getName() + "!");
        System.out.println("You can use your weapons to interact with " + ghost.getName() + ".");

        while (ghost.isAlive() && player.isAlive()) {
            // Let the player choose a weapon to use (you can implement a method for this)
            Weapon selectedWeapon = player.chooseWeapon();

            // Calculate and apply damage
            int damageDealt = selectedWeapon.calculateDamage();
            ghost.takeDamage(damageDealt);

            if (ghost.isAlive()) {
                // Enemy.Ghost attacks back (you can implement their attack logic)
                int ghostDamage = ghost.attack();
                player.takeDamage(ghostDamage);

                //Take ammo from weapon
                selectedWeapon.useAmmunitionIfAny();

                System.out.println(ghost.getName() + " attacks you with " + ghost.getWeaponName() + ": " + ghost.getWeaponDescription() + " and deals " + ghostDamage + " damage.");
            }

            System.out.println("You attacked " + ghost.getName() + " with " + selectedWeapon.getName() +
                    " and dealt " + damageDealt + " damage.");
        }

        if (!ghost.isAlive()) {
            System.out.println("You banished " + ghost.getName() + "!");
            enemies.remove(ghost);
        } else {
            System.out.println(ghost.getName() + " has defeated you.");
            // Handle game over or other logic when the player loses
        }
    }



    public boolean hasExit(String exitKey) {
        return exits.containsKey(exitKey);
    }

    public boolean hasEnemy() {
        return !enemies.isEmpty();
    }

    public boolean hasEnemyGhost() {
        // Check if enemy is ghost:
        for (Enemy enemy : enemies) {
            if (enemy.getName() == "Ghost") {
                return true;
            }
        }
        return false;
    }

    public boolean hasEnemySerialKiller() {
        // Check if enemy is ghost:
        for (Enemy enemy : enemies) {
            if (enemy.getName() == "Serialkiller") {
                return true;
            }
        }
        return false;
    }



    public Enemy getEnemy() {
        return enemy;
    }

    
    public void removeWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addFood(Food food) {
        foodItems.add(food);
    }


    public Item removeItem(String itemName) {
        // Iterate through items in the room
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName() != null && item.getName().equalsIgnoreCase(itemName)) {
                iterator.remove(); // Remove the item from the room
                return item;      // Return the removed item
            }
        }
        return null; // Item not found

    }
}
