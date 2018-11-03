import java.util.ArrayList;

class Inventory {

    public ArrayList<Item> content = new ArrayList<>();
    private final int DEF_SIZE = 15;
    public int size;

    public Inventory() {
        this.size = DEF_SIZE;

        // Vyplneni
        for (int i = 0; i < this.size; i++) {
            this.content.add(new Item());
            System.out.println(i);
        }
    }

    public void acquire(Item i) {
        if (this.content.size() < this.size) {
            this.content.add(i);
        }
        else {
            System.out.println("Inventory full!");
        }
    }

    // Odstrani predmet z inventare a vrati ho do sveta
    public Item drop(Item i) {
        this.content.remove(i);
        return i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        int itemsPerLine = 3;
        int n = content.size();
        for (int i=0; i<n; i+=itemsPerLine) {

            sb.append("||");

            for (int j=0; j<itemsPerLine; j++) {
                
                int row = i / itemsPerLine;
                int col = j; //(n / itemsPerLine);
                
                int index = row + col * (n/itemsPerLine);

                sb.append(String.format(" [%2d] %-16s |", 
                            (index+1), content.get(i+j)));
            }

            sb.append("|\n");
        }

        return sb.toString();
    }

}