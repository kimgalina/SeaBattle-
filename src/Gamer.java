import java.util.Scanner;

public class Gamer {
    String name;
    Scanner console = new Scanner(System.in);
    int attemptsNum = 10;
    private float score = 0;
    private int  x ;
    private int  y;
    Gamer()
    {
        System.out.print("Enter your name >>> ");
        name = console.nextLine();
    }
    void makeGuess()
    {
        boolean isGuessAppropriate = false;
        while(!isGuessAppropriate)
        {
            x = console.nextInt();
            y = console.nextInt();
            if(x >= 0 && x < 10 && y >= 0 && y < 10){
                attemptsNum--;
                isGuessAppropriate = true;
            }
            else{
                System.out.println("Coordinates must be in interval [0,9] !");
            }
        }
    }
    int GetX()
    {
        return x;
    }
    int GetY()
    {
        return y;
    }
    void increaseScore()
    {
        score++;
    }
    float GetScore()
    {
        return score;
    }



}
