import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {


        Problem problem = new Problem("a_example.txt");

        Library l = problem.getLibraries().get(0);

        l.getBooks().add(new Book(12,90));
        System.out.println(l);


    }
}
