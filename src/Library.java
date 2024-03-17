import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {
    private String ID;
    private int SignUpProcess;
    private int BooksPerDay;

    private ArrayList<Book> Books;
    private ArrayList<Book> ScannOrderBooks;
    private ArrayList<Book> FinalScanned;

    private int totalBooksScore;

    private double FirstRatio;

    private double SecondRatio;


    Library(String id,int signUpProcess,int booksPerDay){

        this.ID = id;
        this.SignUpProcess = signUpProcess;
        this.BooksPerDay = booksPerDay;
        this.Books = new ArrayList<>();
        this.ScannOrderBooks = new ArrayList<>();
        this.FinalScanned = new ArrayList<>();
    }


    public Library(String id,String[] information,String[] booksInfo,ArrayList<Book> bookArrayList){
        this.ID = id;
        this.SignUpProcess = Integer.parseInt(information[1]);
        this.BooksPerDay = Integer.parseInt(information[2]);

        this.ScannOrderBooks = new ArrayList<>();
        this.Books = new ArrayList<>();

        this.FinalScanned = new ArrayList<>();

        int score =0;


        for(int i=0;i<Integer.parseInt(information[0]);i++){

            score+= bookArrayList.get(Integer.parseInt(booksInfo[i])).getScore();
            this.Books.add(bookArrayList.get(Integer.parseInt(booksInfo[i])));

        }

        this.FirstRatio = score/(this.SignUpProcess + Math.ceil(this.Books.size()/this.BooksPerDay));
        this.totalBooksScore = score;

        this.SecondRatio = FirstRatio;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getSignUpProcess() {
        return SignUpProcess;
    }

    public void setSignUpProcess(int signUpProcess) {
        SignUpProcess = signUpProcess;
    }

    public int getBooksPerDay() {
        return BooksPerDay;
    }

    public void setBooksPerDay(int booksPerDay) {
        BooksPerDay = booksPerDay;
    }

    public ArrayList<Book> getBooks() {
        return Books;
    }

    public void setBooks(ArrayList<Book> books) {
        Books = books;
    }

   public  String toString(){

        //return "--Library "+this.ID+"--\nBooks: "+this.Books+"\nSignUpProcess"+this.SignUpProcess+" Days\n"+this.BooksPerDay+" per day";
        return " \n--Library "+this.ID+"--\nBooks: "+this.Books+"\nSignUpProcess: "+this.SignUpProcess+" Days\nBooks per day: "+this.BooksPerDay;
    }

    public int getScore(){

        int score=0;
        for(Book b: this.Books){
            score+=b.getScore();
        }

        return score;
    }


    public ArrayList<Book> getScannOrderBooks() {
        return ScannOrderBooks;
    }

    public void setScannOrderBooks(ArrayList<Book> scannOrderBooks) {
        ScannOrderBooks = scannOrderBooks;
    }

    public ArrayList<Book> getFinalScanned() {
        return FinalScanned;
    }

    public void setFinalScanned(ArrayList<Book> finalScanned) {
        FinalScanned = finalScanned;
    }

    public int getTotalBooksScore() {
        return totalBooksScore;
    }

    public void setTotalBooksScore(int totalBooksScore) {
        this.totalBooksScore = totalBooksScore;
    }

    public double getFirstRatio() {
        return FirstRatio;
    }

    public void setFirstRatio(double ratio) {
        this.FirstRatio = ratio;
    }

    public void RefreshSecondRatio(Set<Book> arrayList){

        int score = 0;
        int size = 0;
        for(Book b: this.Books){

            if(!arrayList.contains(b)){
                score+= b.getScore();
                size+=1;
            }



        }

        this.SecondRatio = score/(this.SignUpProcess + Math.ceil(size/this.BooksPerDay));

    }


    public double getSecondRatio() {
        return SecondRatio;
    }

    public void setSecondRatio(double secondRatio) {
        this.SecondRatio = secondRatio;
    }
}
