public class Game {
    private int ShipsCount;
    private EnemyField enemyField ;
    private GamerField myField;
    private Gamer gamer;
    Game()// по умолчанию в классической игре 10 кораблей и поле 10 на 10
    {
        ShipsCount = 10;
        enemyField = new EnemyField(10,"enemyField");
        myField = new GamerField(10,"gamerField");
        gamer  = new Gamer();
    }

    void BeforeGame()
    {
        System.out.println("Game was started >>>");
        System.out.println("Welcome to new Battle Ship " + gamer.name + "!");
        enemyField.setShips(ShipsCount);
        myField.buildGamerField(enemyField.GetEnemyField());
        enemyField.ShowField();
        myField.ShowField();
    }
    void StartGame()
    {
        int attempts = gamer.attemptsNum;
        while(gamer.attemptsNum > 0)
        {
            System.out.println("Enter coordinates of the target >>> ");
            gamer.makeGuess();
            if(myField.CheckGamerGuess(gamer.GetX(),gamer.GetY()))
            {
                gamer.increaseMark();
            }
            myField.ShowField();
        }
        System.out.println("Your number of attempts is expired ((((");
        System.out.println("______________GAME OVER _______________");
        System.out.println("Your results : " + (float)(gamer.GetScore()/attempts) * 100 );
    }

}