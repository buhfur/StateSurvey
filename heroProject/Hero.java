package src;
import java.util.*;

//Ryan McVicker - lab2 

public class Hero{
    public String name;
    public String hClass;
    public int level;
    public int experience;
    public static int MAX_LEVEL = 10;
    /*LEVELS has two added zeroes to make the index of the array correspond with the amount of experience to be given
    for example when giving the hero a random level, you need to give them the correct amount of experience to avoid
    the level getting lowered when adding experience.
    */
    public int[] LEVELS = {0,0,5,10,15,20,30,40,50,75,100}; 

    public Hero(String Name){
        
        name = Name;
    }
    
    public void setRandomLevel(){
        
        Random randomNumGenerator = new Random();
        level = randomNumGenerator.nextInt(10 - 1) + 1;

        experience = LEVELS[level]; // adding the corresponding experience for the level given

        System.out.println(String.format("%s level has been set to %d", name,level));

    }

    public void gainExperience(int Experience){
        
        experience += Experience;
        System.out.println(String.format("%s has gained %d experience!",name, experience));

        //check if the hero leveled up
        if(experience >= 5 && experience < 10){
           System.out.println(String.format("%s has progressed to level 2",name));
            level = 2;
        }
       
        else if(experience >= 10 && experience < 15){
           System.out.println(String.format("%s has progressed to level 3",name));
            level = 3;
        }
        
        else if(experience >= 15 && experience < 20){
           System.out.println(String.format("%s has progressed to level 4",name));
            level = 4;
        }

        else if(experience >= 20 && experience < 30){
           System.out.println(String.format("%s has progressed to level 5",name));
            level = 5;
        }
        else if(experience >= 30 && experience < 40){
           System.out.println(String.format("%s has progressed to level 6",name));
            level = 6;
        } 
        else if(experience >= 40 && experience < 50){
           System.out.println(String.format("%s has progressed to level 7",name));
            level = 7;
        }
        else if(experience >= 50 && experience < 75){
           System.out.println(String.format("%s has progressed to level 8",name));
            level = 8;
        }
        else if(experience >= 75 && experience < 100){
           System.out.println(String.format("%s has progressed to level 9",name));
            level = 9;
        }
        else if(experience >= 100){
           System.out.println(String.format("%s has progressed to level 10",name));
            level = MAX_LEVEL;
        }
    }

    public String toString(){

        String heroInfo = String.format("\nName:\t%s\nClass:\t%s\nLevel:\t%d\nExperience:\t%d\n\n",name,hClass,level,
        experience);

        return heroInfo;

    }
}