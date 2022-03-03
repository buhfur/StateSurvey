package src;
import java.util.*;
//lab 2 - Ryan McVicker

public class PartyTest {

    public static Scanner userInput = new Scanner(System.in);
   public static void main(String[] args){
        String[] classes = {"Warrior", "Mesmer","Necromancer","Thief","Elementalist","Ranger","Revanant"};

        Hero hero1 = new Hero("morlako");
        Hero hero2 = new Hero("kocho");
        Hero hero3 = new Hero("vendrell");

        Party dungeonGroup = new Party();

        dungeonGroup.addHero(0, hero1);
        dungeonGroup.addHero(1, hero2);
        dungeonGroup.addHero(2, hero3);

        
        System.out.println("__________________________________________");
        System.out.println("|=== CHOOSE A CLASS ======================|");
        System.out.println("|=========================================|");
        System.out.println("| Warrior | Mesmer | Necromancer | Thief  |");
        System.out.println("|=========================================|");
        System.out.println("| Elementalist | Ranger | Revanant        |");
        System.out.println("|=========================================|");
        
       

        //will repeat until user enters valid class
        for(Hero heroes : dungeonGroup.Heroes){

            boolean isValidClass = false;
            String classInput = ""; // can i make this : String classInput; ?? if so, change later
            
            while(isValidClass != true){
                
                System.out.print(String.format("Enter a class for %s : ",heroes.name));
                classInput = userInput.nextLine();
                System.out.print("\n"); // TODO: find cleaner way to put a new line after the user input ; StringBuilder maybe?


               /* for(String ValidClass: classes){

                    if (classInput == ValidClass)
                        heroes.hClass = classInput;
                        System.out.println(String.format("%s has chosen the : %s ",heroes.name, classInput));

                        isValidClass = true;
                }*/
                boolean containsClass = Arrays.asList(classes).contains(classInput);
                if (containsClass == true){
                    heroes.hClass = classInput;
                    System.out.println(String.format("%s has chosen the : %s \n",heroes.name, classInput));

                    isValidClass = true;
                }
            }

          } 

        //randomize all the heroes levels and give random amount of experience
        
        Random randomNumber = new Random();

        int randomXp;
        int randomIndex;

        for(Hero partyMember : dungeonGroup.Heroes){

            partyMember.setRandomLevel();
        
            randomXp = randomNumber.nextInt(78 - 5) + 5;

            partyMember.gainExperience(randomXp);
        }

        //give random experience to the whole party 
    
        // does randomXp need to be reinstated in order to generate another random number?
        randomXp = randomNumber.nextInt(78 - 5) + 5;

        randomIndex = randomNumber.nextInt(2 - 0) + 0;
        dungeonGroup.gainExperience(randomXp);
        
        //remove and add a member to the group

        Hero hero5 = new Hero("minty");

        
        dungeonGroup.removeHero(randomIndex);

        dungeonGroup.addHero(randomIndex, hero5);

        hero5.hClass = "Thief";

        hero5.setRandomLevel();

        //print out the party info 
        
        System.out.println(dungeonGroup.toString() + "\n");
    
        //section for run.bat in case theres trouble with importing in eclipse
        System.out.println("Press Enter key to exit...");
        userInput.nextLine();
        System.exit(0); 
    }

} 

