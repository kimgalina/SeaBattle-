import java.util.Random;

public class Field {
    private int size;
    private int[][] field ;
    private String name;
    Random rand = new Random(10);
    Field(int size,String name)
    {
        this.size = size;
        System.out.println("Generating virtual field ...");
        field = new int[size][size];
        this.name = name;
    }
    void ShowField()
    {
        System.out.println("\n" + name);
        for(int i = 0; i < field.length; i++)
        {
            for(int j = 0; j < field[i].length; j++)
            {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
    // fill the field with given number of ships
    // 4 ships with 1 cell

    // 3 ships with 2 cells
    // 2 ships with 3 cells
    // 1 ship with 4 cells
    void setShips(int ShipsCount)
    {
        if(ShipsCount == 10)// if it is classical version of the game
        {
            int i  = 1, ShipsNumberOfEachKind = 1;
            for(int cell  = 4; cell > 0; cell--)// number of ships models
            {
                for(i = ShipsNumberOfEachKind ; i > 0; i--)// number of ships of one model
                {
                    // generate random values [0,9] for x and y coordinates of initial point of our ship
                    int x,y;
                    x = rand.nextInt(10);
                    y = rand.nextInt(10);
                  

                }
                ShipsNumberOfEachKind++;
            }
        }else {
            // если игра не классическая ?
        }

    }

}
