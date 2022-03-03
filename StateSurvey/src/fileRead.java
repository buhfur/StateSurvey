package lab6_ryanmcvicker;

import java.util.*;
import java.io.*;
//seperate file for reading states.bin, for readability and reducing clutter.
class fileRead{
    //return 2d array String[][] array;
    public String[][] readStateFile(){

        String[][] testArray = new String[1][1];
        try{
            FileInputStream finput = new FileInputStream(new File("states.bin"));
            StringBuffer buffer = new StringBuffer();
            DataInputStream inStream = new DataInputStream(finput);

            while(inStream.available() > 0 ){
                buffer.append(inStream.readUTF());
            }

            finput.close();
            System.out.println(buffer.toString());

            String fileData = buffer.toString(); //hold all strings from the binary file
            //put values from file data into java map
            Map<String,String> fDataMap = new HashMap(); 
            for(String word : fileData.split("")){
                System.out.println(fileData);
            }
            // then convert the map into a 2d array


        }catch(IOException io){
            io.printStackTrace();

        }
        return testArray;
        
    }

    public String getState(String[][] states){
        
        return "0";
    }
    
    
}