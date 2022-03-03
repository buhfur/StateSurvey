import java.util.*;
abstract class Car{

    private String id;
    private int year;
    protected double basePrice;
    protected double commission;
    protected static double totalAssets; // all car objects 

    public Car(){}; //executed first when class is inherited : - personal note
    public Car(String eid, int eyear, double eprice, double ecommission){
        this.id = eid;
        this.year = eyear;
        this.basePrice = eprice;
        this.commission = ecommission;
        
    }

    public void setID(String id){ this.id = id; }
    
    public String getID(){ return id; }

    public void setYear(int year){ this.year = year; }
    
    public int getYear() { return year; }

    public static double getTotalAssets(){
        return totalAssets;
    }
    public static void addTotalAssets(double asset){
        totalAssets += asset;
    }
    public String toString(){
        String returnString = String.format("VehicleID = %s\nModel year: %d\nBase Price: %d\n Commission = %d\n\n", getID(), getYear(), this.basePrice, this.commission);
        return returnString;
    }
    abstract void updateAssets();
    abstract double getMileage();
    abstract String goodBusiness();
}

class NewCar extends Car{
    private double optionCost;
    private double rebate;
    private static int numOfNewcar;
    private static double totalAssets;
    public NewCar(){ numOfNewcar++; }

    public NewCar(String eid, double eprice, int eyear, double ecommission, 
    double eoption, double erebate){

        super(eid,eyear,eprice,ecommission);
        setID(eid);
        setYear(eyear);
        super.basePrice = eprice;
        super.commission = ecommission;
        this.optionCost = eoption;
        this.rebate = erebate; 
    }

    public double computeTotal(){
        double totalCost = super.basePrice + this.optionCost + super.commission - this.rebate;
        return totalCost;
    } 

    public String goodBusiness(){
        if(this.commission > 0.08 * super.basePrice){
            return "Good";
            
        }else{
            return "Bad";
        }
    }

    public static int getNumOfNewcar(){ return numOfNewcar; }

    public static double getTotalAssets(){ return totalAssets; } 

    public String toString(){
        String returnString = String.format("VehicleID: %s\nModel year: %d\nBase Price: %f\nCommision: %f\nOption Cost: %f\nTotal Cost : %f\nDeal Result : %s\n\n", getID(), getYear(), basePrice,commission, optionCost, computeTotal(), goodBusiness());
        return returnString;
    }

    public void updateAssets(){ totalAssets += computeTotal(); }

    public double getMileage() { return 0; }
}

class UsedCar extends Car{
    private double mileage;
    private double rateOfDepreciation;
    private static int usedCarCount;
    private static double totalAssets;

    public UsedCar(){ usedCarCount++; }

    public UsedCar(String eid, double eprice, int eyear,
                    double ecommission, double emileage, 
                    double erate){
                        super(eid,eyear,eprice,ecommission);
                        setID(eid); // sets the id of the object 
                        setYear(eyear); // sets the year of the object 

                        super.basePrice = eprice;
                        
                        this.mileage = emileage;
                        this.rateOfDepreciation = erate;
                    }
    
    public double computeTotal(){
        double totalCost = super.basePrice - this.mileage * this.rateOfDepreciation+ this.commission; 
        return totalCost;
    }

    public String goodBusiness(){
        if (this.commission > 0.04 * computeTotal()){
            return "Good";
        }else{
            return "Bad";
        }
    }

    public static int getNumOfUsedcar(){ return usedCarCount; }

    public static double getTotalAssets(){ return totalAssets; }

    public void updateAssets(){ totalAssets += computeTotal(); }

    public String toString(){
        
        String returnString = String.format("VehicleID : %s\nModel year: %d\nBase Price: %f\n Commission = %f\nMileage = %f\nTotal Cost = %f\n Deal = %s\n\n", getID(),getYear(),basePrice,commission, getMileage(),computeTotal(),goodBusiness());
        return returnString;
    }

    public double getMileage(){ return this.mileage; }
}