/************************************************************
 *Name: Kay Men Yap
 *File name: Fruit.java
 *Purpose: Holds information on fruit items 
 *Date last modified: 30/5/2018
 ************************************************************/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class Fruit extends Food
{
    public static final int MINNUMPIECES = 1;
    public static final int MAXNUMPIECES = 20;
    public static final double SPACERATIO = 1.95;
    
    private String type;
    private int numPieces;
    
    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: none
     *ASSERTION: citrus fruit type with a numPieces of 2 is valid
     ************************************************************/
    public Fruit()
    {
       super();
       type = "citrus";
       numPieces = 2;
    }
    
     /************************************************************
     *Alternate Constructor:
	 *IMPORT: inName (String), inStorageTemp (Real), inPackaging (String),
     *        inDay (Integer), inMonth (Integer), inYear (Integer),
     *        inType (String), inNumPieces (Integer)
	 *EXPORT: none
	 *ASSERTION: Creates the object if the imports are valid and FAILS 
                 otherwise
     ************************************************************/
    public Fruit(String inName, double inStorageTemp, String inPackaging, 
                int inDay, int inMonth, int inYear, String inType, 
                int inNumPieces)
    {
        super(inName, inStorageTemp, inPackaging, inDay, inMonth, inYear);
        setType(inType);
        setNumPieces(inNumPieces);
    }
    
    /************************************************************
     *Copy Constructor:
	 *IMPORT: inFruit (Fruit)
	 *EXPORT: none
	 *ASSERTION: Creates an object with an identical object state as the
                 import.
     ************************************************************/
    public Fruit(Fruit inFruit)
    {
        super(inFruit);
        type = inFruit.getType();
        numPieces = inFruit.getNumPieces();
    }
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: clone
	 *IMPORT: none
	 *EXPORT: cloneFruit (Fruit)
	 *ASSERTION: Creates a new Fruit object using the copy constructor
     ************************************************************/
    public Fruit clone()
    {
        Fruit cloneFruit;
        cloneFruit = new Fruit(this);
        return cloneFruit;
    }
    
    //ACCESSORS
    public String getType()
    {
        return type;
    }
    
    public int getNumPieces()
    {
        return numPieces;
    }
    
    /********************************************************************
     *SUBMODULE: equals
	 *IMPORT: inObject (Object)
	 *EXPORT: isEqual (Boolean)
	 *ASSERTION: Two Fruits are interchangeable if they have the same name, 
     *           storageTemp, packaging, dateMark, type and numPieces
     *********************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if(inObject instanceof Fruit)
        {
            Fruit inFruit = (Fruit)inObject;
            if(super.equals(inFruit))
            {
                if(type.equals(inFruit.getType()))
                {
                    if(numPieces == inFruit.getNumPieces())
                    {
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /********************************************************************
     *SUBMODULE: toString
	 *IMPORT: none
	 *EXPORT: foodString (String)
	 *ASSERTION: creates a string that contains all of the meat information
     *           in a user friendly format
     *********************************************************************/
    public String toString()
    {
        String foodString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        foodString = "Type: Fruit, Name: " + super.getName()
                       + ", Storage Temperature: " 
                       + super.getStorageTemp() + ", Packaging: " 
                       + super.getPackaging() + ", Useby Date: " 
                       + dateFormat.format(super.getDateMark().getTime())
                       + ", Type: " + type 
                       + ", NumPieces: " + numPieces;
        return foodString;
    }
    
    //MUTATORS
    /************************************************************
     *SUBMODULE: setType
	 *IMPORT: inType (String)
	 *EXPORT: none
	 *ASSERTION: sets the current type if valid and FAILS otherwise.
     ************************************************************/
     public void setType(String inType)
     {
         if(validateString(inType))
         {
             throw new IllegalArgumentException("Invalid type");
         }
         type = inType;
     }
     
    /********************************************************************
     *SUBMODULE: setNumPieces
	 *IMPORT: inNumPieces (Integer)
	 *EXPORT: none
	 *ASSERTION: sets the current numPieces if valid and FAILS otherwise.
     *********************************************************************/
    public void setNumPieces(int inNumPieces)
    {
        if (validateNumPieces(inNumPieces))
        {
            numPieces = inNumPieces;
        }
        else
        {
            throw new IllegalArgumentException("Invalid numPieces");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /********************************************************************
     *SUBMODULE: toFileString
	 *IMPORT: none
	 *EXPORT: fileString (String)
	 *ASSERTION: creates a file string that contains all of the fruit information
     *           in a file format
     *********************************************************************/
    public String toFileString()
    {
        String fileString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fileString = "Fruit, " + super.getName() + ", " + type + ", " 
                      + numPieces + ", " + super.getStorageTemp() + ", " 
                      + dateFormat.format(super.getDateMark().getTime())
                      + ", " + super.getPackaging();
        return fileString;
    }
    
    /********************************************************************
     *SUBMODULE: calcSpaceUsed
	 *IMPORT: none
	 *EXPORT: spaceUsed(Real)
     *ASSERTION: calculates the space used before rounding up
     *********************************************************************/
    public double calcSpaceUsed()
    {
        double spaceUsed = (double)numPieces * SPACERATIO;
        return spaceUsed;
    }
    
    /********************************************************************
     *SUBMODULE: calcExpiry
	 *IMPORT: today (GregorianCalendar)
	 *EXPORT: isExpired (boolean)
     *ASSERTION: check if the Fruit object has expired or not
     *********************************************************************/
    public boolean calcExpiry(Calendar today)
    {
        boolean isExpired = today.after(super.getDateMark());
        return isExpired;
    }
    
    /********************************************************************
     *SUBMODULE: calcSpace
	 *IMPORT: food (Food)
	 *EXPORT: space(Integer)
	 *ASSERTION: calculates space of imported food
     *********************************************************************/
    public int calcSpace(Food food)
    {
        double spaceDouble;
        spaceDouble = food.calcSpaceUsed();
        int spaceInt;
        if (spaceDouble > (double)((int)spaceDouble))
        {
            spaceInt = (int)(spaceDouble + 1.0);
        }
        else
        {
            spaceInt = (int)spaceDouble;
        }
        return spaceInt;
    }
    
    
    
    
    
    
    //PRIVATE SUBMODULES:
    
    /************************************************************
     *SUBMODULE: validateString
	 *IMPORT: inString(String)
	 *EXPORT: valid(boolean)
     *ASSERTION: returns true if the string is an empty string
     *           and false if string is not an empty string
     ************************************************************/
    private boolean validateString(String inString)
    {
        boolean valid;
        valid = (inString.equals(""));
        return valid;
    }
    
    /************************************************************
     *SUBMODULE: validateNumPieces
	 *IMPORT: inNumPieces(Integer)
	 *EXPORT: valid(boolean)
     *ASSERTION: numPieces is between 0.2 and 5.0 inclusive
     ************************************************************/
    private boolean validateNumPieces(double inNumPieces)
    {
        boolean valid;
        valid = ((inNumPieces >= MINNUMPIECES) && 
                 (inNumPieces <= MAXNUMPIECES));
        return valid;
    }
    
}