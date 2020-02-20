/************************************************************
 *Name: Kay Men Yap
 *File name: Meat.java
 *Purpose: Holds information on meat items 
 *Date last modified: 30/5/2018
 ************************************************************/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class Meat extends Food
{
    public static final double MINWEIGHT = 0.2;
    public static final double MAXWEIGHT = 5.0;
    public static final double SPACERATIO = 0.86;
    
    private String cut;
    private double weight;
    
    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: none
     *ASSERTION: 
     ************************************************************/
    public Meat()
    {
       super();
       cut = "loin";
       weight = 2.0;
    }
    
     /************************************************************
     *Alternate Constructor:
	 *IMPORT: inName (String), inStorageTemp (Real), inPackaging (String),
     *        inDay (Integer), inMonth (Integer), inYear (Integer),
     *        inCut (String), inWeight (Real)
	 *EXPORT: none
	 *ASSERTION: Creates the object if the imports are valid and FAILS 
                 otherwise
     ************************************************************/
    public Meat(String inName, double inStorageTemp, String inPackaging, 
                int inDay, int inMonth, int inYear, String inCut, 
                double inWeight)
    {
        super(inName, inStorageTemp, inPackaging, inDay, inMonth, inYear);
        setCut(inCut);
        setWeight(inWeight);
    }
    
    /************************************************************
     *Copy Constructor:
	 *IMPORT: inMeat (Meat)
	 *EXPORT: none
	 *ASSERTION: Creates an object with an identical object state as the
                 import.
     ************************************************************/
    public Meat(Meat inMeat)
    {
        super(inMeat);
        cut = inMeat.getCut();
        weight = inMeat.getWeight();
    }
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: clone
	 *IMPORT: none
	 *EXPORT: cloneMeat (Meat)
	 *ASSERTION: Creates a new Meat object using the copy constructor
     ************************************************************/
    public Meat clone()
    {
        Meat cloneMeat;
        cloneMeat = new Meat(this);
        return cloneMeat;
    }
    
    //ACCESSORS
    public String getCut()
    {
        return cut;
    }
    
    public double getWeight()
    {
        return weight;
    }
    
    /********************************************************************
     *SUBMODULE: equals
	 *IMPORT: inObject (Object)
	 *EXPORT: isEqual (Boolean)
	 *ASSERTION: Two Meats are interchangeable if they have the same name, 
     *           storageTemp, packaging, dateMark, cut and weight
     *********************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if(inObject instanceof Meat)
        {
            Meat inMeat = (Meat)inObject;
            if(super.equals(inMeat))
            {
                if(cut.equals(inMeat.getCut()))
                {
                    if(Math.abs(weight - inMeat.getWeight()) < 0.01)
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
        foodString = "Type: Meat, Name: " + super.getName()
                       + ", Storage Temperature: " 
                       + super.getStorageTemp() + ", Packaging: " 
                       + super.getPackaging() + ", Useby Date: " 
                       + dateFormat.format(super.getDateMark().getTime())
                       + ", Cut: " + cut 
                       + ", Weight: " + weight;
        return foodString;
    }
    
    //MUTATORS
    /************************************************************
     *SUBMODULE: setCut
	 *IMPORT: inCut (String)
	 *EXPORT: none
	 *ASSERTION: sets the current cut if valid and FAILS otherwise.
     ************************************************************/
     public void setCut(String inCut)
     {
         if(validateString(inCut))
         {
             throw new IllegalArgumentException("Invalid cut");
         }
         cut = inCut;
     }
     
    /********************************************************************
     *SUBMODULE: setWeight
	 *IMPORT: inWeight (real)
	 *EXPORT: none
	 *ASSERTION: sets the current weight if valid and FAILS otherwise.
     *********************************************************************/
    public void setWeight(double inWeight)
    {
        if (validateWeight(inWeight))
        {
            weight = inWeight;
        }
        else
        {
            throw new IllegalArgumentException("Invalid weight");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /********************************************************************
     *SUBMODULE: toFileString
	 *IMPORT: none
	 *EXPORT: fileString (String)
	 *ASSERTION: creates a file string that contains all of the meat information
     *           in a file format
     *********************************************************************/
    public String toFileString()
    {
        String fileString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fileString = "Meat, " + super.getName()  + ", " + cut  + ", "
                      + weight + ", " + super.getStorageTemp() + ", " 
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
        double spaceUsed = weight * SPACERATIO;
        return spaceUsed;
    }
    
    /********************************************************************
     *SUBMODULE: calcExpiry
	 *IMPORT: today (GregorianCalendar)
	 *EXPORT: isExpired (boolean)
     *ASSERTION: check if the Meat object has expired or not
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
     *SUBMODULE: validateWeight
	 *IMPORT: inWeight(Real)
	 *EXPORT: valid(boolean)
     *ASSERTION: weight is between 0.2 and 5.0 inclusive
     ************************************************************/
    private boolean validateWeight(double inWeight)
    {
        boolean valid;
        valid = ((inWeight >= MINWEIGHT) && (inWeight <= MAXWEIGHT));
        return valid;
    }
    
}