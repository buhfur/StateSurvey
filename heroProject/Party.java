package src;
//Ryan McVicker - lab 2
public class Party {
   
    public Hero[] Heroes = new Hero[3];
    
    public Party(){
            System.out.println("party created");
    }

    public void addHero(int index, Hero hero){
        
        Heroes[index] = hero;

        System.out.println(String.format("%s has joined the group.", hero.name));

    }

    public Hero removeHero(int index){
        
        System.out.println(String.format("%s has been removed from the group.", Heroes[index].name));
        
        Heroes[index] = null;

        return Heroes[index];
        
    }

    public Hero getHero(int index){
       
        return Heroes[index];

    }

    public void gainExperience(int experience){
            //increases experience of ALL members in the party 
         
        for (Hero heroes : Heroes){
                heroes.experience += experience;
        }

        System.out.println(String.format("The party gained %d experience!",experience));
    }
    
    public String toString(){
       
        StringBuilder partyInfo = new StringBuilder();
        for (Hero heroes : Heroes){
               partyInfo.append(heroes.toString()); 
        }

        return partyInfo.toString();
    }
}
