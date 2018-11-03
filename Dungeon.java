import java.util.Random;
import java.util.ArrayList;

class Dungeon {
    static Random rand = new Random();

    public ArrayList<Level> levels;
    public Level currentLevel;

    public Player player;

    public Dungeon() {
        this.levels = new ArrayList<>();

        this.generateLevel();
    }

    public static void prettyPrint(Level level) {
        Cell[][] data = level.getData();

        for (int row=0; row < level.getSize(); row++) {
            for (int col=0; col < level.getSize(); col++) {
                String cellContent = data[row][col].toString();
                System.out.print(cellContent);
            }
            System.out.println(" ");
        }
    }

    public void generateLevel() {
        Level level = new Level();
        Cell[][] data = level.getData();
        ArrayList<int[]> baseLocations = new ArrayList<int[]>();

        
        // Umisteni zdi na prvni a posledni radu
        for (int x=0; x<level.getSize(); x++) {
            data[0][x].setContent(Content.WALL);
            data[level.getSize()-1][x].setContent(Content.WALL);;
        }

        // Umisteni zdi na prvni a posledni sloupec
        for (int y=0; y<level.getSize(); y++) {
            data[y][0].setContent(Content.WALL);
            data[y][level.getSize()-1].setContent(Content.WALL);
        }
        
        // Umisteni volnych mist a "zakladu"
        for (int y=1; y<level.getSize()-1; y++) {
            for (int x=1; x<level.getSize()-1; x++) {
                data[y][x].setContent(Content.EMPTY);

                if (y%2 == 0 && x%2 == 0) {
                    data[y][x].setContent(Content.BASE);
                    int[] location = {x, y};
                    baseLocations.add(location); // Ukladani pozic zakladu
                }
            }
        }

        // Tvorba bludiste
        int bSize = baseLocations.size();

        // Dokud nebudou vycerpany vsechny zaklady
        while (bSize > 0) {
            int coords = (bSize == 1) ? 0 : rand.nextInt(bSize-1); // Ziskani koordinantu nahodneho zakladu
 
            int x = baseLocations.get(coords)[0];
            int y = baseLocations.get(coords)[1];

            baseLocations.remove(coords); 

            // 0 - sever     1 - vychod     2 - jih      3 - zapad
            int direction = rand.nextInt(4);

            // Staveni zdi dokud se nenarazi na zed
            if (direction == 0 || direction == 2) {
                while (data[y][x].content != Content.WALL) {
                    data[y][x].setContent(Content.WALL);
                    y += (direction == 0) ? -1 : +1;
                }
            }
            else if (direction == 1 || direction == 3) {
                while (data[y][x].content != Content.WALL) {
                    data[y][x].setContent(Content.WALL);
                    x += (direction == 1) ? +1 : -1;
                }
            }

            bSize = baseLocations.size();
        }

        this.levels.add(level);
        this.currentLevel = level;
    }

    public Coords placeExits() {
        Level l = this.currentLevel;

        int exitCoords = rand.nextInt(8);
        int entranceCoords = rand.nextInt(7);
        entranceCoords = (entranceCoords == exitCoords) ? entranceCoords+1 : entranceCoords;


        int size = l.getSize();
        int[][] exits = {
            {1, 0}, {size-2, 0},            // prvni rada
            {1, size-1}, {size-2, size-1},  // posledni rada
            {0, 1}, {size-1, 1},            // druha rada
            {0, size-2}, {size-1, size-2}   // predposledni rada
        };

        int x = exits[exitCoords][0];
        int y = exits[exitCoords][1];

        l.data[y][x].setContent(Content.START);

        // Ziskani rohu, u ktereho se nachazi vchod pro umisteni hrace
        Coords corner;
        if (y <= 1) {
            if (x <= 1) {
                corner = l.TOPLEFT;
            }
            else {
                corner = l.TOPRIGHT;
            }
        }
        else {
            if (x <= 1) {
                corner = l.BOTLEFT;
            }
            else {
                corner = l.BOTRIGHT;
            }
        }

        x = exits[entranceCoords][0];
        y = exits[entranceCoords][1];

        l.data[y][x].setContent(Content.EXIT);

        return corner;
    }

    public void placePlayer(Player p, Coords coords) {
        this.currentLevel.data[coords.y][coords.x].setContent(Content.PLAYER);
        this.player = p;
        this.player.setPosition(coords);
    }
}
