import java.util.*;



public class CarTest{


    public static void main(String[] args){
        Car[] carInventory = new Car[6];

        NewCar new_car1 = new NewCar("N2312", 18000, 2006, 2000, 3000, 2000);
        NewCar new_car2 = new NewCar("N6467", 43000, 2006, 4000, 6000, 3000); 
        NewCar new_car3 = new NewCar("N0864", 24000, 2007, 1200, 2500, 0);

        carInventory[0] = new_car1;
        carInventory[2] = new_car2;
        carInventory[4] = new_car3;

        UsedCar used_car1 = new UsedCar("U3425", 16000, 2003, 400, 40000, 0.15); 
        UsedCar used_car2 = new UsedCar("U2347", 8000, 1998, 700, 12000, 0.1); 
        UsedCar used_car3 = new UsedCar("U4739", 18000, 2005, 2400, 12000, 0.2); 

        carInventory[1] = used_car1;
        carInventory[3] = used_car2;
        carInventory[5] = used_car3;


        new_car1.updateAssets();
        new_car2.updateAssets();
        new_car3.updateAssets();

        used_car1.updateAssets();
        used_car2.updateAssets();
        used_car3.updateAssets();


        Car.addTotalAssets(new_car1.getTotalAssets());
        Car.addTotalAssets(new_car2.getTotalAssets());
        Car.addTotalAssets(new_car3.getTotalAssets());
        Car.addTotalAssets(used_car1.getTotalAssets());
        Car.addTotalAssets(used_car2.getTotalAssets());
        Car.addTotalAssets(used_car3.getTotalAssets());

        double[] ncAssets = {new_car1.computeTotal(),new_car2.computeTotal(),new_car3.computeTotal()};
        double[] ucAssets = {used_car1.computeTotal(), used_car2.computeTotal(),used_car3.computeTotal()};
        
        System.out.printf("Total Assets of dealer : %f\n", Car.getTotalAssets());
        System.out.printf("Total Assets of NewCar: %f\n", NewCar.getTotalAssets());
        System.out.printf("Total Assets of UsedCar: %f\n\n", UsedCar.getTotalAssets());
        System.out.printf("Average price of UsedCar: %f\n", getAverage(ucAssets));
        System.out.printf("Average price of NewCar : %f\n\n", getAverage(ncAssets));

        for(Car car : carInventory){
           System.out.println( car.toString());
        }
    }

    public static double getAverage(double[] assets){
        double sum = 0;

        for ( int i = 0; i < assets.length; i++){
            sum += assets[i];
        }

        return sum / assets.length;
    }
}