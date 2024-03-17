import javax.sound.sampled.Port;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiPredicate;

public class Main {

    public static Problem problem;

    public static void main(String[] args) {
        String a = "a_example.txt";
        String b = "b_read_on.txt";
        String c = "c_incunabula.txt";
        String d = "d_tough_choices.txt";
        String e = "e_so_many_books.txt";
        String f = "f_libraries_of_the_world.txt";

        ArrayList<String> files = new ArrayList<>();

        files.add(a);
        files.add(b);
        files.add(c);
        files.add(d);
        files.add(e);
        files.add(f);





        GreedyApproach greedyApproach = new GreedyApproach();

        for(String s:files){
            problem = new Problem(s);
            System.out.println(s+"\nScore: "+greedyApproach.BasicGreedy(problem));
        }



    }















}