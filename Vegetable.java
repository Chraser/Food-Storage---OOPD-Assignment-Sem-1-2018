/************************************************************
 *Name: Kay Men Yap
 *File name: Vegetable.java
 *Purpose: Holds information on vegetable items 
 *Date last modified: 30/5/2018
 ************************************************************/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class Vegetable extends Food 
{
    public static final double MINWEIGHT = 0.2;
    public static final double MAXWEIGHT = 5.0;
    public static final double SPACERATIO = 1.025;
    
    private String type;
    private double weight;
    
    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: none
     *ASSERTION: weight of 2.0 is valid
     ************************************************************/
    public Vegetable()
    {
       super();
       weight = 2.0;
    }
    
     /************************************************************
     *Alternate Constructor:
	 *IMPORT: inName (String), inStorageTemp (Real), inPackaging (String),
     *        inDay (Integer), inMonth (Integer), inYear (Integer),
     *        inWeight (Real)
	 *EXPORT: none
	 *ASSERTION: Creates the object if the imports are valid and FAILS 
                 otherwise
     ************************************************************/
    public Vegetable(String inName, double inStorageTemp, String inPackaging, 
                int inDay, int inMonth, int inYear, double inWeight)
    {
        super(inName, inStorageTemp, inPackaging, inDay, inMonth, inYear);
        setWeight(inWeight);
    }
    
    /************************************************************
     *Copy Constructor:
	 *IMPORT: inVegetable (Vegetable)
	 *EXPORT: none
	 *ASSERTION: Creates an object with an identical object state as the
                 import.
     ************************************************************/
    public Vegetable(Vegetable inVegetable)
    {
        super(inVegetable);
        weight = inVegetable.getWeight();
    }
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: clone
	 *IMPORT: none
	 *EXPORT: cloneVegetable (Vegetable)
	 *ASSERTION: Creates a new Vegetable object using the copy constructor
     ************************************************************/
    public Vegetable clone()
    {
        Vegetable cloneVegetable;
        cloneVegetable = new Vegetable(this);
        return cloneVegetable;
    }
    
    //ACCESSORS
    public double getWeight()
    {
        return weight;
    }
    
    /********************************************************************
     *SUBMODULE: equals
	 *IMPORT: inObject (Object)
	 *EXPORT: isEqual (Boolean)
	 *ASSERTION: Two Vegetables are interchangeable if they have the same name, 
     *           storageTemp, packaging, dateMark, type and weight
     *********************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if(inObject instanceof Vegetable)
        {
            Vegetable inVegetable = (Vegetable)inObject;
            if(super.equals(inVegetable))
            {
                if(Math.abs(weight - inVegetable.getWeight()) < 0.01)
                {
                    isEqual = true;
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
        foodString = "Type: Vegetable, Name: " + super.getName()
                       + ", Storage Temperature: " 
                       + super.getStorageTemp() + ", Packaging: " 
                       + super.getPackaging() + ", Best before Date: " 
                       + dateFormat.format(super.getDateMark().getTime())
                       + ", Weight: " + weight;
        return foodString;
    }
    
    
    
    
    
    
    
    //MUTATORS
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
	 *ASSERTION: creates a file string that contains all of the vegetable
     *           information in a file format
     *********************************************************************/
    public String toFileString()
    {
        String fileString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fileString = "Vegetable, " + super.getName() + ", " + weight + ", "
                      + super.getStorageTemp() + ", " 
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
     *ASSERTION: check if the Vegetable object has expired or not
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