package pl.edu.agh.to.mosti.fetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserDataProvider {

    //pobranie danych

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scan = new Scanner(System.in);
        boolean add = true;
        List<String> lista = new ArrayList<String>();
        Fetcher fetcher = new FetchStatic();

        System.out.print("Please enter the URL: ");
        String URL = scan.nextLine();

        while(add == true){
            System.out.print("Please enter the selector: ");
            lista.add(scan.nextLine());
            System.out.print("Do you want to add more selectors? [yes/no]");
            String add_case = scan.nextLine();
            if(add_case.equals("no")){
                add = false;
            }
        }

        System.out.print("Please enter the refresh time(seconds): ");
        int Wait = scan.nextInt();

        PageData page = new PageData(URL, lista, Wait);
        fetcher.fetch(page);
    }

}
