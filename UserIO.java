/************************************************************
 *Name: Kay Men Yap
 *File name: UserIO.java
 *Purpose: Handles all of user inputs and outputs
 *Date last modified: 30/5/2018
 ************************************************************/
// Parts of this file comprise externally-obtained code.
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.GregorianCalendar;
public class UserIO
{
    /************************************************************
    *SUBMODULE: getMenuSelection
    *IMPORTS: none
    *EXPORT: getMenuSelection
    *ASSERTION: optionSelected is an integer between 1  to 7 inclusive
    *PURPOSE: print out the menu and request for menu selection
     ************************************************************/
    public static int getMenuSelection()
    {
        int optionSelected = 0;
        String error = "";
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println("Select an option:");
            System.out.println("1. Add food");
            System.out.println("2. Remove food");
            System.out.println("3. Display contents");
            System.out.println("4. Find expired");
            System.out.println("5. Read in storage");
            System.out.println("6. Write out storage");
            System.out.println("7. Exit");
            try
            {
                optionSelected = sc.nextInt();
                error = "Invalid option selected. Please enter an integer "
                         + "between 1 to 7 inclusive.";
            }
            catch (InputMismatchException e)
            {
                String flush = sc.nextLine();
                error = "Invalid input. Please enter an integer.";
            }
        }while((optionSelected < 1) || (optionSelected > 7));
        return optionSelected;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: stringInput
     *IMPORTS: promptMessage(String)
     *EXPORT: string(String)
     *PURPOSE: prompt user to enter a string that is not empty 
               and inform any input errors
     ************************************************************/
    public static String stringInput(String promptMessage)
    {
        String string = "";
        String error = "";
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println(promptMessage);
            string = sc.nextLine();
            error = "ERROR: Input can only be a non empty string";
        }while(string.equals(""));
        return string;
    }
    
    /************************************************************
     *SUBMODULE: integerInput
     *IMPORTS: promptMessage(String), min(Integer), max(Integer)
     *EXPORT: integer(Integer)
     *PURPOSE: prompt user to enter an integer and inform any input
               errors
     ************************************************************/
    public static int integerInput(String promptMessage, int min, int max)
    {
        int integer;
        String error = "";
        integer = 0;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println(promptMessage);
            try
            {
                integer = sc.nextInt();
                error = "ERROR: Input can only be an integer between " + min
                         + " and " + max + " inclusive";
            }
            catch (InputMismatchException e)
            {
                String flush = sc.nextLine();
                error ="Invalid input. Integer input required."
                        + " Please enter again.";
            }
        }while((integer < min) || (integer > max));
        return integer;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: realInput
     *IMPORTS: promptMessage(String), min(Real), max(Real)
     *EXPORT: real(Real)
     *PURPOSE: prompt user to enter a real and inform any input
               errors
     ************************************************************/
    public static double realInput(String promptMessage, double min, 
                                   double max)
    {
        double real;
        String error = "";
        real = 0;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println(promptMessage);
            try
            {
                real = sc.nextDouble();
                error = "ERROR: Input can only be a real between " + min
                         + " and " + max + " inclusive";
            }
            catch (InputMismatchException e)
            {
                String flush = sc.nextLine();
                error = "Invalid input. Real input required."
                        + " Please enter again." ;
            }
        }while((real < min) || (real > max));
        return real;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: storageTempInput
     *IMPORTS: promptMessage(String)
     *EXPORT: storageTemp(Real)
     *PURPOSE: prompt user to enter a storage temperature and 
               inform any input errors
     ************************************************************/
    public static double storageTempInput(String promptMessage)
    {
        double storageTemp;
        String error, prompt;
        error = "";
        prompt = "Enter storage temperature of food that is in the range "
                  + "of Pantry(" + Storage.PANTRYMINTEMP + " - " 
                  + Storage.PANTRYMAXTEMP + "), Fridge(" 
                  + Storage.FRIDGEMINTEMP + " - " 
                  + Storage.FRIDGEMAXTEMP + "), or Freezer(" 
                  + Storage.FREEZERMINTEMP + " - " + Storage.FREEZERMAXTEMP 
                  + ")";
        storageTemp = -100;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println(prompt);
            try
            {
                storageTemp = sc.nextDouble();
                error = "ERROR: Input can only be a real in the range "
                         + "specified above";
            }
            catch (InputMismatchException e)
            {
                String flush = sc.nextLine();
                error = "Invalid input. Real input required."
                        + " Please enter again." ;
            }
        }while(!((storageTemp >= Storage.FREEZERMINTEMP &&
                  storageTemp <= Storage.FREEZERMAXTEMP) ||
                 (storageTemp >= Storage.FRIDGEMINTEMP && 
                  storageTemp <= Storage.FRIDGEMAXTEMP) ||
                 (storageTemp >= Storage.PANTRYMINTEMP &&
                  storageTemp <= Storage.PANTRYMAXTEMP)));
        return storageTemp;
    }
    
    /************************************************************
     *SUBMODULE: displayContents
     *IMPORT: storage(Storage)
     *EXPORT: none
     *ASSERTION: contents of a storage area requested by user is 
     *           outputted
     ************************************************************/
    public static void displayContents(Storage storage)
    {
        int storageArea;
        storageArea = integerInput("Enter storage area to be displayed" 
                                    + "\n" + "1. Freezer" + "\n"
                                    + "2. Fridge" + "\n"
                                    + "3. Pantry", 1, 3);
        System.out.println(storage.displayContents(storageArea));
    }

    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: findExpired
     *IMPORT: storage(Storage)
     *EXPORT: none
     *ASSERTION: food items that have expired is outputted to the 
     *           user along with its location
     ************************************************************/
    public static void findExpired(Storage storage)
    {
        String expiredString = storage.findExpired();
        System.out.println(expiredString);
    }
    
    // Obtained from OLD Worked Examples in Object Orientation Example 
    // in Unit Materials of OOPD (Accessed on 28 May 2018)
    
    /************************************************************
     *SUBMODULE: daysInMonth
	 *IMPORT: month(Integer), year(Integer)
	 *EXPORT: numDays(Integer)
	 *ASSERTION: finds the number of days in the month
     ************************************************************/
    public static int daysInMonth(int month, int year)
    {
        int numDays;
        if (( month == 4 ) || ( month == 6 ) ||
            ( month == 9 ) || ( month == 11 ))
        {
            numDays = 30;
        }
        else
        {
            if (( month == 1 ) || ( month == 3 ) ||
                ( month == 5 ) || ( month == 7 ) ||
                ( month == 8 ) || ( month == 10 ) ||
                ( month == 12 ))
            {
                numDays = 31;
            }
            else
            {
                if (isLeapYear(year))
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
	 *IMPORT: year(Integer)
	 *EXPORT: isLeap(boolean)
	 *ASSERTION: returns true if year is a leap year and false if
     *           not a leap year        
     ************************************************************/
    public static boolean isLeapYear(int year)
    {
        boolean isLeap =(( year % 4 == 0 ) && (!(( year % 100 == 0 ) && 
                         ( year % 400 != 0 ))));
        return isLeap;
    }
    
    // End of code obtained from OLD Worked Examples in Object Orientation 
    // Example in Unit Materials of OOPD
    
    /************************************************************
     *SUBMODULE: validateDateMark
	 *IMPORT: day(Integer), month(Integer), year(Integer)
	 *EXPORT: valid(boolean)
	 *ASSERTION:  returns true if the date is not in the past and false if 
     *            in the past
     ************************************************************/
    public static boolean validateDateMark(int day, int month, int year)
    {
        GregorianCalendar date =  new GregorianCalendar(year, month, day);
        GregorianCalendar today =  new GregorianCalendar();
        boolean valid;
        valid = (!(date.before(today)));
        return valid;
    }
}