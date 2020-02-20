/************************************************************
 *Name: Kay Men Yap
 *File name: Food.java
 *Purpose: Holds general information on food items
 *Date last modified: 30/5/2018
 ************************************************************/ 

//Parts of this file comprise externally-obtained code.
import java.util.GregorianCalendar;
public abstract class Food implements IFood
{
    private String name, packaging;
    private double storageTemp;
    private GregorianCalendar dateMark;
    
    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: none
     *ASSERTION: A food with the name "Chicken", having a storage   
     *           temperature of 5.0 and packaged in a plastic bag
     ************************************************************/
    public Food()
    {
        name = "Chicken";
        storageTemp = 5.0;
        packaging = "Plastic bag";
        dateMark = new GregorianCalendar();
    }
    
    /************************************************************
     *Alternate Constructor:
	 *IMPORT: inName (String), inStorageTemp (Real), inPackaging (String),
     *        inDay (Integer), inMonth (Integer), inYear (Integer)
	 *EXPORT: none
	 *ASSERTION: Creates the object if the imports are valid and FAILS 
                 otherwise
     ************************************************************/
    public Food(String inName, double inStorageTemp, String inPackaging,
                int inDay, int inMonth, int inYear)
    {
        setName(inName);
        setStorageTemp(inStorageTemp);
        setPackaging(inPackaging);
        setDateMark(inDay, inMonth, inYear);
    }
    
    /************************************************************
     *Copy Constructor:
	 *IMPORT: inFood (Food)
	 *EXPORT: none
	 *ASSERTION: Creates an object with an identical object state as the
                 import.
     ************************************************************/
    public Food(Food inFood)
    {
        name = inFood.getName();
        storageTemp = inFood.getStorageTemp();
        packaging = inFood.getPackaging();
        dateMark = inFood.getDateMark();
    }
    
    public abstract Food clone();
    
    //ACCESSORS
    public String getName()
    {
        return name;
    }
    
    public double getStorageTemp()
    {
        return storageTemp;
    }
    
    public String getPackaging()
    {
        return packaging;
    }
    
    public GregorianCalendar getDateMark()
    {
        GregorianCalendar dateMarkCopy = (GregorianCalendar)(dateMark.clone());
        return dateMarkCopy;
    }
    
    /********************************************************************
     *SUBMODULE: equals
	 *IMPORT: inObject (Object)
	 *EXPORT: isEqual (Boolean)
	 *ASSERTION: Two food are interchangeable if they have the same name, 
     *           storageTemp, packaging and date mark.
     *********************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if(inObject instanceof Food)
        {
            Food inFood = (Food)inObject;
            if(name.equals(inFood.getName()))
            {
                if(Math.abs(storageTemp - inFood.getStorageTemp()) < 0.01)
                {
                    if(packaging.equals(inFood.getPackaging()))
                    {
                        if(dateMark.equals(inFood.getDateMark()))
                        {
                            isEqual = true;
                        }
                    }
                }
            }
        }
        return isEqual;
    }
    
    public abstract String toString();
    
    //MUTATORS
    /************************************************************
     *SUBMODULE: setName
	 *IMPORT: inName (String)
	 *EXPORT: none
	 *ASSERTION: sets the current name if valid and FAILS otherwise.
     ************************************************************/
    public void setName(String inName)
    {
        if (validateString(inName))
        {
            throw new IllegalArgumentException("Invalid name");
        }
        name = inName;
    }

    
    
    
    
    /********************************************************************
     *SUBMODULE: setStorageTemp
	 *IMPORT: inStorageTemp (Real)
	 *EXPORT: none
	 *ASSERTION: sets the current mark if valid and FAILS otherwise.
     *********************************************************************/
    public void setStorageTemp(double inStorageTemp)
    {
        if (validateStorageTemp(inStorageTemp))
        {
            storageTemp = inStorageTemp;
        }
        else
        {
            throw new IllegalArgumentException("Invalid storage" +
                                               " temperature");
        }
    }
    
    /************************************************************
     *SUBMODULE: setPackaging
	 *IMPORT: inPackaging (String)
	 *EXPORT: none
	 *ASSERTION: sets the current packaging if valid and FAILS otherwise.
     ************************************************************/
    public void setPackaging(String inPackaging)
    {
        if (validateString(inPackaging))
        {
            throw new IllegalArgumentException("Invalid packaging");
        }
        packaging = inPackaging;
    }
    
    /************************************************************
     *SUBMODULE: setDateMark
	 *IMPORT: inDay(Integer), inMonth(Integer), inYear(Integer)
	 *EXPORT: none
	 *ASSERTION: sets the current dateMark if valid and FAILS otherwise.
     ************************************************************/
    public void setDateMark(int inDay, int inMonth, int inYear)
    {
        if (isValidDate(inDay, inMonth, inYear))
        {
            if (validateDateMark(inDay, inMonth, inYear))
            {
                dateMark = new GregorianCalendar(inYear, inMonth - 1, inDay);
            }
            else
            {
                throw new IllegalArgumentException("Invalid date as in "
                                                    + "the past");
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid date imports");
        }
    }
    
    public abstract String toFileString();

    public abstract double calcSpaceUsed();
    
    
    
    
    
    
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
        valid = ((inString.equals("")) || (inString.equals(null)));
        return valid;
    }
    
    /************************************************************
     *SUBMODULE: validateStorageTemp
	 *IMPORT: inStorageTemp(Real)
	 *EXPORT: valid(boolean)
     *ASSERTION: returns true if the storage temperature is in the range 
     *           of the storages and false if not in range
     ************************************************************/
    private boolean validateStorageTemp(double inStorageTemp)
    {
        boolean freezer = ((inStorageTemp >= Storage.FREEZERMINTEMP) && 
                           (inStorageTemp <= Storage.FREEZERMAXTEMP));
        boolean fridge = ((inStorageTemp >= Storage.FRIDGEMINTEMP) && 
                          (inStorageTemp <= Storage.FRIDGEMAXTEMP));
        boolean pantry = ((inStorageTemp >= Storage.PANTRYMINTEMP) &&
                          (inStorageTemp <= Storage.PANTRYMAXTEMP));
        boolean valid;
        valid = (freezer || fridge || pantry);
        return valid;
    }
    
    // Obtained from OLD Worked Examples in Object Orientation Example 
    // in Unit Materials of OOPD (Accessed on 28 May 2018)
    /************************************************************
     *SUBMODULE: isValidDate
	 *IMPORT: inDay(Integer), inMonth(Integer), inYear(Integer)
	 *EXPORT: valid(boolean)
	 *ASSERTION: returns true if its a valid date and false if invalid
     ************************************************************/
    private boolean isValidDate(int inDay, int inMonth, int inYear)
    {
        boolean valid = false;
        if (inYear >= 2000 && inYear <= 3000)
        {
            if(inMonth >= 1 && inMonth <= 12)
            {
                if(inDay >= 1 && inDay <= daysInMonth(inMonth, inYear))
                {
                    valid = true;
                }
            }
        }
        return valid;
    }

    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: daysInMonth
	 *IMPORT: inMonth(Integer), inYear(Integer)
	 *EXPORT: numDays(Integer)
	 *ASSERTION: finds the number of days in the inMonth
     ************************************************************/
    private int daysInMonth(int inMonth, int inYear)
    {
        int numDays;
        if (( inMonth == 4 ) || ( inMonth == 6 ) ||
            ( inMonth == 9 ) || ( inMonth == 11 ))
        {
            numDays = 30;
        }
        else
        {
            if (( inMonth == 1 ) || ( inMonth == 3 ) ||
                ( inMonth == 5 ) || ( inMonth == 7 ) ||
                ( inMonth == 8 ) || ( inMonth == 10 ) ||
                ( inMonth == 12 ))
            {
                numDays = 31;
            }
            else
            {
                if (isLeapYear(inYear))
                {
                    numDays = 29;
                }
                else
                {
                    numDays = 28;
                }
            }
        }
        return numDays;
    }
    
    /************************************************************
     *SUBMODULE: isLeapYear
	 *IMPORT: inYear(Integer)
	 *EXPORT: isLeap(boolean)
	 *ASSERTION: returns true if inYear is a leap year and false if
     *           not a leap year        
     ************************************************************/
    private boolean isLeapYear(int inYear)
    {
        boolean isLeap =(( inYear % 4 == 0 ) && (!(( inYear % 100 == 0 ) && 
                         ( inYear % 400 != 0 ))));
        return isLeap;
    }
    
    // End of code obtained from OLD Worked Examples in Object Orientation 
    // Example in Unit Materials of OOPD
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: validateDateMark
	 *IMPORT: inDay(Integer), inMonth(Integer), inYear(Integer)
	 *EXPORT: valid(boolean)
	 *ASSERTION:  returns true if the date is not in the past and false if 
     *            in the past
     ************************************************************/
    private boolean validateDateMark(int inDay, int inMonth, int inYear)
    {
        GregorianCalendar date =  new GregorianCalendar(inYear, inMonth, inDay);
        GregorianCalendar today =  new GregorianCalendar();
        boolean valid;
        valid = (!(date.before(today)));
        return valid;
    }
}