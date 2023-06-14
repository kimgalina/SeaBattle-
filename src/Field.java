import java.util.Random;

public class Field {
    private int size;
    private int[][] field ;
    private String name;
    private int Direction;
    private int FieldEnd ;
    Field(int size,String name)
    {
        this.size = size;
        System.out.println("Generating virtual field ...");
        field = new int[size][size];
        this.name = name;
        FieldEnd = field.length - 1;
    }
    void ShowField()
    {
        System.out.print("\n" + name + "\n" + "   ");
        for(int i = 0 ; i < 10; i++)
        {
            System.out.print(i + "  ");
        }
        System.out.print("\n");
        for(int i = 0; i < field.length; i++)
        {
            System.out.print(i + "  ");
            for(int j = 0; j < field[i].length; j++)
            {
                if(field[i][j] == 0)
                {
                    System.out.print("-  ");
                }
                //System.out.print(field[i][j] + "  ");
                else{
                    System.out.print("x  ");
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    void setShips(int ShipsCount)
    {
        int ShipsNumberOfEachKind = 1;// we have only one ship with 4 decks
        for(int decksCount  = 4; decksCount > 0; decksCount--)// number of decks
        {

            for(int shipsNumOfOneKind = ShipsNumberOfEachKind ; shipsNumOfOneKind > 0; shipsNumOfOneKind--)// number of ships of one model
            {
                boolean isShipSet = false;
                int x,y;
                while(!isShipSet)
                {
                    // generate random values [0,9] for x and y coordinates of initial point of our ship
                    // random value [a,b)      (int) Math.random() * (b - a) + a
                    x = (int) (Math.random() * field.length);//rows
                    y = (int) (Math.random() * field.length);//columns
                    //System.out.print("x = " + x + " y = " + y + "\n");
                    // checking
                    if(isNoShipNear(x,y))//if there are any other ships or deck is already occupied
                    {
                        if(decksCount > 1)// if more than  one deck
                        {
                            if(isDirectionFound(x,y,decksCount))
                            {
                                // Setting ship
                                switch(Direction)
                                {
                                    case 0 :
                                        for(int k = 0; k < decksCount; k++)
                                        {
                                            field[x - k][y] = 1;
                                        }
                                        break;
                                    case 1:
                                        for(int k = 0; k < decksCount; k++)
                                        {
                                            field[x][y + k] = 1;
                                        }
                                        break;
                                    case 2:
                                        for(int k = 0; k < decksCount; k++)
                                        {
                                            field[x + k][y] = 1;
                                        }
                                        break;
                                    case 3:
                                        for(int k = 0; k < decksCount; k++)
                                        {
                                            field[x][y - k] = 1;
                                        }
                                        break;
                                }
                                isShipSet = true;
                            }
                        }
                        else{
                            //System.out.println("Direction is successfully found !!! ");
                            field[x][y] = 1;
                            isShipSet = true;
                        }
                    }
                }

            }
            ShipsNumberOfEachKind++;
            System.out.println();
        }
        // output the result look of the field
        ShowField();
    }
    private boolean isNoShipNear(int x,int y)// x - rows , y - columns
    {
        if(x != 0 && y != 0 && x != field.length - 1 && y != field.length - 1)// if it is not bounds of the array
        {
            return (field[x + 1][y + 1] == 0 && field[x][y + 1] == 0 && field[x][y-1] == 0 &&
                    field[x - 1][y + 1] == 0 && field[x - 1][y] == 0 && field[x + 1][y] == 0 &&
                    field[x + 1][y - 1] == 0 && field[x - 1][y-1] == 0 && field[x][y] == 0);
        }
        else{
            //horizontally
            if(y < FieldEnd && y >= 1)
            {
                if(field[x][y - 1] == 0 && field[x][y + 1] == 0 && field[x][y] == 0)
                {
                    if(x == 0)
                    {
                        return (field[x + 1][y + 1] == 0 && field[x + 1][y - 1] == 0 && field[x + 1][y] == 0);
                    }
                    else if(x == FieldEnd)
                    {
                        return(field[x - 1][y + 1] == 0 && field[x - 1][y - 1] == 0 && field[x - 1][y] == 0);
                    }
                }else{
                    return false;
                }
            }
            // vertically
           if(x < FieldEnd && x >= 1)
            {
                if(field[x + 1][y] == 0 && field[x - 1][y] == 0 && field[x][y] == 0)
                {
                    if(y == 0)
                    {
                        return (field[x - 1][y + 1] == 0 && field[x + 1][y + 1] == 0 && field[x][y + 1] == 0);
                    }
                    else if(y == FieldEnd)
                    {
                        return (field[x - 1][y - 1] == 0 && field[x + 1][y - 1] == 0 && field[x][y - 1] == 0);
                    }
                }else{
                    return false;
                }
            }
           return false;

        }
    }
    // the function generates a direction,
    // checks if the ship is placed appropriately
    // if the ship cannot be installed in the selected direction, it changes direction
    // if with the given coordinates it is impossible to build a ship in all directions, then
    //  returns false and already in the setShips function we change the coordinates themselves
    private boolean isDirectionFound(int x,int y, int decksNumber)
    {
        boolean directionChecked = false;
        int directionNumber = 4;
        while(!directionChecked)
        {
            // generate direction
            // 0 - top , 1 - right , 2 - bottom , 3 - left
            int direction  = (int)(Math.random()*directionNumber);
            //System.out.println("direction is " + direction);

            switch(direction)
            {
                case 0:
                    // two type of checking : arrays bounds and near ships
                    // arrays bound
                   if(x >= decksNumber - 1)
                   {
                       for(int i = 0; i < decksNumber; i++)
                       {
                           directionChecked = isNoShipNear(x - i, y);// we pass each deck for verification
                           if(!directionChecked)// if there is a problem in one of the decks, then exit and search for a new direction
                           {
                               directionChecked = false;
                               break;
                           }
                           if(i == decksNumber - 1)// if we still reached the end of the loop without exiting with break, the direction is successful
                           {
                               //System.out.println("Direction is successfully found !!! ");
                               Direction = direction;
                           }
                       }

                   }
                    break;
                case 1:
                    // arrays bound
                    if( y <= field.length - decksNumber)
                    {
                        for(int i = 0; i < decksNumber; i++)
                        {
                            directionChecked = isNoShipNear(x, y + i);
                            if(!directionChecked)
                            {
                                directionChecked = false;
                                break;
                            }
                            if(i == decksNumber - 1)
                            {
                                //System.out.println("Direction is successfully found !!! ");
                                Direction = direction;
                            }
                        }

                    }
                    break;
                case 2:
                    if(x <= field.length - decksNumber)
                    {
                        for(int i = 0; i < decksNumber; i++)
                        {
                            directionChecked = isNoShipNear(x + i, y);
                            if(!directionChecked)
                            {
                                directionChecked = false;
                                break;
                            }
                            if(i == decksNumber - 1)
                            {
                                //System.out.println("Direction is successfully found !!! ");
                                Direction = direction;
                            }
                        }

                    }
                    break;
                case 3:
                    if(y >= decksNumber - 1)
                    {
                        for(int i = 0; i < decksNumber; i++)
                        {
                            directionChecked = isNoShipNear(x , y - i );
                            if(!directionChecked)
                            {
                                directionChecked = false;
                                break;
                            }
                            if(i == decksNumber - 1)
                            {
                                //System.out.println("Direction is successfully found !!! ");
                                Direction = direction;
                            }
                        }

                    }
                    break;
            }
        }
        return true;
    }
}
