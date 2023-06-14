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
                if(field1[i][j] == 2)
                {
                    System.out.print("x  ");
                }else{
                    System.out.print("-  ");
                }
            }
            System.out.println();
        }
    }
    boolean CheckGamerGuess(int x , int y)
    {
        if(field1[x][y] == 1)
        {
            System.out.println("Hit !");
            field1[x][y] = 2;
            return true;
        }else{
            System.out.println("Sorry , try again )");
            return false;
        }
    }
}
