public class GamerField {
    private int size;
    private int[][] field1 ;
    private String name;
    GamerField(int size, String name)
    {
        this.size = size;
        field1 = new int[size][size];
        this.name = name;

    }
    void buildGamerField(int[][] enemyField)
    {
        for(int i = 0; i < field1.length; i++)
        {
            for(int j = 0; j < field1[i].length; j++)
            {
                field1[i][j] = enemyField[i][j];
            }
        }
    }
    void ShowField()
    {
        System.out.print("\n " + name + "\n" + "   ");
        for(int i = 0 ; i < 10; i++)
        {
            System.out.print(i + "  ");
        }
        System.out.print("\n");
        for(int i = 0; i < field1.length; i++)
        {
            System.out.print(i + "  ");
            for(int j = 0; j < field1[i].length; j++)
            {
                if(field1[i][j] == 2)// если попали
                {
                    System.out.print("x  ");
                }
                else if(field1[i][j] == 3){// если промахнулись
                    System.out.print(".  ");
                }
                else{// если там стоит 1(корабль) или 0 (ничего)
                    System.out.print("-  ");
                }

            }
            System.out.println();
        }
    }
    // 1 - стоит не тронутый корабль
    // 2 - стоял корабль но мы попали
    // 0 - ничего не стоит и мы не стреляли
    // 3 - промахнулись
    boolean CheckGamerGuess(int x , int y)
    {
        if(field1[x][y] == 1)
        {
            System.out.println("Hit !");
            field1[x][y] = 2;
            return true;
        }
        else if (field1[x][y] == 0){
            field1[x][y] = 3;
            System.out.println("Sorry , try again )");
            return false;
        }
        else{// если мы уже попали в корабль и там стоит 2 или если мы уже промахнулись и там стоит 3
            System.out.println("Sorry , try again )");
            return false;
        }
    }
}
