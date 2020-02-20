/************************************************************
 *Name: Kay Men Yap
 *File name: Grain.java
 *Purpose: Holds information on grain items 
 *Date last modified: 30/5/2018
 ************************************************************/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class Grain extends Food
{
    public static final double MINVOLUME = 0.2;
    public static final double MAXVOLUME = 5.0;
    public static final double SPACERATIO = 1.0;
    
    private String type;
    private double volume;
    
    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: none
     *ASSERTION: a grain type of whole and volume of 2.0 is valid
     ************************************************************/
    public Grain()
    {
       super();
       type = "whole";
       volume = 2.0;
    }
    
    /************************************************************
     *Alternate Constructor:
	 *IMPORT: inName (String), inStorageTemp (Real), inPackaging (String),
     *        inDay (Integer), inMonth (Integer), inYear (Integer),
     *        inType (String), inVolume (Real)
	 *EXPORT: none
	 *ASSERTION: Creates the object if the imports are valid and FAILS 
                 otherwise
     ************************************************************/
    public Grain(String inName, double inStorageTemp, String inPackaging, 
                int inDay, int inMonth, int inYear, String inType, 
                double inVolume)
    {
        super(inName, inStorageTemp, inPackaging, inDay, inMonth, inYear);
        setType(inType);
        setVolume(inVolume);
    }
    
    /************************************************************
     *Copy Constructor:
	 *IMPORT: inGrain (Grain)
	 *EXPORT: none
	 *ASSERTION: Creates an object with an identical object state as the
                 import.
     ************************************************************/
    public Grain(Grain inGrain)
    {
        super(inGrain);
        type = inGrain.getType();
        volume = inGrain.getVolume();
    }
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: clone
	 *IMPORT: none
	 *EXPORT: cloneGrain (Grain)
	 *ASSERTION: Creates a new Grain object using the copy constructor
     ************************************************************/
    public Grain clone()
    {
        Grain cloneGrain;
        cloneGrain = new Grain(this);
        return cloneGrain;
    }
    
    //ACCESSORS
    public String getType()
    {
        return type;
    }
    
    public double getVolume()
    {
        return volume;
    }
    
    /********************************************************************
     *SUBMODULE: equals
	 *IMPORT: inObject (Object)
	 *EXPORT: isEqual (Boolean)
	 *ASSERTION: Two Grains are interchangeable if they have the same name, 
     *           storageTemp, packaging, dateMark, type and volume
     *********************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if(inObject instanceof Grain)
        {
            Grain inGrain = (Grain)inObject;
            if(super.equals(inGrain))
            {
                if(type.equals(inGrain.getType()))
                {
                    if(Math.abs(volume - inGrain.getVolume()) < 0.01)
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
        foodString = "Type: Grain, Name: " + super.getName()
                       + ", Storage Temperature: " 
                       + super.getStorageTemp() + ", Packaging: " 
                       + super.getPackaging() + ", Best before Date: " 
                       + dateFormat.format(super.getDateMark().getTime())
                       + ", Type: " + type 
                       + ", Volume: " + volume;
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
     *SUBMODULE: setVolume
	 *IMPORT: inVolume (real)
	 *EXPORT: none
	 *ASSERTION: sets the current volume if valid and FAILS otherwise.
     *********************************************************************/
    public void setVolume(double inVolume)
    {
        if (validateVolume(inVolume))
        {
            volume = inVolume;
        }
        else
        {
            throw new IllegalArgumentException("Invalid volume");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /********************************************************************
     *SUBMODULE: toFileString
	 *IMPORT: none
	 *EXPORT: fileString (String)
	 *ASSERTION: creates a file string that contains all of the grain information
     *           in a file format
     *********************************************************************/
    public String toFileString()
    {
        String fileString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fileString = "Grain, " + super.getName()  + ", " + type  + ", " 
                      + volume + ", " + super.getStorageTemp() + ", " 
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
        double spaceUsed = volume * SPACERATIO;
        return spaceUsed;
    }
    
    /********************************************************************
     *SUBMODULE: calcExpiry
	 *IMPORT: today (GregorianCalendar)
	 *EXPORT: isExpired (boolean)
     *ASSERTION: check if the Grain object has expired or not
     *********************************************************************/
    public boolean calcExpiry(Calendar today)
    {
        boolean isExpired = false;
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
     *SUBMODULE: validateVolume
	 *IMPORT: inVolume(Real)
	 *EXPORT: valid(boolean)
     *ASSERTION: volume is between 0.2 and 5.0 inclusive
     ************************************************************/
    private boolean validateVolume(double inVolume)
    {
        boolean valid;
        valid = ((inVolume >= MINVOLUME) && (inVolume <= MAXVOLUME));
        return valid;
    }
    
}