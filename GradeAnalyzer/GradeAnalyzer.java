import java.util.*;
import java.io.*;
import java.nio.file.*;

/** I decided to use underscores instead of camel case as I believe it reigns supreme over camelCase*/

public class GradeAnalyzer{



	public static void main(String[] args){
		


		ArrayList<student> student_array = new ArrayList<student>();

		File student_grades_file = new File("student_grades.txt");

		try{

		// read the student_grades files

			ArrayList<String> file_data = new ArrayList<String>();
	
			String student_grade_data; 

			BufferedReader gradeFileReader = new BufferedReader(new FileReader(student_grades_file));

			while((student_grade_data = gradeFileReader.readLine()) != null){
			
				// each line is split into an array, then used to make Student objects
				file_data.add(student_grade_data);	
			}

			String student_name;
			ArrayList<Integer> score_array;
			for(String file_line : file_data){

				
				String file_line_array[]  = file_line.split(" ");
				score_array = new ArrayList<Integer>();

				for(int a = 0 ; a < file_line_array.length; a++){
					try {
						score_array.add(Integer.parseInt(file_line_array[a]));
					}catch(NumberFormatException e){
						continue;
					}
				}


				student_array.add(new student(file_line_array[0], score_array));
			}

			File checkForFile = new File("final_grades.txt");
			if(checkForFile.exists()){
				checkForFile.delete();
			}
			PrintWriter printWriter = new PrintWriter(new FileWriter("final_grades.txt",true));

			//adds the first header to the outputFile
			printWriter.print("Student\t\t        T1  T2  T3  T4  T5  Avg     Grade\n");
			printWriter.flush();
			//loop through all student objects calling their methods

			for (int a = 0; a < student_array.size();a++ ){

				//calculate all the objects average and final grade
				student_array.get(a).calculateAverage();
				student_array.get(a).calculateGrade();
				student_array.get(a).generateOutput(printWriter);
			}	
						


	
		}catch(Exception err){
			System.out.println(err);
		}
	}
}






