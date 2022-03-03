import java.util.*;
import java.io.*;


public class student{

	public String name;
	public char grade;

	public double average;

	public ArrayList<Integer> scores = new ArrayList<Integer>();
	public student(String Name, ArrayList<Integer> studentScores){

		name = Name;
		scores = studentScores;
	}

	public void calculateAverage(){
		
		average = 0.0;

		//for loop to calculate average
		for(int i = 0 ; i < scores.size(); i++){ average +=scores.get(i); }
		average = average / scores.size();

		System.out.println(average);
	}

	public void calculateGrade(){ 

		if( average <= 100 && average >= 90) { grade = 'A'; }

		else if( average <= 89 && average >= 80) { grade = 'B'; }
		else if( average <= 79 && average >= 70) { grade = 'B'; }
		else if( average <= 69 && average >= 60) { grade = 'B'; }
		else { grade = 'F'; }
	} 

	public void generateOutput(PrintWriter outFile) {
						
		outFile.write(String.format(" %-7.7s\t\t%d  %d  %d  %d  %d  %f    %c\n", name, scores.get(0),scores.get(1),scores.get(2),scores.get(3),scores.get(4),average,grade));
		outFile.flush();
		System.out.println(String.format(" %-7.7s\t\t%d  %d  %d  %d  %d  %f    %c", name, scores.get(0),scores.get(1),scores.get(2),scores.get(3),scores.get(4),average,grade));

		
	}

	public static Scanner userInput = new Scanner(System.in);


}
