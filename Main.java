import java.util.Scanner;

class Main {

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // ~~~~~~~~~~~~DUNGEON INIT~~~~~~~~~~~~~~
        Dungeon dung = new Dungeon();
        // ~~~~~~~~~~~~/DUNGEON INIT\~~~~~~~~~~~~


        CliControl cliControl = new CliControl(dung);

    }
}