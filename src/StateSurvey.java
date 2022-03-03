import java.util.*;
import java.nio.*;
import java.io.*;
/*********************************************************************
                    COMPILE OUTSIDE DIRECTORY 
                    (example)
                | 
                | C:\Users\ryanm> javac lab6_ryanmcvicker/StateSurvey.java 
                |
******************************************************************** */
public class StateSurvey{
    public static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
            try {
                fileRead fr = new fileRead();
                int age = getAge();
                int ZIPCode = getZIPcode();
                String[][] states = fr.readStateFile();
                String state = fr.getState(states);
                
                
                System.out.printf("\nAge:\t\t%d\n", age);
                System.out.printf("Address:\t%s %s\n\n", ZIPCode, state);
                
                System.out.println("Your survey is complete. " + 
                            "Your participation has been valuable.");
            }
            catch (CancelledSurveyException e) {
                System.out.println(e.getMessage());
            }
            finally {
                System.out.println("Thank you for your time.");
                System.exit(0);
            }
    }
    public static String getState(String[][] states) throws CancelledSurveyException{

        String state = "";
        System.out.print("Please enter the 2 letter state abbreviation or 'q' to quit: ");

        try{
            String ab = userInput.next();
            if(ab.charAt(0) == 'q'){
                throw new CancelledSurveyException("exit");
            }

            //look up state using users abbreviation
            for(int x = 0; x < states.length; x++){
                if(states[x][0] == ab){
                    state = states[x][1];
                }
            }
        }catch(CancelledSurveyException e){
            System.out.println(e.getMessage());
        }
        return state;
    }

    public static int getAge() throws CancelledSurveyException{

        int age = 0;

       

        try{
            System.out.print("Please enter your age or press 'q' to quit: ");
            int input = userInput.nextInt();
            if(input == 'q'){
                throw new CancelledSurveyException("error"); 
            }else if(input >= 115){
                System.out.println("Theres no way you are that old....");
                getAge();
            }else if(input > 0){
                age = input;
            }else{
                getAge();
            }

        }catch(NumberFormatException e){
            System.out.println("Please enter a valid number");
            System.out.println(e);
        }
            
        
        return age;

    }

    public static int getZIPcode() throws CancelledSurveyException{
        int zipcode = 0;
        System.out.print("Please enter your zip code, or press 'q' to exit: "); 
        try{
            zipcode = userInput.nextInt();
            if(zipcode == 'q'){
                throw new CancelledSurveyException("exit");
            }
        }catch(CancelledSurveyException e){
            System.out.println("Invalid input"); 
            getZIPcode();
        }
        return zipcode;

    }

   


}

class CancelledSurveyException extends Exception{
    public CancelledSurveyException(String str){
        super(str);
    }
}

class fileRead{
    //return 2d array String[][] array;
    public String[][] readStateFile(){

        String[][] blank = new String[1][1];
        try{
            FileInputStream finput = new FileInputStream(new File("states.bin"));
            StringBuffer buffer = new StringBuffer();
            DataInputStream inStream = new DataInputStream(finput);

            while(inStream.available() > 0 ){
                buffer.append(inStream.readUTF());
            }

            finput.close();

            String fileData = buffer.toString(); //hold all strings from the binary file
            //put values from file data into java map
            HashMap<String,String> fDataMap = new HashMap<String,String>(); 
            String[] outString = fileData.split(" ");
            String[][] returnArray = new String[outString.length][2];
            // then convert the map into a 2d array
            for(int i = 0; i < outString.length; i++){
                //check if both letters are upper case, if so, add them to the character array
                char[] stateAb = new char[2];
                char[] state = outString[i].toCharArray();
                
                stateAb[0] = Character.isUpperCase(state[state.length - 2]) ? state[state.length - 2] : null;
                stateAb[1] = Character.isUpperCase(state[state.length - 1]) ? state[state.length - 1] : null;


                
                returnArray[i][0] = new String(stateAb);
                returnArray[i][1] = outString[i + 1];

            }
            // okay so iterate through the list and check if the string is longer than 1 character
            // if it is, join it with the second element?
            System.out.println("returning array on line 155");
            return returnArray;

            

        }catch(IOException io){
            io.printStackTrace();

        }
        
        return blank; 
    }

    public String getState(String[][] states){
        
        return "0";
    }
    
    
}
