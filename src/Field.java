import java.util.Random;

public class Field {
    private int size;
    private int[][] field ;
    private String name;
    private int Direction;

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
            int ShipsNumberOfEachKind = 1;
            for(int decksCount  = 4; decksCount > 0; decksCount--)// number of ships models
            {
                /////////ОШИБКА !!!!!!!!!!!!!! БЕСКОНЕЧНЫЙ ЦИКЛ !!!!!!!
                for(int i = ShipsNumberOfEachKind ; i > 0; i--)// number of ships of one model
                {
                    boolean isShipSet = false;
                    int x,y;
                    while(!isShipSet)
                    {
                        // generate random values [0,9] for x and y coordinates of initial point of our ship
                        x = rand.nextInt(10);//rows
                        y = rand.nextInt(10);//columns
                        System.out.print("x = " + x + " y = " + y + "\n");
                        // проверка ///////////////////////
                        if(isNoShipNear(x,y))// если клетка не занята ,нет ли кораблей поблизости
                        {
                            if(decksCount != 1)// если у нас не однопалубный корабль
                            {
                                // выбор направления ///////////(сначала сделаем их прямыми  то есть корабль будет расти в одну сторону )
                                // но далее можно сделать цикл с постоянным выбором напрвления так что корабль может расти в разных направлениях
                                if(isDirectionFound(x,y,decksCount))
                                {
                                    // устанавливаем корабль
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
                                field[x][y] = 1;
                            }
                        }
                    }

                }
                ShipsNumberOfEachKind++;
                System.out.println();
            }
            // выведем полученное поле на экран
            ShowField();
        }else {
            // если игра не классическая ?
        }

    }
    private boolean isNoShipNear(int x,int y)// x - rows , y - columns
    {
        if(x != 0 && y != 0 && x != field.length - 1 && y != field.length - 1)// если это не границы поля
        {
            return (field[x + 1][y + 1] == 0 && field[x][y + 1] == 0 && field[x][y-1] == 0 &&
                    field[x - 1][y + 1] == 0 && field[x - 1][y] == 0 && field[x + 1][y] == 0 &&
                    field[x + 1][y - 1] == 0 && field[x - 1][y-1] == 0 && field[x][y] == 0);
        }else{
            return false ; ///////////////////////
        }

    }
    // функция генерирует напрвление ,
    // проверяет уместностность постановки корабля
    // если же корабль установить невозможно в выбранном направлении , меняет направление
    // если же с данными координатами невозможно посторить корабль во всех направлениях , то
    // возвращает false и уже в функции setShips мы меняем сами координаты
    private boolean isDirectionFound(int x,int y, int decksNumber)
    {
        boolean directionChecked = false;
        boolean isSpaceFree = false;
        while(!directionChecked)
        {
            // generate direction
            // 0 - top , 1 - right , 2 - bottom , 3 - left
            int direction  = rand.nextInt(4);
            System.out.println("direction is " + direction);

            switch(direction)
            {
                case 0:
                    // two type of checking : arrays bounds and near ships
                    // arrays bound
                   if(x >= decksNumber - 1)
                   {
                       // checking ships around

                       for(int i = 0; i < decksNumber; i++)
                       {
                           isSpaceFree = isNoShipNear(x - i, y);// передаем каждую палубу на проверку
                           if(!isSpaceFree)// если в какой-то из палуб проблема то выход и поиск нового направления
                           {
                              break;
                           }
                           if(i == decksNumber - 1)// если все таки мы достигли конца цикла не выйдя досрочно, направление удачное
                           {
                               directionChecked = true;
                               System.out.println("Direction is successfully found !!! ");
                               Direction = direction;
                           }
                       }

                   }
                    break;
                case 1:
                    // arrays bound
                    if( y <= field.length - decksNumber)
                    {
                        // checking ships around

                        for(int i = 0; i < decksNumber; i++)
                        {
                            isSpaceFree = isNoShipNear(x, y + i);// передаем каждую палубу на проверку
                            if(!isSpaceFree)// если в какой-то из палуб проблема то выход и поиск нового направления
                            {
                                break;
                            }
                            if(i == decksNumber - 1)// если все таки мы достигли конца цикла не выйдя досрочно, направление удачное
                            {
                                directionChecked = true;
                                System.out.println("Direction is successfully found !!! ");
                                Direction = direction;
                            }
                        }

                    }
                    break;
                case 2:
                    if(x <= field.length - decksNumber)
                    {
                        // checking ships around

                        for(int i = 0; i < decksNumber; i++)
                        {
                            isSpaceFree = isNoShipNear(x + i, y);// передаем каждую палубу на проверку
                            if(!isSpaceFree)// если в какой-то из палуб проблема то выход и поиск нового направления
                            {
                                break;
                            }
                            if(i == decksNumber - 1)// если все таки мы достигли конца цикла не выйдя досрочно, направление удачное
                            {
                                directionChecked = true;
                                System.out.println("Direction is successfully found !!! ");
                                Direction = direction;
                            }
                        }

                    }
                    break;
                case 3:
                    if(y >= decksNumber - 1)
                    {
                        // checking ships around

                        for(int i = 0; i < decksNumber; i++)
                        {
                            isSpaceFree = isNoShipNear(x , y - i );// передаем каждую палубу на проверку
                            if(!isSpaceFree)// если в какой-то из палуб проблема то выход и поиск нового направления
                            {
                                break;
                            }
                            if(i == decksNumber - 1)// если все таки мы достигли конца цикла не выйдя досрочно, направление удачное
                            {
                                directionChecked = true;
                                System.out.println("Direction is successfully found !!! ");
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
