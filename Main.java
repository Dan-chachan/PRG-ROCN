import java.util.Scanner;

class Main {

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // ~~~~~~~~~~~~DUNGEON INIT~~~~~~~~~~~~~~
        Dungeon dung = new Dungeon();

        Level level = dung.generateLevel();
        Coords playerPos = dung.placeExits();
        // ~~~~~~~~~~~~/DUNGEON INIT\~~~~~~~~~~~~


        // ~~~~~~~~~~~~PLAYER INIT~~~~~~~~~~~~~~
        //System.out.println("What is your name?");
        //System.out.print("\n> ");
        String playName = "Pixel"; //sc.nextLine();

        Player player = new Player(playName);

        dung.placePlayer(player, playerPos);

        Dungeon.prettyPrint(level);
        System.out.println(player);
    }
}