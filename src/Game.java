import java.util.Scanner;

public class Game {
    private int ShipsCount;
    private Field enemyField ;
    private Field myField;
    private Gamer gamer;
    Game()// по умолчанию в классической игре 10 кораблей и поле 10 на 10
    {
        ShipsCount = 10;
        enemyField = new Field(10,"enemyField");
        myField = new Field(10,"myField");
        gamer  = new Gamer();
    }

    void StartGame()
    {
        System.out.println("Game was started >>>");
        System.out.println("Welcome to new Battle Ship " + gamer.name + "!");
        enemyField.ShowField();
        myField.ShowField();
        enemyField.setShips(ShipsCount);


    }

}
