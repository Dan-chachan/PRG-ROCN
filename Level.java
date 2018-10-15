class Level {
    public String[][] data;
    private int size = 11; // Musi byt vzdy liche cislo

    public Level() {
        this.data = new String[this.size][this.size];
    }

    public int getSize() {
        return this.size;
    }

    public int getHumanReadableSize() {
        return this.size-1;
    }

    public String[][] getData() {
        return this.data;
    }
}