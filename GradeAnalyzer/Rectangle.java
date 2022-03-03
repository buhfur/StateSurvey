import java.util.*;

//Ryan McVicker
// 08.26.2021
public class Rectangle{



        public static ArrayList<ArrayList<String>> rectList = new ArrayList<ArrayList<String>>();
	public static int numRows = 1;
	public static int numCols = 1;

	private static boolean filled = false;

	public static String toString(ArrayList<ArrayList<String>> RectangleList){
		
		StringBuilder returnString = new StringBuilder();

		for(ArrayList<String> row : RectangleList){
			
			String rowString = String.join(" ", row);

			System.out.println(rowString);

			//append returnString

			returnString.append(rowString);
		}
		System.out.println("\n\n\n\n\n");
		return returnString.toString();


	}
	



	public Rectangle(int rowinput, int colsinput, String filledinput){

		 
		numRows = rowinput;
		numCols = colsinput;
		filled = (filledinput=="filled") ? true : false; 

		
        	ArrayList<ArrayList<String>> rectList = new ArrayList<ArrayList<String>>();
		
		/** The amount of rows(numRows) dictate how many actual ArrayLists will be in rectList */
		for ( int colsIndex = 0; colsIndex < getRows(); colsIndex++){
			
			ArrayList<String> rowArray = new ArrayList<String>();
			
			for( int rowIndex = 0; rowIndex < Rectangle.getCols(); rowIndex++){
				rowArray.add("#");	
				// check if the filled and the rowIndex is not the first or last index, if true; change character to space
				if (filled) { 
					if (rowIndex != 0 && rowIndex != rowArray.size()) { rowArray.add(" "); }
				}
			}

			//TODO: need to add the filled/unfilled functionality
			// my guess is that if ( row != 0 && row != -1 ) 
			// remove all the characters besides the first and the last 
			// so the array would look like for a row of 4 characters;  ArrayList<String>("#"," "," ","#"); or something similar 
			rectList.add(rowArray);
		}
		
		toString(rectList);
	}



	public static void main(String[] args){


		
	}

	// GETTERS AND SETTERS
	public static int getRows(){
		return numRows;
	}


	public static int getCols(){

		return numCols;
	}
	
	public static void setRows(int rowinput){

		numRows = rowinput;
	}


	public static void setCols(int colsinput){
		
		numCols = colsinput;

	}

}

