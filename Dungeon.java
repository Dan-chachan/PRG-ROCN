import java.util.Random;
import java.util.ArrayList;

class Dungeon {
    public Level[] levels;
    private String wall = "#";
    private String empty= ".";

    public Dungeon() {
        levels = new Level[2];
    }

    public void prettyPrint(Level level) {
        String[][] data = level.getData();

        for (byte row=0; row<level.getSize(); row++) {
            for (byte col=0; col<level.getSize(); col++) {
                System.out.print(data[row][col]);
            }
            System.out.println(" ");
        }
    }
 
    // Public or private?
    public Level generateLevel() {
        Level level = new Level();
        String[][] data = level.getData();
        Random rand = new Random();
        ArrayList<int[]> baseLocations = new ArrayList<int[]>();

        // Umisteni zdi na prvni a posledni radu
        for (byte x=0; x<level.getSize(); x++) {
            data[0][x] = this.wall;
            data[level.getSize()-1][x] = this.wall;
        }

        // Umisteni zdi na prvni a posledni sloupec
        for (byte y=0; y<level.getSize(); y++) {
            data[y][0] = this.wall;
            data[y][level.getSize()-1] = this.wall;
        }
        
        // Umisteni volnych mist a "zakladu"
        for (int y=1; y<level.getSize()-1; y++) {
            for (int x=1; x<level.getSize()-1; x++) {
                data[y][x] = this.empty;

                if (y%2 == 0 && x%2 == 0) {
                    data[y][x] = "X";
                    int[] location = {x, y};
                    baseLocations.add(location);
                }
            }
        }

        // Tvorba bludiste
        while (baseLocations.size() > 0) {
            int n = rand.nextInt(baseLocations.size()-1);
            int baseX = baseLocations.get(n)[0];
            int baseY = baseLocations.get(n)[1];

            baseLocations.remove(n);

            // 0 - sever, 1 - východ, 2 - jih, 3 - západ
            int direction = rand.nextInt(3);


            if (direction == 0 || direction == 2) {

            }

            else if (direction == 2 || direction == 3)

        }
        return level;

    }
}