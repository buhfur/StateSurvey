import java.util.*;
import java.io.*;
//script to test the rectangle class


public class RectangleTest{
	
	public static void main(String[] args){

		// find the quickest way to read files 
		// going to use buffered reader instead of lambdas until i fully know how to use them in java
		File dataFile = new File("rectangles.txt");

		try{
			BufferedReader dataFileReader = new BufferedReader(new FileReader(dataFile));
			
			ArrayList<String> dataList = new ArrayList<String>();

			String fileData; /** String holding data from the line read from dataFileReader.readLine() */

			while((fileData= dataFileReader.readLine()) != null){
				
				// while loop that adds all lines in the file to an arrayList

				dataList.add(fileData);
				

			}

			//found out this new feature in java 8 for iteration, prints out the arrayList

//			dataList.forEach(System.out::println);

			// need to parse through the data file

			//first line is the amount of objects

			/** dataList.get(0) : Pulls out the first element in the file, being the quantity of rectangle objects to create */
			
			ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();
	
			int amtOfObjects = Integer.parseInt(dataList.get(0));

			//creating all the rectangles and adding them all to a list
			for (int index = 1; index < amtOfObjects ; index++){

				// use the dataList.get(index).split(" ") to make objects of rectangles
					
				String[] splitEntry = dataList.get(index).split(" ");
				//create the rectangle using data from the file 
				
				Rectangle newRect = new Rectangle(Integer.parseInt(splitEntry[0]),Integer.parseInt(splitEntry[1]), splitEntry[2]);

				rectangleList.add(newRect);

							
			}


		}catch(Exception err){

			System.out.println(err);

		}
		
					

	}

}

