import javafx.application.Application; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.beans.value.*;
import javafx.scene.control.*; 
import javafx.scene.layout.*;
import javafx.scene.text.*; 
import javafx.stage.Stage;  
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.*;
import java.lang.Math;


public class AutoLoanCalculator extends Application{

    @Override public void start(Stage stage){
        BorderPane root = new BorderPane(); 
        GridPane root2  = new GridPane(); // think of this as the container for sections(One,Two,Three,Four)
        root2.setGridLinesVisible(true); 

        Scene scene = new Scene(root);

//================ Section 1 ======================================

        Label paymentLabel   = new Label("Payment Information");
        Text totalLoanAmount = new Text("Total Loan Amount:     $0.0   ");
        Text monthlyPayment  = new Text("Total Monthly Payment: $0.0   ");
        Text totalPayment    = new Text("Total Payment:         $0.0   ");
        GridPane section1  =   new GridPane();

        paymentLabel.setFont(Font.font("Verdana",FontWeight.BOLD, 14)); //felt like changing the font for fun! :) 

        VBox sectOneBox = new VBox(8); // box holds all elements within the section

        sectOneBox.getChildren().addAll(paymentLabel,totalLoanAmount,monthlyPayment,totalPayment);

        section1.add(paymentLabel, 0 ,0);
        section1.add(totalLoanAmount, 0, 1);
        section1.add(monthlyPayment, 0 , 2);
        section1.add(totalPayment, 0 ,3);



//=================== section 2 =======================================

        Label loanTerm      = new Label("Loan Term");
        GridPane section2 = new GridPane();

        loanTerm.setFont( Font.font("Verdana",FontWeight.BOLD, 14) );

        RadioButton rb1 = new RadioButton("24 months");
        rb1.setUserData("0.07"); 
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("36 months");
        rb2.setUserData("0.06");
        RadioButton rb3 = new RadioButton("48 months");

        rb3.setUserData("0.055");
        RadioButton rb4 = new RadioButton("60 months");

        rb4.setUserData("0.05");
        ToggleGroup loanTermGroup = new ToggleGroup();

        rb1.setToggleGroup(loanTermGroup);
        rb2.setToggleGroup(loanTermGroup);
        rb3.setToggleGroup(loanTermGroup);
        rb4.setToggleGroup(loanTermGroup);

        VBox radioButtons = new VBox(4);
        radioButtons.getChildren().addAll(rb1,rb2,rb3,rb4);

        ArrayList<CheckBox> cBoxList = new ArrayList<CheckBox>();// list used to iterate through each selected checkbox 
        ArrayList<RadioButton> rButtonList = new ArrayList<RadioButton>();

        rButtonList.add(rb1);
        rButtonList.add(rb2);
        rButtonList.add(rb3);
        rButtonList.add(rb4);

        section2.add( loanTerm, 0, 0 );
        section2.add( radioButtons, 0 , 1 );

//========== section 3 =========================================

        GridPane section3 = new GridPane();

        Label financingInfo   = new Label( "Financing Information"  );
        Label basePrice       = new Label( "Base price: $"          );
        Label downPayment     = new Label( "Down Payment: $"        );
        Label salesTax        = new Label( "Sales Tax: %"           );

        financingInfo.setFont( Font.font( "Verdana",FontWeight.BOLD, 14 ) );

        VBox financeLabels = new VBox(12);
        VBox textFields    = new VBox(4);
        
        TextField bPrice   = new TextField( "0.0"  );
        TextField dPayment = new TextField( "0.0"  );
        TextField sTax     = new TextField( "7.0"  );

        financeLabels.getChildren().addAll( basePrice,downPayment,salesTax );
        textFields.getChildren().addAll( bPrice,dPayment,sTax );

        section3.add( financeLabels, 0 , 1 );
        section3.add( textFields, 1 , 1    );
        section3.add( financingInfo, 0 , 0  );

//============== Section 4 ==================================

        GridPane section4 = new GridPane();

        Label priceWithOptions = new Label( "Price with Options" );
        priceWithOptions.setFont( Font.font( "Verdana",FontWeight.BOLD, 14 ) );
        VBox checkBoxes = new VBox(5);

        CheckBox cBox1 = new CheckBox( "Auto Transmission:$1800" );
        CheckBox cBox2 = new CheckBox( "AntiLock Brake:$1200"    );
        CheckBox cBox3 = new CheckBox( "Sun Roof:$800"           );
        CheckBox cBox4 = new CheckBox( "Navigation System:$1350" );
        CheckBox cBox5 = new CheckBox( "Audio Package:$1550"     );

        cBox1.setUserData( "1800" );
        cBox2.setUserData( "1200" );
        cBox2.setSelected(  true  );
        cBox3.setUserData( "800"  );
        cBox4.setUserData( "1350" );
        cBox5.setUserData( "1550" );

        cBoxList.add( cBox1 ); 
        cBoxList.add( cBox2 );
        cBoxList.add( cBox3 );
        cBoxList.add( cBox4 );
        cBoxList.add( cBox5 );

        checkBoxes.getChildren().addAll( cBox1,cBox2,cBox3,cBox4,cBox5 );

        section4.add( priceWithOptions , 0 , 0 );
        section4.add( checkBoxes , 0 , 1       );

//============== section 5===============================

        GridPane section5   = new GridPane();
        
        Button calculateButton = new Button( "Calculate" );
        Button resetButton     = new Button( "Reset"     );
        Button exitButton      = new Button( "Exit"      );

        HBox buttonBox         = new HBox(5);

        buttonBox.getChildren().addAll( calculateButton,resetButton, exitButton );

        section5.add( buttonBox, 0, 0 );



//============= EVENT HANDLING =========================
        calculateButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle( ActionEvent e ){
                    double months          =   0 ;
                    double opCost          =   CalculateOptCost(   cBoxList             );
                    double baPrice         =   Double.parseDouble( bPrice.getText()     );
                    double doPayment       =   Double.parseDouble( dPayment.getText()   );
                    double saTax           =   Double.parseDouble( sTax.getText()       );

                    if(rb1.isSelected()){
                            months = 24;
                        }else if (rb2.isSelected() ){
                            months = 36;
                        }else if (rb3.isSelected() ){
                            months = 48;
                        }else if ( rb4.isSelected() ){
                            months = 60;
                        }

                    double tax = (( baPrice - doPayment) + opCost ) * saTax/100.0;//2824.5
                    System.out.printf("base price: %f\ndown payment : %f\n opcost: %f\n saTax: %f\nTax: %f\n\n",baPrice,doPayment,opCost,saTax, tax);
                    
                    double tla  =   (baPrice - doPayment +  opCost + tax);//43,174.5
                    double mint =   0.07 / 12;//.46% interest 
                    System.out.printf("monthly interest: %f\n\n",tla);
                    double mpay =   tla * mint * (Math.pow(1 + mint, months)) / (Math.pow(1 + mint, months) - 1);
                    System.out.printf("monthly payment: %f\n\n", mpay);
                    double tpay =   mpay * months + doPayment;
                    System.out.printf("total payment: %f\n\n", tpay);

                    totalLoanAmount.setText(String.format("Total Loan Amount:     $%.2f   ",tla));
                    monthlyPayment.setText(String.format("Total Monthly Payment:  $%.2f", mpay));
                    totalPayment.setText(String.format("Total Payment:            $%.2f   ",tpay));


                    
            }
        });


        resetButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                
                totalLoanAmount.setText("Total Loan Amount:      $0.0");
                monthlyPayment.setText("Total Monthly Payment:  $0.0");
                totalPayment.setText("Total Payment:          $0.0");

                for(CheckBox box: cBoxList){
                    box.setSelected(false);
                }

                cBox2.setSelected(true);

                for(RadioButton rb: rButtonList){
                    rb.setSelected(false);
                }                

                bPrice.setText("0.0");
                dPayment.setText("0.0");
                sTax.setText("7.0");
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                System.exit(0);
            }
        });
//      ====== Adding sections ==========

        root2.add( section1, 0, 0   );
        root2.add( section2, 1, 0   );
        root2.add( section3, 0, 1 );
        root2.add( section4, 1, 1  );
        
        root.setCenter( root2 );
        root.setBottom( section5 );
        stage.setScene( scene );
        stage.show();

        section2.setAlignment( Pos.CENTER ); 
        section3.setAlignment( Pos.CENTER ); 
        section4.setAlignment( Pos.CENTER ); 
        section5.setAlignment( Pos.CENTER );
    }

    public double CalculateOptCost( ArrayList<CheckBox> boxList ){

        //get user data and calculate sum 
        double optCost = 0;
        for( CheckBox cBox : boxList ){
            if( cBox.isSelected() ){
                optCost += Double.parseDouble( cBox.getUserData().toString() );
            }
        }

        return optCost;
    }
}