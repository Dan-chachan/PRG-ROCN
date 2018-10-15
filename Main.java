class Main {
    public static void main(String[] args) {
        Dungeon dung = new Dungeon();
        
        Level level = dung.generateLevel();
        dung.prettyPrint(level);
    }
}