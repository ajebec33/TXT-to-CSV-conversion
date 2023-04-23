// Cheyenne Ajebe
//Last Updated: 4/6/2023
//Subject: DroneData Conversion: JSON to CSV
// Junior Engineering Clinic 2022-2023

// READ BEFORE RUNNING CODE:
//At line 25: change the file location to where you saved the txt file
//At line 71: change rows variable to how many frames that are in your data set
//Run the code and download the console output
//Change the file type to csv ( You can rename the file and at the end type .csv )


import java.io.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;  
public class Final_Test  
{  
public static void main(String[] args) throws Exception  
{  
	System.setOut(new PrintStream(new FileOutputStream("dummyXaxis.csv")));
	//System.out.println("This is test output");
	
//parsing a CSV file into Scanner class constructor  
// This allows us to grab the csv body file from any location within your computer
Scanner sc = new Scanner(new File("C:\\Users\\cheye\\Downloads\\dummyXaxis.txt"));  

// After every string value sets a comma 
sc.useDelimiter(",");   //sets the delimiter pattern  


// the first array list that sets the title of each columns / first row
String[][] title = new String[1][5];
title[0][0] = ",";
title[0][1] = "Frame,";
title[0][2] ="Time,";
title[0][3] ="label,";
title[0][4] = "coords\n";

// A for loop that Outputs the first array list
for( int i = 0; i < 5; i++)
{
	System.out.print(title[0][i] );
}

// the second array list that sets the initial values of the bounding box at 0
// this is for the second row and was missing in the original body of the file
String[][] arraySetZero = new String[1][5];
arraySetZero[0][0] = 0 + ",";
arraySetZero[0][1] = 0 + ",";
arraySetZero[0][2] = 0 + " sec,";
arraySetZero[0][3] ="DRONE,";
arraySetZero[0][4] = "\"[0.0, 0.0, 0.0, 0.0]\""; 

// A for loop that Outputs the second array list
for( int a = 0; a < 5; a++)
{
	System.out.print(arraySetZero[0][a] );
}

// Empty variables created to store different values at each frame
String coord1 = "";
String coord2 = "";
String coord3 = "";
String coord4 = "";
String cord = "";
double frames = 0;
double time = 0.00; 


// change the rows based on the amount of frames in the data set, columns should stay the same because their are 5 titles
int rows = 151; // frames
int columns = 5;
boolean test = true;

// Have the Time variable to display as 2 decimal points 
final DecimalFormat df = new DecimalFormat("0.00");

//Creates a third arraylist for the main body
String[][] arraySet = new String[rows][columns];

//This string value will grab each value in the original file so you can iterate it based on what type it should be
String frame = "";

//A while loop to iterate through each value of the file until there is none

double x = 0.00;	
double y = 0.00;
double h = 0.00;
double w = 0.00;
//&& test == true
	while (sc.hasNext() == true )  //returns a boolean value  
	{
	/*	if (count == 1500)
		{
			//insert wait for space to be pressed
			Thread.sleep(count);
			count=0;
		}
		*/
		// populate matrix
		//Starts the matrix at row 1
		for (int j = 0; j < rows; j++) 
		{
			// creates a new line
			System.out.print("\n");
			// Starts the matrix at [1,0]
			for(int k = 0; k < columns; k++)
			{ 
					// 
					if (k == 0)
					{
						// grabs the first value and save in string file
						frame = sc.next();
						// deletes unnessesary value in string
						frame = frame.replace("{\"frameNumber\":", "");
						// changes the string to be an integer - optional
						frames = Double.parseDouble(frame);
						// sets final value in the arraylist
						arraySet[j][k] = frame + ",";
					}
					if (k == 1)
					{
						arraySet[j][k] = frame + ",";
					}
					
					if ( k == 2)
					{
						//System.out.print("\t"+ "\t" );
						time = frames * 1/30;
						arraySet[j][k] = df.format(time) + " sec,";
					}
					
					if (k == 3)
					{
						arraySet[j][k] ="DRONE,";
					}
		
					if (k == 4)
					{
						
						// skips unnescessary columns in the csv file
						sc.next();
						sc.next();
						sc.next();
						sc.next();
						sc.next();
						sc.next();
						sc.next();
						
						coord1 = sc.next();
						
						//This is set to grab the coordinates from the body
						// If the coordinates is <100 then if statement is there so the substring will grab the values from two places
						// y axis
						coord1 = coord1.replace("\"bbox\":{\"top\":", "");
					
						double c1 = Double.parseDouble(coord1);
						
						// x axis
						coord2 = sc.next();
						coord2 = coord2.replace("\"left\":", "");
			
						double c2 = Double.parseDouble(coord2);

		
						//height
						coord3 = sc.next();
						coord3 = coord3.replace("\"height\":", "");
						double c3 = Double.parseDouble(coord3);
						//coord3 = df.format(c3);
		
						//width
						coord4 = sc.next();
						// cuts off "width:" in the txt file
						coord4 = coord4.substring(8);
						coord4 = coord4.replace("}", "");
						double c4 = Double.parseDouble(coord4);
						
						// reformats into x,y,w,h
						x = c1;
						y = c2;
						h = c4;
						w = c3;
						
						double newX = (x+x+w)/2;
						double newY = (y+y+h)/2;
		
						
						coord1 = df.format(newX);
						coord2 = df.format(newY);
						coord3 = df.format(h);
						coord4 = df.format(w);
						
						cord = "\"[" + coord2  + ", "+ coord1 + ", "+ coord3 + ", "+ coord4 + "]\" ";
						arraySet[j][k] = cord; 
						
						sc.nextLine();
						
					}
		
				System.out.print(arraySet[j][k]);
		}

	}
	
}
	sc.close();  //closes the scanner  s
}
}
