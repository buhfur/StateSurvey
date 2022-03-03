import java.util.*;
import java.nio.*;
import java.io.*;

public class fileTest{


    public static void main(String[] args){
        File checkFile = new File("states.bin");
        System.out.printf("does file exist? : %s",checkFile.exists()); 
    }
}