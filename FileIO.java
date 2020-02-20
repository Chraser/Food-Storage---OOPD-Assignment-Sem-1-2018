/************************************************************
 *Name: Kay Men Yap
 *File name: FileIO.java
 *Purpose: Handle the file input output operations
 *Date last modified: 30/5/2018
 ************************************************************/

import java.io.*;
public class FileIO
{
    /************************************************************
     *SUBMODULE: getNumLines
     *IMPORTS: fileName(String)
     *EXPORT: numLines(Integer)
     *ASSERTION: numLines is an integer
     *PURPOSE: To get the number of lines in the file
     ************************************************************/
    public static int getNumLines(String fileName)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int numLines = 0;
        String line;
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            while (line != null)
            {
                numLines++;
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
        return numLines;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: getStorageSize
     *IMPORTS: fileName(String), storageArea(String)
     *EXPORT: size(Integer)
     *ASSERTION: size is an integer
     *PURPOSE: To get the storage size in the file
     ************************************************************/
    public static int getStorageSize(String fileName, String storageArea)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        int size = 0;
        String string;
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            
            for (int i = 0; i <= 2; i++)
            {
                line = bufRdr.readLine();
                string = processString(line, 0);
                if (string.equals(storageArea))
                {
                    size = processInteger(line, 1);
                }
            }
            fileStrm.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
        if(size <= 0)
        {
            throw new IllegalArgumentException("Invalid file format. Size "
                                                + "of storage can't be found");
        }
        return size;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: readFile
     *IMPORTS: lineArray(Array of String), fileName(String), numLines(Integer)
     *EXPORT: none
     *ASSERTION: file is not empty
     *PURPOSE: To load data from the file into the line array
     ************************************************************/
    public static void readFile(String[] lineArray, String fileName, int numLines)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            for(int i = 0; i < numLines; i++)
            {
                line = bufRdr.readLine();
                lineArray[i] = line;
            }
            fileStrm.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
    }
    
    /************************************************************
     *SUBMODULE: processString
     *IMPORTS: line(String), position(Integer)
     *EXPORT: stirng(String)
     *ASSERTION: line contains a string
     *PURPOSE: To get a string from line
     ************************************************************/
    public static String processString(String line, int position)
    {
        String[] lineArray = line.split(", ?");
        String string = lineArray[position];
        return string;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: processInteger
     *IMPORTS: line(String), position(Integer)
     *EXPORT: integer(Integer)
     *ASSERTION: line contains an integer value
     *PURPOSE: To get a string from line and change it to an
               integer from the line
     ************************************************************/
    public static int processInteger(String line, int position)
    {
        String[] lineArray = line.split(", ?");
        int integer = Integer.parseInt(lineArray[position]);
        return integer;
    }
	
    /************************************************************
     *SUBMODULE: processReal
     *IMPORTS: line(String), position(Integer)
     *EXPORT: real(Real)
     *ASSERTION: line contains a real value
     *PURPOSE: To get a string from line and change it to a real
               from the line
     ************************************************************/
    public static double processReal(String line, int position)
    {
        String[] lineArray = line.split(", ?");
        double real = Double.parseDouble(lineArray[position]);
        return real;
    }
    
    /************************************************************
     *SUBMODULE: processDate
     *IMPORTS: string(String), position(Integer)
     *EXPORT: integer(Integer)
     *ASSERTION: string contains date information
     *PURPOSE: To get a string and change it to an
               integer from the string
     ************************************************************/
    public static int processDate(String string, int position)
    {
        String[] stringArray = string.split("/");
        int integer = Integer.parseInt(stringArray[position]);
        return integer;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /************************************************************
     *SUBMODULE: writeFile
     *IMPORTS: fileString(String), fileName(String)
     *EXPORT: none
     *ASSERTION: fileName is a string
     *PURPOSE: To write the storage to a file
     */
    public static void writeOutStorage(String fileString, String fileName)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try{
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            pw.println(fileString);
            pw.close();
            System.out.println("The storage has been written to " + fileName);
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
        }
    }
}