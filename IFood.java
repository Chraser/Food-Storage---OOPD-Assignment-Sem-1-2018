/************************************************************
 *Name: Kay Men Yap
 *File name: IFood.java
 *Purpose: Forces classes that implement IFood to have
           specified methods
 *Date last modified: 30/5/2018
 ************************************************************/
import java.util.Calendar;
public interface IFood
{
    public boolean calcExpiry( Calendar today );
    // imports todays date and exports true if this food item
    // has reached its expiry date, false otherwise.
    public int calcSpace( Food food );
    // imports a Food class object, and exports an integer
    // specifying the volume in litres of storage required.
}
/* Additional information:
Each subclass of Food has a different calculation for space:
Meat – weight * 0.86 rounded up
Grain – volume * 1.0 rounded up
Fruit – number of pieces * 1.95 rounded up
Vegetables – weight * 1.025 rounded up
*/