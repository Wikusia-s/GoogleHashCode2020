import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GreedyApproach {


    private String appraoch;

    private String filename;

    private Set<Book> ScannedBooks = new HashSet<>();



    /*
    public long NaiveGreedy(Problem problem){

        appraoch = "NaiveGreedy";
        filename = problem.getFilename();

        ScannedBooks = new HashSet<>();

        problem.setLibraries(SortLibrariesBasedOnSP(problem.getLibraries()));

        ArrayList<Library> nonSelectedOnes = problem.getLibraries();

        ArrayList<Library> selectedOnes = new ArrayList<>();


        for(int i=0;i<problem.getProcessDays();i++){


            if(!nonSelectedOnes.isEmpty()){

                if(nonSelectedOnes.get(0).getSignUpProcess()<=problem.getProcessDays()-i){

                    i+=nonSelectedOnes.get(0).getSignUpProcess()-1;

                    selectedOnes.add(nonSelectedOnes.get(0));

                    nonSelectedOnes.remove(0);

                }else{

                    break;
                }
            }else{
                break;
            }



        }



        TrimLibrariesBeforeScanningPhase(SortBooksForScanningPhase(selectedOnes), problem.getProcessDays());


        return getScoreOfBooks(ScannedBooks);






    }*/

    public long BasicGreedy(Problem problem){
        filename = problem.getFilename();

        appraoch = "BasicGreedy";


        ScannedBooks = new HashSet<>();

        problem.setLibraries(SortLibrariesBasedOnSP(problem.getLibraries()));

        ArrayList<Library> nonSelectedOnes = problem.getLibraries();

        ArrayList<Library> selectedOnes = new ArrayList<>();


        for(int i=0;i<problem.getProcessDays();i++){


            if(!nonSelectedOnes.isEmpty()){

                if(nonSelectedOnes.get(0).getSignUpProcess()<=problem.getProcessDays()-i){

                    i+=nonSelectedOnes.get(0).getSignUpProcess()-1;

                    selectedOnes.add(nonSelectedOnes.get(0));

                    nonSelectedOnes.remove(0);

                }else{

                    break;
                }
            }else{
                break;
            }



        }


        selectedOnes = SortLibrariesBasedOnRatio(selectedOnes);


        TrimLibrariesBeforeScanningPhase(SortBooksForScanningPhase(selectedOnes), problem.getProcessDays());


        return getScoreOfBooks(ScannedBooks);



    }



    public long ImprovedGreedy(Problem problem){

        filename = problem.getFilename();
        appraoch = "ImprovedGreedy";


        ScannedBooks = new HashSet<>();


        ArrayList<Library> nonSelectedOnes = SortLibrariesBasedOnRatio(problem.getLibraries());


        ArrayList<Library> selectedOnes = new ArrayList<>();


        for(int i=0;i<problem.getProcessDays();i++){


            if(!nonSelectedOnes.isEmpty()){

                if(nonSelectedOnes.get(0).getSignUpProcess()<=problem.getProcessDays()-i){

                    i+=nonSelectedOnes.get(0).getSignUpProcess()-1;

                    selectedOnes.add(nonSelectedOnes.get(0));

                    nonSelectedOnes.remove(0);

                }else{

                    break;
                }
            }else{
                break;
            }



        }


        TrimLibrariesBeforeScanningPhase(SortBooksForScanningPhase(selectedOnes), problem.getProcessDays());


        return getScoreOfBooks(ScannedBooks);
    }


    public long SmartGreedy(Problem problem){

        filename = problem.getFilename();

        appraoch = "SmartGreedy";



        ScannedBooks = new HashSet<>();


        ArrayList<Library> nonSelectedOnes = SortLibrariesBasedOnRatio(problem.getLibraries());


        ArrayList<Library> selectedOnes = new ArrayList<>();


        for(int i=0;i<problem.getProcessDays();i++){


            if(!nonSelectedOnes.isEmpty()){

                if(nonSelectedOnes.get(0).getSignUpProcess()<=problem.getProcessDays()-i){

                    i+=nonSelectedOnes.get(0).getSignUpProcess()-1;

                    selectedOnes.add(nonSelectedOnes.get(0));

                    ScannedBooks = addToIt(ScannedBooks,nonSelectedOnes.get(0).getBooks());
                    nonSelectedOnes.remove(0);



                    nonSelectedOnes = RefreshForAll(nonSelectedOnes,ScannedBooks);

                    TrimLibrariesBeforeScanningPhase(SortBooksForScanningPhase(selectedOnes), problem.getProcessDays());


                }else{

                    break;
                }
            }else{
                break;
            }



        }



        return getScoreOfBooks(ScannedBooks);
    }







    private ArrayList<Library> SortBooksForScanningPhase(ArrayList<Library> selected){


        ArrayList<Book> SelectedAlready= new ArrayList<>();
        ArrayList<Library> ss = new ArrayList<>();

        for(Library l:selected){

            ArrayList<Book> head = new ArrayList<>();
            ArrayList<Book> tail = new ArrayList<>();

            for(Book b:l.getBooks()){

                if(SelectedAlready.contains(b)){

                    tail.add(b);

                }else {

                    SelectedAlready.add(b);

                    head.add(b);
                }



            }

            head.addAll(tail);
            l.setScannOrderBooks(head);


            ss.add(l);



        }



        return ss;

    }


    private  void TrimLibrariesBeforeScanningPhase(ArrayList<Library> toBeScanned,int ProcessDays){

        String output="";
        int current_day=0;

        int x=0;


        output+=toBeScanned.size();
        for(Library l:toBeScanned){


            if(current_day+ l.getSignUpProcess() <= ProcessDays-1){

                current_day+=l.getSignUpProcess();


                l = TrimLibrary(l,current_day,ProcessDays);
                x+= l.getFinalScanned().size();

                output+="\n"+l.getID()+" "+l.getFinalScanned().size()+"\n";

                for(Book b:l.getFinalScanned()){
                    output+=b.getID()+" ";
                }



            }else {

                break;
            }






        }


        PrintResult(output,appraoch,filename);




    }

    private  Library TrimLibrary(Library library, int day,int ProcessDays){




        ArrayList<Book> LibraryFinalBooks = new ArrayList<>();

        int counter = 1;

        for(int i=0;i<library.getScannOrderBooks().size();i++){


            if(day <= ProcessDays){



                if(counter==library.getBooksPerDay()){
                    counter=1;
                    day+=1;


                }else{

                    counter+=1;


                }

                LibraryFinalBooks.add(library.getScannOrderBooks().get(i));

                ScannedBooks.add(library.getScannOrderBooks().get(i));

            }else{
                break;
            }


        }


        library.setFinalScanned(LibraryFinalBooks);


        return library;



    }


    public static long getScoreOfBooks(Set<Book> set){
        long score = 0;

        for(Book b:set){
            score+=b.getScore();
        }

        return  score;
    }




    private  ArrayList<Library> SortLibrariesBasedOnSP(ArrayList<Library> libraries){
        //ASCENDING
        Collections.sort(libraries, new Comparator<Library>() {
            @Override
            public int compare(Library library1, Library library2) {

                return Integer.compare(library1.getSignUpProcess(), library2.getSignUpProcess());
            }
        });


        return libraries;
    }


    private ArrayList<Library> SortLibrariesBasedOnRatio(ArrayList<Library> libraries){
        libraries.sort(new Comparator<Library>() {
            @Override
            public int compare(Library library1, Library library2) {

                return Double.compare(library2.getFirstRatio(), library1.getFirstRatio());
            }
        });

        return libraries;
    }

    private ArrayList<Library> SortLibrariesBasedOnRatio2(ArrayList<Library> libraries){
        libraries.sort(new Comparator<Library>() {
            @Override
            public int compare(Library library1, Library library2) {

                return Double.compare(library2.getSecondRatio(), library1.getSecondRatio());
            }
        });

        return libraries;
    }


    private ArrayList<Library> RefreshForAll(ArrayList<Library> arrayList,Set<Book> selectedBooks){
        ArrayList<Library> newones =new ArrayList<>();
        for(Library l:arrayList){
            l.RefreshSecondRatio(selectedBooks);
            newones.add(l);
        }
        return SortLibrariesBasedOnRatio2(newones);
    }

    private Set<Book> addToIt(Set<Book> set,ArrayList<Book> books){

        set.addAll(books);

        return set;
    }


    private void PrintResult(String text,String app,String filenam){


        String filePath = app+"/"+filenam;


        try {
            // Get the File object for the specified file path
            File file = new File(filePath);

            // Create the directory and its parent directories if they don't exist
            file.getParentFile().mkdirs();


            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            bufferedWriter.write(text);


            bufferedWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
