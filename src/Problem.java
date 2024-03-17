import javax.sound.sampled.Port;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class Problem {
    private ArrayList<Book> Books;
    private ArrayList<Library> Libraries;
    private int ProcessDays;
    private int numberOfBooks;
    private int numberOfLibraries;

    private String Filename;

    Problem(int processDays,ArrayList<Book> books, ArrayList<Library> libraries){
        this.ProcessDays = processDays;
        this.Books = books;
        this.Libraries = libraries;

        this.numberOfBooks = this.Books.size();
        this.numberOfLibraries = this.Libraries.size();
    }

    Problem(String fileName){

        ArrayList<String> content= new ArrayList<>();

        this.Filename = fileName;

        try {

            content= readFromFile(fileName);
        } catch (IOException e) {

            e.printStackTrace();
        }

        String[] generalInformationLine = content.get(0).split(" ");

        String[] booksScores = content.get(1).split(" ");

        ArrayList<Book> bookArrayList = doBooks(booksScores);

        ArrayList<Library> libraries = doLibraries(content,Integer.parseInt(generalInformationLine[1]), bookArrayList);




        this.ProcessDays = Integer.parseInt(generalInformationLine[2]);
        this.Books = bookArrayList;
        this.Libraries = libraries;

        this.numberOfBooks = this.Books.size();
        this.numberOfLibraries = this.Libraries.size();


    }


    public ArrayList<Book> getBooks() {
        return Books;
    }

    public void setBooks(ArrayList<Book> books) {
        Books = books;
    }

    public ArrayList<Library> getLibraries() {
        return Libraries;
    }

    public void setLibraries(ArrayList<Library> libraries) {
        Libraries = libraries;
    }

    public int getProcessDays() {
        return ProcessDays;
    }

    public void setProcessDays(int processDays) {
        ProcessDays = processDays;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public int getNumberOfLibraries() {
        return numberOfLibraries;
    }

    public void setNumberOfLibraries(int numberOfLibraries) {
        this.numberOfLibraries = numberOfLibraries;
    }


    @Override
    public String toString() {
        return "Problem: " +
                "\nProcessDays=" + ProcessDays +
                "\nnumberOfBooks=" + numberOfBooks +
                "\nnumberOfLibraries=" + numberOfLibraries +
                "\n\nBooks=" + Books +
                "\n\nLibraries=" + Libraries +

                '}';
    }

    public int getMaxScore(){
        int score=0;
        for(Book b: this.Books){
            score+=b.getScore();
        }

        return score;
    }





    private static ArrayList<String> readFromFile(String filePath) throws IOException {

        ArrayList<String> c=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;


            while ((line = br.readLine()) != null) {
                c.add(line);
            }
        }

        return c;
    }


    private static ArrayList<Book> doBooks(String[] b){

        ArrayList<Book> books = new ArrayList<>();
        for(int i=0;i<b.length;i++){


            books.add(new Book(i,Integer.parseInt(b[i])));
        }

        return books;
    }

    private static ArrayList<Library> doLibraries(ArrayList<String> content,int libszie,ArrayList<Book> bookArrayList){

        ArrayList<Library> libraries = new ArrayList<>();

        for(int i=0;i<libszie;i++){



            String[] libraryInfo = content.get(i*2+2).split(" ");
            String[] libraryBooks = content.get(i*2+3).split(" ");


            libraries.add(new Library(String.valueOf(i),libraryInfo,libraryBooks,bookArrayList));



        }


        return libraries;

    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        this.Filename = filename;
    }
}
