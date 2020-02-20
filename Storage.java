/************************************************************
 *Name: Kay Men Yap
 *File name: Storage.java
 *Purpose: Stores food in a 2D Array which is a freezer, fridge
           and pantry
 *Date last modified: 30/5/2018
 ************************************************************/
import java.util.GregorianCalendar;
public class Storage
{
    public static final double FREEZERMINTEMP = -27.5;
    public static final double FREEZERMAXTEMP = -5.0;
    public static final double FRIDGEMINTEMP = -2.0;
    public static final double FRIDGEMAXTEMP = 6.0;
    public static final double PANTRYMINTEMP = 8.0;
    public static final double PANTRYMAXTEMP = 25.0;
                    
    private Food[][] storageArray;
    private int numFreezer;
    private int numFridge;
    private int numPantry;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: none
     *ASSERTION: an empty storage with capacity of 10 in each
     *           storage area is valid
     ************************************************************/
    public Storage()
    {
        storageArray = new Food[3][10];
        numFreezer = 0;
        numFridge = 0;
        numPantry = 0;
    }
        
    /************************************************************
     *Alternate Constructor:
     *IMPORT: inFileName(String)
     *EXPORT: none
     *ASSERTION: Creates the object if the imports are valid and FAILS 
               otherwise
     ************************************************************/
    public Storage(String inFileName)
    {
        int numLines = FileIO.getNumLines(inFileName);
        if (numLines < 3)
        {
            throw new IllegalArgumentException("Invalid file format. Need "
                                               + "at least 3 lines in file");
        }
        int freezerSize = FileIO.getStorageSize(inFileName, "Freezer");
        int fridgeSize = FileIO.getStorageSize(inFileName, "Fridge");
        int pantrySize = FileIO.getStorageSize(inFileName, "Pantry");
        storageArray = new Food[3][];
        storageArray[0] = new Food[freezerSize];
        storageArray[1] = new Food[fridgeSize];
        storageArray[2] = new Food[pantrySize];
        String[] lineArray = new String[numLines];
        FileIO.readFile(lineArray, inFileName, numLines);
        storeFood(lineArray);
    }
        
    
    
    
    
    
    /************************************************************
     *Copy Constructor:
	 *IMPORT: inStorage (Storage)
	 *EXPORT: none
	 *ASSERTION: Creates an object with an identical object state as the
                 import.
     ************************************************************/
    public Storage(Storage inStorage)
    {
        numFreezer = inStorage.getNumFreezer();
        numFridge = inStorage.getNumFridge();
        numPantry = inStorage.getNumPantry();
        Food[][] inStorageArray = inStorage.getStorageArray();
        storageArray = new Food[3][];
        for (int row = 0; row < inStorageArray.length; row++)
        {
            storageArray[row] = new Food[inStorageArray[row].length];
            for (int column = 0; column < inStorageArray[row].length; column++)
            {
                storageArray[row][column]= inStorageArray[row][column];
            }
        }
    }

    /************************************************************
     *SUBMODULE: clone
	 *IMPORT: none
	 *EXPORT: cloneStorage(Storage)
	 *ASSERTION: Creates a new Storage object using the copy constructor
     ************************************************************/
    public Storage clone()
    {
        Storage cloneStorage;
        cloneStorage = new Storage(this);
        return cloneStorage;
    }
        
    //ACCESSORS:

    //returns a copy of storageArray
    public Food[][] getStorageArray()
    {
        Food[][] storageArrayCopy = new Food[3][];
        for (int row = 0; row < storageArray.length; row++)
        {
            storageArrayCopy[row] = new Food[storageArray[row].length];
            for (int column = 0; column < storageArray[row].length; column++)
            {
                storageArrayCopy[row][column] = storageArray[row][column].clone();
            }
        }
        return storageArrayCopy;
    }

    public int getNumFreezer()
    {
        return numFreezer;
    }

    public int getNumFridge()
    {
        return numFridge;
    }
    public int getNumPantry()
    {
        return numPantry;
    }
    
    
    /************************************************************
     *SUBMODULE: equals
     *IMPORT: inObject(Object)
     *EXPORT: isEqual(boolean)
     *ASSERTION: Two Storage are interchangeable if they have the same
     *           numFreezer, numFridge, numPantry, contents in storageArray 
     ************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if(inObject instanceof Storage)
        {
            Storage inStorage = (Storage)inObject;
            if(numFreezer == inStorage.getNumFreezer())
            {
                if(numFridge == inStorage.getNumFridge())
                {
                    if(numPantry == inStorage.getNumPantry())
                    {
                        if(arrayEquals(inStorage.getStorageArray()))
                        {
                            isEqual = true;
                        }
                    }
                }
            }
        }
        return isEqual;
    }

    /********************************************************************
     *SUBMODULE: toString
	 *IMPORT: none
	 *EXPORT: storageString (Storage)
	 *ASSERTION: creates a string that contains all of the storage information
     *           in a user friendly format
     *********************************************************************/
    public String toString()
    {
        String storageString = "";
        for (int row = 0; row < storageArray.length; row++)
        {
            switch(row)
            {
                case 0:
                    storageString = toFreezerString();
                    break;
                case 1:
                    storageString = storageString + "\n"
                                    + toFridgeString();
                    break;
                default:
                    storageString = storageString + "\n"
                                    + toPantryString();
            }
        }
        return storageString;
    }

    
    
    
    
    
    
    
    
    
    
    /********************************************************************
     *SUBMODULE: toFileString
	 *IMPORT: none
	 *EXPORT: fileString (Storage)
	 *ASSERTION: creates a string that contains all of the storage information
     *           in a file format
     *********************************************************************/
    public String toFileString()
    {
        Food food;
        String fileString = "Freezer, " + storageArray[0].length + "\n"
                            + "Fridge, " + storageArray[1].length + "\n"
                            + "Pantry, " + storageArray[2].length;
        for (int row = 0; row < storageArray.length; row++)
        {
            for (int column = 0; column < storageArray[row].length; column++)
            {
                food = storageArray[row][column];
                if (food != null)
                {
                    fileString = fileString + "\n" + food.toFileString();
                }
            }
        }
        return fileString;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //MUTATORS:
    /************************************************************
     *SUBMODULE: addFood
     *IMPORT: inFood(Food)
     *EXPORT: none
     *ASSERTION: adds food in correct storage area if not full already
                 otherwise FAILS
     ************************************************************/
    public void addFood(Food inFood)
    {
        double storageTemp = inFood.getStorageTemp();
        if (validateStorageTemp(storageTemp, FREEZERMINTEMP, FREEZERMAXTEMP))
        {
            if (numFreezer < storageArray[0].length)
            {
                storageArray[0][numFreezer] = inFood;
                numFreezer = numFreezer + 1;
            }
            else
            {
                throw new IllegalArgumentException("Freezer is full");
            }
        }
        else if (validateStorageTemp(storageTemp, FRIDGEMINTEMP, FRIDGEMAXTEMP))
        {
            if (numFridge < storageArray[1].length)
            {
                storageArray[1][numFridge] = inFood;
                numFridge = numFridge + 1;
            }
            else
            {
                throw new IllegalArgumentException ("Fridge is full");
            }
        }
        else if (validateStorageTemp(storageTemp, PANTRYMINTEMP, PANTRYMAXTEMP))
        {
            if (numPantry < storageArray[2].length)
            {
                storageArray[2][numPantry] = inFood;
                numPantry = numPantry + 1;
            }
            else
            {
                throw new IllegalArgumentException("Pantry is full");
            }
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: removeFood
     *IMPORT: storageArea(Integer), storageLocation(Integer)
     *EXPORT: none
     *ASSERTION: food at area and location is removed if exist and shuffle
                 array otherwise FAILS
     ************************************************************/
    public void removeFood(int storageArea, int storageLocation)
    {
        storageArea = storageArea - 1;
        storageLocation = storageLocation - 1;
        int max;
        if (storageArray[storageArea][storageLocation] == null)
        {
            throw new IllegalArgumentException("No food item at storage "
                                                + "location and area");
        }
        switch(storageArea)
        {
            case 0:
                numFreezer = numFreezer - 1;
                max = numFreezer;
                break;
            case 1:
                numFridge = numFridge - 1;
                max = numFridge;
                break;
            default:
                numPantry = numPantry - 1;
                max = numPantry;
        }
        for (int column = storageLocation; column < max; column++)
        {
            storageArray[storageArea][column] = storageArray[storageArea][column + 1];
        }
        storageArray[storageArea][max] = null;
    }

    /************************************************************
     *SUBMODULE: displayContents
     *IMPORT: storageArea(Integer)
     *EXPORT: displayString(String)
     *ASSERTION: gets the string of the chosen storage area
     ************************************************************/
    public String displayContents(int storageArea)
    {
        String displayString;
        switch(storageArea)
        {
            case 1:
                displayString = toFreezerString();
                break;
            case 2:
                displayString = toFridgeString();
                break;
            default:
                displayString = toPantryString();
        }
        return displayString;
    }

    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: findExpired
     *IMPORT: none
     *EXPORT: expiredString
     *ASSERTION: checks for expired food items and gets the string of the 
                 expired food items
     ************************************************************/
    public String findExpired()
    {
        String expiredString = "";
        Food food;
        int numExpired, max;
        GregorianCalendar today = new GregorianCalendar();
        for (int row = 0; row < storageArray.length; row++)
        {
            numExpired = 0;
            switch(row)
            {
                case 0:
                    expiredString = "Freezer";
                    max = numFreezer;
                case 1:
                    expiredString = expiredString + "\n" + "Fridge";
                    max = numFridge;
                default:
                    expiredString = expiredString + "\n" + "Pantry";
                    max = numPantry;
            }
            for (int column = 0; column < max; column++)
            {
                food = storageArray[row][column];
                if (food.calcExpiry(today))
                {
                    expiredString = expiredString + "\n" + (column + 1)
                                    + ". " + food.toString();
                    numExpired = numExpired + 1;
                }
            }
            if (numExpired == 0)
            {
                expiredString = expiredString + "\n" + "No food expired here";
            }
        }
        return expiredString;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //PRIVATE SUBMODULES:

    /************************************************************
     *SUBMODULE: arrayEquals
     *IMPORT: inStorageArray(2D ARRAY OF Food)
     *EXPORT: isEqual(boolean)
     *ASSERTION: storageArray is equal to inStorageArray
     ************************************************************/
    private boolean arrayEquals(Food[][] inStorageArray)
    {
        boolean isArrayEquals = true;
        int column;
        do
        {
            column = 0;
            if (!(storageArray[0][column].equals(inStorageArray[0][column])))
            {
                isArrayEquals = false;
            }
            column = column + 1;
        }while (isArrayEquals && column < numFreezer);
        do
        {
            column = 0;
            if (!(storageArray[1][column].equals(inStorageArray[1][column])))
            {
                isArrayEquals = false;
            }
            column = column + 1;
        }while (isArrayEquals && column < numFridge);
        do
        {
            column = 0;
            if (!(storageArray[2][column].equals ( inStorageArray[2][column])))
            {
                isArrayEquals = false;
            }
            column = column + 1;
        }while (isArrayEquals && column < numPantry);
        return isArrayEquals;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
    *SUBMODULE: storeFood
    *IMPORT: lineArray(ARRAY OF String)
    *EXPORT: none
    *ASSERTION: valid food objects are constructed and stored into
                storageArray and invalid are disregarded
     ************************************************************/
    private void storeFood(String[] lineArray)
    {
        String foodType, name, cut, packaging, type, line, date;
        int day, month, year, numPieces;
        double weight, volume, storageTemp;
        Food food;
        for (int i = 3; i < lineArray.length; i++)
        {   
            try
            {
                line = lineArray[i];
                foodType = FileIO.processString(line, 0);
                if (foodType.equals("Meat"))
                {
                    name = FileIO.processString(line, 1);
                    cut = FileIO.processString(line, 2);
                    weight = FileIO.processReal(line, 3);
                    storageTemp = FileIO.processReal(line, 4);
                    date = FileIO.processString(line, 5);
                    day = FileIO.processDate(date, 0);
                    month = FileIO.processDate(date, 1);
                    year = FileIO.processDate(date, 2);
                    packaging = FileIO.processString(line, 6);
                    food = new Meat(name, storageTemp, packaging, day, 
                                    month, year, cut, weight);
                    addFood(food);
                }
                else if (foodType.equals("Grain"))
                {
                    name = FileIO.processString(line, 1);
                    type = FileIO.processString(line, 2);
                    volume = FileIO.processReal(line, 3);
                    storageTemp = FileIO.processReal(line, 4);
                    date = FileIO.processString(line, 5);
                    day = FileIO.processDate(date, 0);
                    month = FileIO.processDate(date, 1);
                    year = FileIO.processDate(date, 2);
                    packaging = FileIO.processString(line, 6);
                    food = new Grain(name, storageTemp, packaging, day,
                                     month, year, type, volume);
                    addFood(food);
                }
                else if (foodType.equals("Fruit"))
                {
                    name = FileIO.processString(line, 1);
                    type = FileIO.processString(line, 2);
                    numPieces = FileIO.processInteger(line, 3);
                    storageTemp = FileIO.processReal(line, 4);
                    date = FileIO.processString(line, 5);
                    day = FileIO.processDate(date, 0);
                    month = FileIO.processDate(date, 1);
                    year = FileIO.processDate(date, 2);
                    packaging = FileIO.processString(line, 6);
                    food = new Fruit(name, storageTemp, packaging, day,
                                     month, year, type, numPieces);
                    addFood(food);
                }
                
                
                
                
                
                else if (foodType.equals("Vegetable"))
                {
                    name = FileIO.processString(line, 1);
                    weight = FileIO.processReal(line, 2);
                    storageTemp = FileIO.processReal(line, 3);
                    date = FileIO.processString(line, 4);
                    day = FileIO.processDate(date, 0);
                    month = FileIO.processDate(date, 1);
                    year = FileIO.processDate(date, 2);
                    packaging = FileIO.processString(line, 5);
                    food = new Vegetable(name, storageTemp, packaging,
                                         day, month, year, weight);
                    addFood(food);
                }
            }
            catch (IllegalArgumentException e)
            {
            }
        }
        
    }

    /************************************************************
     *SUBMODULE: validateStorageTemp
     *IMPORT: inStorageTemp (Real), min(Real), max(Real)
     *EXPORT: valid (boolean)
     *ASSERTION: returns true if inStorageTemp is between min and max 
     *           inclusive otherwise false
     ************************************************************/
    private boolean validateStorageTemp(double inStorageTemp, double min,
                                        double max)
    { 
        boolean valid;
        valid = ((inStorageTemp >= min) && (inStorageTemp <= max));
        return valid;
    }
    
    /************************************************************
     *SUBMODULE: toFreezerString
     *IMPORT: none
     *EXPORT: freezerString(String)
     *ASSERTION: creates a string that contains all of the 
     *           freezer information in a user friendly format
     ************************************************************/
    private String toFreezerString()
    {
        String freezerString = "Freezer:";
        Food food;
        if (numFreezer == 0)
        {
            freezerString = freezerString + "\n" + "No food item in freezer";
        }
        else
        {
            for (int column = 0; column < numPantry; column++)
            {
                food = storageArray[0][column];
                freezerString = freezerString + "\n" + (column + 1) + ". " 
                                + food.toString();
            }
        }
        return freezerString;
    }
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: toFridgeString
     *IMPORT: none
     *EXPORT: fridgeString(String)
     *ASSERTION: creates a string that contains all of the 
     *           fridge information in a user friendly format
     ************************************************************/
    private String toFridgeString()
    {
        String fridgeString = "Fridge:";
        Food food;
        if (numFridge == 0)
        {
            fridgeString = fridgeString + "\n" + "No food item in fridge";
        }
        else
        {
            for (int column = 0; column < numFridge; column++)
            {
                food = storageArray[1][column];
                fridgeString = fridgeString + "\n" + (column + 1) + ". " 
                                + food.toString();
            }
        }
        return fridgeString;
    }

    /************************************************************
     *SUBMODULE: toPantryString
     *IMPORT: none
     *EXPORT: pantryString(String)
     *ASSERTION: creates a string that contains all of the 
     *           pantry information in a user friendly format
     ************************************************************/
    private String toPantryString()
    {
        String pantryString = "Pantry:";
        Food food;
        if (numPantry == 0)
        {
            pantryString = pantryString + "\n" + "No food item in pantry";
        }
        else
        {
            for (int column = 0; column < numPantry; column++)
            {
                food = storageArray[2][column];
                pantryString = pantryString + "\n" + (column + 1) + ". " 
                                + food.toString();
            }
        }
        return pantryString;
    }
}