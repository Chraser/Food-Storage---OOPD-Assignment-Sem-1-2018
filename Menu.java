/************************************************************
 *Name: Kay Men Yap
 *File name: Menu.java
 *Purpose: Handles menu operations
 *Date last modified: 30/5/2018
 ************************************************************/
public class Menu
{
    /************************************************************
     *SUBMODULE: run
     *IMPORT: none    
     *EXPORT: none
     *PURPOSE: Keeps running the program and performs the option
     *         selected by the user until exit option is selected
     ************************************************************/
    public static void run() 
    {
        boolean storageExist = false;
        boolean runProgram = true;
        Storage storage = new Storage();
        String readFileName, writeFileName, storageFileString;
        int optionSelected;
        do
        {
            optionSelected = UserIO.getMenuSelection();
            switch(optionSelected)
            {
                case 1:
                    if (storageExist)
                    {
                        addFood(storage);
                    }
                    else
                    {
                        System.out.println("ERROR: Storage facility does not"
                                           + " exist yet. Can't add food.");
                    }
                    break;
                case 2:
                    if (storageExist)
                    {
                        removeFood(storage);
                    }
                    else
                    {
                        System.out.println("ERROR: Storage facility does not "
                                            + "exist yet.  Can't remove food.");
                    }
                    break;
                case 3:
                    if (storageExist)
                    {
                        UserIO.displayContents(storage);
                    }
                    else
                    {
                        System.out.println("ERROR: Storage facility does not"
                                           + " exist yet. Can't display "
                                           + "content of food.");
                    }
                    break;
                
                
                
                
                
                
                
                
                case 4:
                    if (storageExist)
                    {
                        UserIO.findExpired(storage);
                    }
                    else
                    {
                        System.out.println("ERROR: Storage facility does not"
                                           + " exist yet. Can't find"
                                           + " expired food.");
                    }
                    break;
                case 5:
                    readFileName = UserIO.stringInput("Enter file name"
                                                             + " to be read");
                    try
                    {
                        storage = new Storage(readFileName);
                        storageExist = true;
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    if (storageExist)
                    {
                        writeFileName = UserIO.stringInput("Enter file name to"
                                                           + " be written to");
                        storageFileString = storage.toFileString();
                        FileIO.writeOutStorage(storageFileString, writeFileName);
                    }
                    else
                    {
                        System.out.println("ERROR: Storage facility does not "
                                           + "exist yet. " + "Can't write out"
                                           + "storage to file");
                    }
                    break;
                default:
                    runProgram = false;
                    System.out.println("Program exitted");
            }
        }while(runProgram);
    }
    
    /************************************************************
     *SUBMODULE: addFood
     *IMPORT: storage(Storage)
     *EXPORT: none
     *ASSERTION: a valid food object is added to storage after 
     *           user input
     ************************************************************/
    public static void addFood(Storage storage)
    {
        String name, cut, packaging, type, error;
        int day, month, year, numPieces, foodType;
        double storageTemp, weight, volume;
        Food food;
        foodType = UserIO.integerInput("Enter food type: \n" + "1. Meat \n"
                                           + "2. Grain \n" + "3. Fruit \n"
                                           + "4. Vegetable", 1, 4);
        error = "";
        switch(foodType)
        {
            
            
            
            case 1:
                name = UserIO.stringInput("Enter name of meat");
                cut = UserIO.stringInput("Enter cut of meat");
                weight = UserIO.realInput("Enter weight of meat", 
                                           Meat.MINWEIGHT, Meat.MAXWEIGHT);
                storageTemp = UserIO.storageTempInput("meat");
                do
                {
                    System.out.println(error);
                    year = UserIO.integerInput("Enter year of Use by"
                                               + " date:", 2000, 3000);
                    month = UserIO.integerInput("Enter month of Use by"
                                                + " date:", 1, 12);
                    day = UserIO.integerInput("Enter day of Use by"
                                              + " date:", 1, 
                                              UserIO.daysInMonth(month, year));
                    error = "Enter a use by date that is not in the past";
                }while (!(UserIO.validateDateMark(day, month, year)));
                packaging = UserIO.stringInput("Enter packaging of meat");
                food = new Meat(name, storageTemp, packaging, day, month, 
                                year, cut, weight);
                break;
            case 2:
                name = UserIO.stringInput("Enter name of grain");
                type = UserIO.stringInput("Enter type of grain");
                volume = UserIO.realInput("Enter volume of grain",
                                            Grain.MINVOLUME, Grain.MAXVOLUME);
                storageTemp = UserIO.storageTempInput("grain");
                do
                {
                    System.out.println(error);
                    year = UserIO.integerInput("Enter year of best before"
                                               + " date:", 2000, 3000);
                    month = UserIO.integerInput("Enter month of best before"
                                                + " date:", 1, 12);
                    day = UserIO.integerInput("Enter day of best before"
                                              + " date:", 1, 
                                              UserIO.daysInMonth(month, year));
                    error = "Enter a best before date that is not in the past";
                }while (!(UserIO.validateDateMark(day, month, year)));
                packaging = UserIO.stringInput("Enter packaging of grain");
                food = new Grain(name, storageTemp, packaging, day, month, 
                                 year, type, volume);
                break;
            case 3:
                name = UserIO.stringInput("Enter name of fruit");
                type = UserIO.stringInput("Enter type of fruit");
                numPieces = UserIO.integerInput("Enter number of pieces of "
                                                + "fruit", Fruit.MINNUMPIECES,
                                                Fruit.MAXNUMPIECES);
                storageTemp = UserIO.storageTempInput("fruit");
                do
                {
                    System.out.println(error);
                    year = UserIO.integerInput("Enter year of Use by"
                                               + " date:", 2000, 3000);
                    month = UserIO.integerInput("Enter month of Use by"
                                                + " date:", 1, 12);
                    day = UserIO.integerInput("Enter day of Use by"
                                              + " date:", 1, 
                                              UserIO.daysInMonth(month, year));
                    error = "Enter a use by date that is not in the past";
                }while (!(UserIO.validateDateMark(day, month, year)));
                packaging = UserIO.stringInput("Enter packaging of fruit");
                food = new Fruit(name, storageTemp, packaging, day, month, 
                                 year, type, numPieces);
                break;
            
            
            default:
                name = UserIO.stringInput("Enter name of vegetable");
                weight = UserIO.realInput("Enter weight of vegetable",
                                          Vegetable.MINWEIGHT, 
                                          Vegetable.MAXWEIGHT);
                storageTemp = UserIO.storageTempInput("vegetable");
                do
                {
                    System.out.println(error);
                    year = UserIO.integerInput("Enter year of best before"
                                               + " date:", 2000, 3000);
                    month = UserIO.integerInput("Enter month of best before"
                                                + " date:", 1, 12);
                    day = UserIO.integerInput("Enter day of best before"
                                              + " date:", 1, 
                                              UserIO.daysInMonth(month, year));
                    error = "Enter a best before date that is not in the past";
                }while (!(UserIO.validateDateMark(day, month, year)));
                packaging = UserIO.stringInput("Enter packaging of vegetable");
                food = new Vegetable(name, storageTemp, packaging, day, month, 
                                     year, weight);
        }
        storage.addFood(food);
    }

    /************************************************************
     *SUBMODULE: removeFood
     *IMPORT: storage(Storage)
     *EXPORT: none
     *ASSERTION: a food object is removed from storage after user
     *           inputs a storage area and locaiton
     ************************************************************/
    public static void removeFood(Storage storage)
    {
        String area;
        int max, storageArea, storageLocation;
        storageArea = UserIO.integerInput("Enter storage area to be displayed" 
                                          + "\n" + "1. Freezer" + "\n"
                                          + "2. Fridge" + "\n" + "3. Pantry",
                                          1, 3);
        switch(storageArea)
        {
            case 1:
                area = "freezer";
                max = storage.getNumFreezer();
                break;
            case 2:
                area = "fridge";
                max = storage.getNumFridge();
                break;
            default:
                area = "pantry";
                max = storage.getNumPantry();
        }
        storageLocation = UserIO.integerInput("Enter location in "
                                              + area, 1, max);
        storage.removeFood(storageArea, storageLocation);
        System.out.println("Food has been removed");
    }
}