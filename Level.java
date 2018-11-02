class Level {
    public Cell[][] data;
    public static final int DEF_SIZE = 11; // Vychozi velikost
    public static final int MIN_SIZE = 7;
    private int size; // Musi byt vzdy liche cislo

    public final Coords TOPLEFT;
    public final Coords TOPRIGHT;
    public final Coords BOTLEFT;
    public final Coords BOTRIGHT;


    public Level(int size) {
        if (size < MIN_SIZE) throw new IllegalArgumentException();

        this.size = (size % 2 == 0) ? size+1 : size; // Zajisteni liche velikosti
        this.data = new Cell[this.size][this.size];

        this.TOPLEFT = new Coords(1, 1);
        this.TOPRIGHT = new Coords(size-2, 1);
        this.BOTLEFT = new Coords(1, size-2);
        this.BOTRIGHT = new Coords(size-2, size-2);

        // Vyplneni pole
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                this.data[y][x] = new Cell(x, y, Content.EMPTY);
            }
        }
    }

    public Level() {
        this(DEF_SIZE);
    }

    public int getSize() {
        return this.size;
    }

    public Cell[][] getData() {
        return this.data;
    }
}
