package BookStore;
import java.util.*;


public class Book {

    public String title;
    public double price;

    public Book(String title, double price){
        this.title = title;
        this.price = price;
    }    

    public String toString(){
        return String.format("Title: %s\nPrice: %.2f", title,price);
    }

    
}