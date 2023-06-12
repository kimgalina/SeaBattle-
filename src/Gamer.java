import java.util.Scanner;

public class Gamer {
    String name;
    Scanner console = new Scanner(System.in);
    Gamer()
    {
        System.out.print("Enter your name >>> ");
        name = console.nextLine();
    }


}
