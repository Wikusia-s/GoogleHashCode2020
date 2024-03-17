public class Book {
    private int ID;
    private int Score;


    Book(int id,int score){
        this.ID = id;
        this.Score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    /*
    @Override
    public String toString() {
        return "\nBook{" +
                "ID=" + ID +
                ", Score=" + Score +
                '}'+"";
    }*/


    @Override
    public String toString() {
        return "\t"+
                "Book_ID=" + ID+", ";
    }



}
