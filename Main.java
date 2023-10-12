public class Main {

    public static void main(String[] args) {

        // Create instances of GameWorld and UserInterface
        GameWorld gameWorld = new GameWorld();
        UserInterface userInterface = new UserInterface(gameWorld);

        // Start the program
        userInterface.startProgram();
    }

    private static final int APPLE_NUTRITION = 10;
    private static final int SANDWICH_NUTRITION = 20;
    private static final int CAKE_NUTRITION = -15;
}