package Assignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StockManagement extends Application {
	
	static UserInfo user = new UserInfo();
	static ArrayList<Product> product = new ArrayList<Product>();
	static int max, i=0;

	@Override
	public void start (Stage primaryStage)
	{
		BorderPane pane = new BorderPane();
				
		VBox vb = new VBox(15);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets (5,5,5,5));
		
		Label dateTime = new Label();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{        	
                LocalDateTime now = LocalDateTime.now();
                dateTime.setText("Current Date and Time: " + formatter.format(now));
                dateTime.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20 ));
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();        
		
		DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.GRAY);
        
		Label welcome = new Label("Welcome To Stock Management System");
        welcome.setEffect(dropShadow);
		welcome.setFont(Font.font("Times New Roman",FontWeight.BOLD, 26));
		welcome.setAlignment(Pos.CENTER);
		welcome.setTextFill(Color.PURPLE);
		
		Label developer = new Label ("System Developer: \n" + "1. Keh Yi Qian \n" + "2. Lai Chin Hin\n" + "3. Leong Jing Li \n" + "4. Neo Ze Zheng" );		
		developer.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		developer.setAlignment(Pos.CENTER);
				
		Button close = new Button("Close");
		close.setPrefSize(80, 25);
		close.setOnAction(e -> primaryStage.close());
		
		Button start = new Button("Start");
		start.setPrefSize(80, 25);
		start.setOnAction(e -> userName(primaryStage) );
		
		HBox hb = new HBox(15);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(5,5,5,5));
		hb.getChildren().addAll(start, close);
		
		vb.getChildren().addAll(dateTime, welcome, developer, hb);
		
		pane.setCenter(vb);
		
		Scene scene = new Scene(pane, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}

	public static void userName(Stage primaryStage)
	{
		GridPane paneUser = new GridPane();
		paneUser.setAlignment(Pos.CENTER);
		paneUser.setPadding(new Insets(15));
		paneUser.setVgap(5);
		
		paneUser.add(new Label ("Please type your full name accord to first name and surname: "),0, 0);
		Label label1 = new Label ("*Please type the space between first name and surname");
		label1.setStyle("-fx-text-fill: red");
		Label next = new Label("Press enter to continue");
		next.setStyle("-fx-text-fill: green");
		paneUser.add(label1,0, 1);
		
		TextField tf = new TextField();
		tf.setPrefColumnCount(1);
		paneUser.add(tf, 0, 2);
	
		paneUser.add(next, 0, 3);
		
		tf.setOnAction(e -> {
			user.getUser(tf.getText());
			maxProduct(primaryStage);} );
		
		
		Scene scene = new Scene(paneUser, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void maxProduct(Stage primaryStage)
	{
		GridPane paneMax = new GridPane();
		paneMax.setAlignment(Pos.CENTER);
		paneMax.setPadding(new Insets(15));
		paneMax.setVgap(10);
		paneMax.setHgap(5);
		
		paneMax.add(new Label ("Welcome " + user.getName() + " enter to our SMS!"), 0, 0);
		paneMax.add(new Label ("Enter maximum number of product that you want to stock: "),0, 1);
		
		Label jump = new Label("*If user do not wish to insert product, can press 0 jump to the main menu.");
		jump.setStyle("-fx-text-fill: red");
		paneMax.add(jump,  0, 3);
		
		TextField tf1 = new TextField();
		tf1.setPrefColumnCount(1);
		paneMax.add(tf1, 0,4);
		
		Label next_invalid = new Label("Press enter to continue");
		next_invalid.setStyle("-fx-text-fill: green");
		paneMax.add(next_invalid, 0,5);
		
		
		tf1.setOnAction(e -> {						
			
			 String text = tf1.getText();

		     if (text.isEmpty())
		     {
		    	 next_invalid.setText("*Please enter a number!!! Type it again.");
		    	 next_invalid.setStyle("-fx-text-fill: red");
		    	 tf1.setText("");
		     } 
		     else 
		     {
		        try
		        {
		        	max = Integer.parseInt(text);
		        	 if (max >= 0)
		             {
		                 addProduct(primaryStage);
		                 return; //exit the method
		             } 
		             else
		             {
		            	 next_invalid.setText("*Number of product only accepts positive values (0 and above)!!! Type it again.");
		            	 next_invalid.setStyle("-fx-text-fill: red");
		            	 tf1.setText("");
		             }
		        }
		        catch (NumberFormatException e1)
		        {
		        	next_invalid.setText("*Invalid input! Please enter a number!!! Type it again.");
		        	next_invalid.setStyle("-fx-text-fill: red");
		        	tf1.setText("");
		        }
		        
		     }
		   });
				
		Scene scene = new Scene(paneMax, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void addProduct(Stage primaryStage)
	{
		
		if ( max != 0)
		{
			
			GridPane paneAdd = new GridPane();
			paneAdd.setAlignment(Pos.CENTER);
			paneAdd.setPadding(new Insets(15));
			paneAdd.setVgap(5);
			
			Label proType = new Label("Product Types: \n" + "1. Refrigerator \n" + "2. TV \n" + "3. Laptop \n" + "4. Washing Machine \n" + 
			"Please select the type for product " + i + " :");
			paneAdd.add(proType, 0, 0);
			
			TextField tf2 = new TextField();
			tf2.setPrefColumnCount(1);
			paneAdd.add(tf2, 0, 1);
			
			Label next_invalid1 = new Label("Press enter to continue");
			next_invalid1.setStyle("-fx-text-fill: green");
			
			tf2.setOnAction(e->{
				
				 String text = tf2.getText();
			     int numberPro;
			     
			     if (text.isEmpty())
			     {
			    	 next_invalid1.setText("*Please enter a number!!! Type it again.");
			    	 next_invalid1.setStyle("-fx-text-fill: red");
			    	 tf2.setText("");
			     } 
			     else 
			     {
			        try
			        {
			        	 numberPro = Integer.parseInt(text);
			             if (numberPro == 1)
			             {
			                 addRef(primaryStage);
			               
			             } 
			             else if (numberPro == 2)
			             {
			                 addTV(primaryStage);
			             } 
			             else if (numberPro == 3)
			             {
			                 addLaptop(primaryStage);
			             } 
			             else if (numberPro == 4)
			             {
			                 addWMachine(primaryStage);
			             } 
			             else
			             {
			            	 next_invalid1.setText("*Input values only accepts 1 to 4 !!! Type it again.");
			            	 next_invalid1.setStyle("-fx-text-fill: red");
			            	 tf2.setText("");
			             }
			        }
			        catch (NumberFormatException e2)
			        {
			        	next_invalid1.setText("*Invalid input! Please enter a number!!! Type it again.");
			        	next_invalid1.setStyle("-fx-text-fill: red");
			        	tf2.setText("");
			        }
			        i++;
			     }
			});
			
			paneAdd.add(next_invalid1, 0,3);
						
			Scene scene = new Scene(paneAdd, 600, 800);// pane, width, height
			primaryStage.setTitle("Stock Management System");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else mainMenu(primaryStage);
				
		
	}
	public static void addRef(Stage primaryStage)
	{
		GridPane paneAddRef = new GridPane();
		paneAddRef.setAlignment(Pos.CENTER);
		paneAddRef.setPadding(new Insets(15));
		paneAddRef.setVgap(5);
		
		paneAddRef.add(new Label ("Refrigerator name: "), 0, 0);
		TextField nameRef = new TextField();
		paneAddRef.add(nameRef, 1, 0);
		
		paneAddRef.add(new Label ("Door design: "), 0, 1);
		TextField doorRef = new TextField();
		paneAddRef.add(doorRef, 1, 1);
		
		paneAddRef.add(new Label ("Color: "), 0, 2);
		TextField colorRef = new TextField();
		paneAddRef.add(colorRef, 1, 2);
		
		paneAddRef.add(new Label ("Capacity (in Liters): "), 0, 3);
		TextField capacityRef = new TextField();
		paneAddRef.add(capacityRef, 1, 3);
		
		paneAddRef.add(new Label ("Quantity available in stock: "), 0, 4);
		TextField quantityRef = new TextField();
		paneAddRef.add(quantityRef, 1, 4);
		
		paneAddRef.add(new Label ("Price: "), 0, 5);
		TextField priceRef = new TextField();
		paneAddRef.add(priceRef, 1, 5);
		
		paneAddRef.add(new Label ("Item number: "), 0, 6);
		TextField numberRef = new TextField();
		paneAddRef.add(numberRef, 1, 6);
		
		Text invalid = new Text();
		paneAddRef.add(invalid, 0, 7);
		
		Button nextRef = new Button("Next");
		nextRef.setPrefSize(80, 25);
		nextRef.setOnAction( e -> {
			
			try 
			{
				if (duplicatedProNumber(Integer.parseInt(numberRef.getText())))
				{
					Product refrigerator = new Refrigerator(nameRef.getText(), 
							Double.parseDouble(priceRef.getText()),
							Integer.parseInt(quantityRef.getText()), 
							Integer.parseInt(numberRef.getText()),
							doorRef.getText(),
							colorRef.getText(), 
							Double.parseDouble(capacityRef.getText()));
					
					product.add(refrigerator);
					if(max !=0)
						max--;
					else mainMenu(primaryStage);
					addProduct(primaryStage) ;
					displayProductArray();
				}
				else 
				{
					invalid.setText("*Cannot insert the duplicated product item number!!!");
					invalid.setFill(Color.RED);
				}
					
			}
			catch (IllegalArgumentException ex)
			{
				invalid.setText("Error: " + ex.getMessage());
				invalid.setFill(Color.RED);
			}
		});
		paneAddRef.add(nextRef, 1, 8);
		GridPane.setHalignment(nextRef, HPos.RIGHT);
	
		Scene scene = new Scene(paneAddRef, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void addTV(Stage primaryStage)
	{
		GridPane paneAddTV = new GridPane();
		paneAddTV.setAlignment(Pos.CENTER);
		paneAddTV.setPadding(new Insets(15));
		paneAddTV.setVgap(5);
		
		paneAddTV.add(new Label ("TV name: "), 0, 0);
		TextField nameTV = new TextField();
		paneAddTV.add(nameTV, 1, 0);
		
		paneAddTV.add(new Label ("Screen Type: "), 0, 1);
		TextField screenTV = new TextField();
		paneAddTV.add(screenTV, 1, 1);
		
		paneAddTV.add(new Label ("Resolution: "), 0, 2);
		TextField resolutionTV = new TextField();
		paneAddTV.add(resolutionTV, 1, 2);
		
		paneAddTV.add(new Label ("Display size: "), 0, 3);
		TextField sizeTV = new TextField();
		paneAddTV.add(sizeTV, 1, 3);
		
		paneAddTV.add(new Label ("Quantity available in stock: "), 0, 4);
		TextField quantityTV = new TextField();
		paneAddTV.add(quantityTV, 1, 4);
		
		paneAddTV.add(new Label ("Price: "), 0, 5);
		TextField priceTV = new TextField();
		paneAddTV.add(priceTV, 1, 5);
		
		paneAddTV.add(new Label ("Item number: "), 0, 6);
		TextField numberTV = new TextField();
		paneAddTV.add(numberTV, 1, 6);
		
		Text invalid1 = new Text();
		paneAddTV.add(invalid1, 0, 7);
		
		Button nextRef = new Button("Next");
		nextRef.setPrefSize(80, 25);
		nextRef.setOnAction( e -> {
			
			try
			{
				if (duplicatedProNumber(Integer.parseInt(numberTV.getText())))
				{
					Product tv = new TV(nameTV.getText(), 
							Double.parseDouble(priceTV.getText()),
							Integer.parseInt(quantityTV.getText()), 
							Integer.parseInt(numberTV.getText()),
							screenTV.getText(),
							resolutionTV.getText(), 
							Double.parseDouble(sizeTV.getText()));
					
					product.add(tv);
					if(max !=0)
						max--;
					else mainMenu(primaryStage);					
					addProduct(primaryStage) ;
					displayProductArray();
				}
				else
				{
					invalid1.setText("Cannot insert the duplcated product item number!!!");
					invalid1.setFill(Color.RED);
				}
			}
			catch (IllegalArgumentException ex)
			{
				invalid1.setText("Error: " + ex.getMessage());
				invalid1.setFill(Color.RED);
			}
			
		});
		paneAddTV.add(nextRef, 1, 8);
		GridPane.setHalignment(nextRef, HPos.RIGHT);
		
		Scene scene = new Scene(paneAddTV, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void addLaptop(Stage primaryStage)
	{
		GridPane paneAddLaptop = new GridPane();
		paneAddLaptop.setAlignment(Pos.CENTER);
		paneAddLaptop.setPadding(new Insets(15));
		paneAddLaptop.setVgap(5);
		
		paneAddLaptop.add(new Label ("Laptop name: "), 0, 0);
		TextField nameLap = new TextField();
		paneAddLaptop.add(nameLap, 1, 0);
		
		paneAddLaptop.add(new Label ("Graphic Card: "), 0, 1);
		TextField graphicLap = new TextField();
		paneAddLaptop.add(graphicLap, 1, 1);
		
		paneAddLaptop.add(new Label ("Processor: "), 0, 2);
		TextField processorLap = new TextField();
		paneAddLaptop.add(processorLap, 1, 2);
		
		paneAddLaptop.add(new Label ("RAM: "), 0, 3);
		TextField ramLap = new TextField();
		paneAddLaptop.add(ramLap, 1, 3);
		
		paneAddLaptop.add(new Label ("Quantity available in stock: "), 0, 4);
		TextField quantityLap = new TextField();
		paneAddLaptop.add(quantityLap, 1, 4);
		
		paneAddLaptop.add(new Label ("Price: "), 0, 5);
		TextField priceLap = new TextField();
		paneAddLaptop.add(priceLap, 1, 5);
		
		paneAddLaptop.add(new Label ("Item number: "), 0, 6);
		TextField numberLap = new TextField();
		paneAddLaptop.add(numberLap, 1, 6);
		
		Text invalid = new Text();
		paneAddLaptop.add(invalid, 0, 7);
		
		Button nextLap = new Button("Next");
		nextLap.setPrefSize(80, 25);
		nextLap.setOnAction( e -> {
			
			try 
			{
				if (duplicatedProNumber(Integer.parseInt(numberLap.getText())))
				{
					Product laptop = new Laptop(nameLap.getText(), 
							Double.parseDouble(priceLap.getText()),
							Integer.parseInt(quantityLap.getText()), 
							Integer.parseInt(numberLap.getText()),
							graphicLap.getText(),
							processorLap.getText(), 
							Integer.parseInt(ramLap.getText()));
					
					product.add(laptop);
					if(max !=0)
						max--;
					else mainMenu(primaryStage);
					addProduct(primaryStage) ;
					displayProductArray();
				}
				else 
				{
					invalid.setText("*Cannot insert the duplicated product item number!!!");
					invalid.setFill(Color.RED);
				}
					
			}
			catch (IllegalArgumentException ex)
			{
				invalid.setText("Error: " + ex.getMessage());
				invalid.setFill(Color.RED);
			}
		});
		paneAddLaptop.add(nextLap, 1, 8);
		GridPane.setHalignment(nextLap, HPos.RIGHT);
	
		Scene scene = new Scene(paneAddLaptop, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void addWMachine(Stage primaryStage)
	{
		GridPane paneAddMachine = new GridPane();
		paneAddMachine.setAlignment(Pos.CENTER);
		paneAddMachine.setPadding(new Insets(15));
		paneAddMachine.setVgap(5);
		
		paneAddMachine.add(new Label ("Washing Machine name: "), 0, 0);
		TextField nameWMachine = new TextField();
		paneAddMachine.add(nameWMachine, 1, 0);
		
		paneAddMachine.add(new Label ("Machine's Design: "), 0, 1);
		TextField designWMachine = new TextField();
		paneAddMachine.add(designWMachine, 1, 1);
		
		paneAddMachine.add(new Label ("Spin Speed (High/Middle/Low): "), 0, 2);
		TextField speedWMachine = new TextField();
		paneAddMachine.add(speedWMachine, 1, 2);
		
		paneAddMachine.add(new Label ("Size: "), 0, 3);
		TextField sizeWMachine = new TextField();
		paneAddMachine.add(sizeWMachine, 1, 3);
		
		paneAddMachine.add(new Label ("Capacity (in Kg): "), 0, 4);
		TextField capacityWMachine = new TextField();
		paneAddMachine.add(capacityWMachine, 1, 4);
		
		paneAddMachine.add(new Label ("Quantity available in stock: "), 0, 5);
		TextField quantityWMachine = new TextField();
		paneAddMachine.add(quantityWMachine, 1, 5);
		
		paneAddMachine.add(new Label ("Price: "), 0, 6);
		TextField priceWMachine = new TextField();
		paneAddMachine.add(priceWMachine, 1, 6);
		
		paneAddMachine.add(new Label ("Item number: "), 0, 7);
		TextField numberWMachine = new TextField();
		paneAddMachine.add(numberWMachine, 1, 7);
		
		Text invalid = new Text();
		paneAddMachine.add(invalid, 0, 8);
		
		Button nextRef = new Button("Next");
		nextRef.setPrefSize(80, 25);
		nextRef.setOnAction( e -> {
			
			try 
			{
				if (duplicatedProNumber(Integer.parseInt(numberWMachine.getText())))
				{
					Product washingMachine = new WashingMachine(nameWMachine.getText(), 
							Double.parseDouble(priceWMachine.getText()),
							Integer.parseInt(quantityWMachine.getText()), 
							Integer.parseInt(numberWMachine.getText()),
							designWMachine.getText(),
							speedWMachine.getText(), 
							Integer.parseInt(sizeWMachine.getText()),					
							Double.parseDouble(capacityWMachine.getText()));
					
					product.add(washingMachine);
					if(max !=0)
						max--;
					else mainMenu(primaryStage);
					addProduct(primaryStage) ;
					displayProductArray();
				}
				else 
				{
					invalid.setText("*Cannot insert the duplicated product item number!!!");
					invalid.setFill(Color.RED);
				}
					
			}
			catch (IllegalArgumentException ex)
			{
				invalid.setText("Error: " + ex.getMessage());
				invalid.setFill(Color.RED);
			}
		});
		paneAddMachine.add(nextRef, 1, 9);
		GridPane.setHalignment(nextRef, HPos.RIGHT);
	
		Scene scene = new Scene(paneAddMachine, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void mainMenu(Stage primaryStage)
	{
		GridPane paneMenu = new GridPane();
		paneMenu.setAlignment(Pos.CENTER);
		paneMenu.setPadding(new Insets(15));
		paneMenu.setVgap(5);
		
		Label optionMenu = new Label("*********** Main Menu *********** \n" + 
									"1. View products \n" + "2. Add stock \n" + "3. Deduct stock \n" + "4. Discontinued product \n" + "0. Exit \n" + 
									"Please type a menu option: ");
		
		paneMenu.add(optionMenu, 0, 0);
		
		TextField tfOption = new TextField();
		tfOption.setPrefColumnCount(1);
		paneMenu.add(tfOption, 0, 1);
		
		Label invalidOption = new Label();
		paneMenu.add(invalidOption, 0, 2);
		invalidOption.setText("Press enter to continue");
		invalidOption.setStyle("-fx-text-Fill: green");
		
		tfOption.setOnAction( e -> {
			
			int option;
			try
			{
				option = Integer.parseInt(tfOption.getText());
				switch(option)
				{
					case 1:
						viewPro();
						invalidOption.setText("Press enter to continue");
						invalidOption.setStyle("-fx-text-Fill: green");
						tfOption.setText("");
						break;
					case 2:
						addStock();
						invalidOption.setText("Press enter to continue");
						invalidOption.setStyle("-fx-text-Fill: green");
						tfOption.setText("");
						break;
					case 3:
						deductStock();
						invalidOption.setText("Press enter to continue");
						invalidOption.setStyle("-fx-text-Fill: green");
						tfOption.setText("");
						break;
					case 4:
						setStatus();
						invalidOption.setText("Press enter to continue");
						invalidOption.setStyle("-fx-text-Fill: green");
						tfOption.setText("");
						break;
					case 0 :
						exit(primaryStage);
						break;
					default:
						invalidOption.setText("*Please insert the number within the range of 0 - 4!!!");
						invalidOption.setStyle("-fx-text-Fill: red");
						tfOption.setText("");
				}
			}
			catch (NumberFormatException ex)
			{
				invalidOption.setText("*Invalid input only accept a number!!!");
				invalidOption.setStyle("-fx-text-Fill: red");
				tfOption.setText("");
			}
		});
		
		Button back = new Button("back");
		back.setPrefSize(80, 25);
		back.setOnAction( e -> {
			
			maxProduct(primaryStage);
			
		});
		paneMenu.add(back, 0, 4);
		GridPane.setHalignment(back, HPos.RIGHT);
		
		Scene scene = new Scene(paneMenu, 600, 800);// pane, width, height
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void viewPro()
	{
		Stage stageViewPro = new Stage();
		
		GridPane paneViewPro = new GridPane();
		paneViewPro.setAlignment(Pos.TOP_LEFT);
		paneViewPro.setPadding(new Insets(15));
		paneViewPro.setVgap(5);
		
		StringBuilder RefContent = new StringBuilder();
		StringBuilder TVContent = new StringBuilder();
		StringBuilder LapContent = new StringBuilder();
		StringBuilder WMContent = new StringBuilder();
		int countRef = 0, countTV = 0, countLap = 0, countWM = 0;
		
		RefContent.append("Refrigerator \n\n");
		TVContent.append("\n\nTV \n\n");
		LapContent.append("\n\nLaptop \n\n");
		WMContent.append("\n\nWashing Machine \n\n");
		
		for (int i=0; i < product.size(); i++)
		{
			if (product.get(i) instanceof Refrigerator)
			{
				countRef++;
				RefContent.append("Refrigerator " + countRef + "\n" ).append(product.get(i).toString()).append("\n");
				
			}
			else if (product.get(i) instanceof TV)
			{
				countTV++;
				TVContent.append("TV " + countTV + "\n" ).append(product.get(i).toString()).append("\n");
			}
			else if (product.get(i) instanceof Laptop)
			{
				countLap++;
				LapContent.append("Laptop " + countLap + "\n" ).append(product.get(i).toString()).append("\n");
			}
			else if (product.get(i) instanceof WashingMachine)
			{
				countWM++;
				WMContent.append("Washing Machine " + countLap + "\n" ).append(product.get(i).toString()).append("\n");
			}
		}
		
		Text Refoverview = new Text("");
		Refoverview.setTextAlignment(TextAlignment.LEFT);
		paneViewPro.add(Refoverview, 0, 0);
		
		if (countRef > 0)
		{
			Refoverview.setText(RefContent.toString());
		}
		else
		{
			Refoverview.setText(RefContent.toString() + "\n" + "No items");
		}
		
		Text TVoverview = new Text("");
		TVoverview.setTextAlignment(TextAlignment.LEFT);
		paneViewPro.add(TVoverview, 0, 1);
		
		if (countTV > 0)
		{
			TVoverview.setText(TVContent.toString());
		}
		else
		{
			TVoverview.setText(TVContent.toString() + "\n" + "No items");
		}
		
		Text Lapoverview = new Text("");
		TVoverview.setTextAlignment(TextAlignment.LEFT);
		paneViewPro.add(Lapoverview, 0, 2);
		
		if (countLap > 0)
		{
			Lapoverview.setText(LapContent.toString());
		}
		else
		{
			Lapoverview.setText(LapContent.toString() + "\n" + "No items");
		}
		
		Text WMoverview = new Text("");
		WMoverview.setTextAlignment(TextAlignment.LEFT);
		paneViewPro.add(WMoverview, 0, 3);
		
		if (countWM > 0)
		{
			WMoverview.setText(WMContent.toString());
		}
		else
		{
			WMoverview.setText(WMContent.toString() + "\n" + "No items");
		}
		
		Button close = new Button("Close");
		close.setPrefSize(80, 25);
		close.setOnAction( e -> {
			stageViewPro.close();
		});
		paneViewPro.add(close, 0, 4);
		GridPane.setHalignment(close, HPos.RIGHT);
		
		ScrollPane spOverview =new ScrollPane(paneViewPro);
		
		Scene scene = new Scene(spOverview, 600, 600);// pane, width, height
		stageViewPro.setTitle("View Product");
		stageViewPro.setScene(scene);
		stageViewPro.show();
	}
	public static void addStock()
	{
		Stage stageAddStock = new Stage();
		
		BorderPane paneAddStock = new BorderPane();
		paneAddStock.setPadding(new Insets(15));
		
		HBox hbAddStock = new HBox(15);
		hbAddStock.getChildren().add(new Label ("Please select the product that you want to add stock"));
		
		ComboBox<String> productList= new ComboBox<String> ();
		for(Product products : product)
		{
			productList.getItems().add(Integer.toString(products.getNumber()));
		}
		productList.setValue("-");
		productList.setPrefWidth(100);
		productList.setPrefHeight(10);
		hbAddStock.getChildren().add(productList);
		
		paneAddStock.setTop(hbAddStock);
		
		Pane pAddStockInfo = new Pane();
		Text addStockInfo = new Text();
		pAddStockInfo.getChildren().add(addStockInfo);
		paneAddStock.setCenter(pAddStockInfo);
		BorderPane.setMargin(pAddStockInfo, new Insets(50, 0, 0, 0));
		
		VBox vbAddStock = new VBox(15);
		Label pro = new Label("Product name: " + "-");
		vbAddStock.getChildren().add(pro);
		vbAddStock.getChildren().add(new Label ("Please insert the number of stock that you want to add: "));
		
		TextField addAmount = new TextField();
		addAmount.setPrefColumnCount(1);
		vbAddStock.getChildren().add(addAmount);
		
		Label addInvalid = new Label();
		vbAddStock.getChildren().add(addInvalid);

		HBox hbOption = new HBox(15);
		Button add = new Button("Add");
		add.setPrefSize(80, 25);
		hbOption.getChildren().add(add);
		
		Button cancel = new Button("Cancel");
		cancel.setPrefSize(80, 25);
		hbOption.getChildren().add(cancel);
		
		hbOption.setAlignment(Pos.CENTER_RIGHT);
		vbAddStock.getChildren().add(hbOption);
		paneAddStock.setBottom(vbAddStock);
		
		productList.setOnAction(e -> {
			int i = productList.getItems().indexOf(productList.getValue());
			
			addStockInfo.setText(product.get(i).toString());
			
			pro.setText("Product name: " + product.get(i).getName());
			

			add.setOnAction( e1 -> {
				
				String amount = addAmount.getText();
			    int numberAdd;
			     
			    if (amount.isEmpty())
			    {
			    	addInvalid.setText("*Please enter a number!!!");
			    	addInvalid.setStyle("-fx-text-fill: red");
			    } 
			    else 
			    {
			    	
			    	try
			        {
			       	    numberAdd = Integer.parseInt(amount);
			            if (numberAdd >= 0)
			            {
			            	product.get(i).add_inventory(numberAdd);
			            	addStockInfo.setText(product.get(i).toString());
			            	if (product.get(i).isStatus())
			            	{
			            		addInvalid.setText("Quantity: " +  addAmount.getText() + ". Add successfully");
				            	addAmount.setText("");			            	
						    	addInvalid.setStyle("-fx-text-fill: green");
			            	}
			            	else
			            	{
			            		addInvalid.setText("*Discontinued product is not allowed to add the stock!!!");
			            		addAmount.setText("");		
						    	addInvalid.setStyle("-fx-text-fill: red");
			            	}
			            	
			            	
		                 } 
			            else
			            {
			            	addInvalid.setText("*Input values only accepts positive values (0 and above) !!!");
			            	addAmount.setText("");		
			            	addInvalid.setStyle("-fx-text-fill: red");
			            }
			        }
			        catch (NumberFormatException e2)
		            {
			    	   addInvalid.setText("*Invalid input! Please enter a number!!");
			    	   addInvalid.setStyle("-fx-text-fill: red");		        
			    	}
			    }   
			});
			
		});
		
		cancel.setOnAction( e -> {
			stageAddStock.close();
		});
		Scene scene = new Scene(paneAddStock, 600, 600);// pane, width, height
		stageAddStock.setTitle("Add Stock");
		stageAddStock.setScene(scene);
		stageAddStock.show();
	}
	
	public static void deductStock()
	{
		Stage stageDeductStock = new Stage();
		
		BorderPane paneDeductStock = new BorderPane();
		paneDeductStock.setPadding(new Insets(15));
		
		HBox hbDeductStock = new HBox(15);
		hbDeductStock.getChildren().add(new Label ("Please select the product that you want to deduct stock"));
		
		ComboBox<String> productList= new ComboBox<String> ();
		for(Product products : product)
		{
			productList.getItems().add(Integer.toString(products.getNumber()));
		}
		productList.setValue("-");
		productList.setPrefWidth(100);
		productList.setPrefHeight(10);
		hbDeductStock.getChildren().add(productList);
		
		paneDeductStock.setTop(hbDeductStock);
		
		Pane pDeductStockInfo = new Pane();
		Text DeductStockInfo = new Text();
		pDeductStockInfo.getChildren().add(DeductStockInfo);
		paneDeductStock.setCenter(pDeductStockInfo);
		BorderPane.setMargin(pDeductStockInfo, new Insets(50, 0, 0, 0));
		
		VBox vbDeductStock = new VBox(15);
		Label pro = new Label("Product name: " + "-");
		vbDeductStock.getChildren().add(pro);
		vbDeductStock.getChildren().add(new Label ("Please insert the number of stock that you want to deduct: "));
		
		TextField deductAmount = new TextField();
		deductAmount.setPrefColumnCount(1);
		vbDeductStock.getChildren().add(deductAmount);
		
		Label deductInvalid = new Label();
		vbDeductStock.getChildren().add(deductInvalid);

		HBox hbOption = new HBox(15);
		Button deduct = new Button("Deduct");
		deduct.setPrefSize(80, 25);
		hbOption.getChildren().add(deduct);
		
		Button cancel = new Button("Cancel");
		cancel.setPrefSize(80, 25);
		hbOption.getChildren().add(cancel);
		
		hbOption.setAlignment(Pos.CENTER_RIGHT);
		vbDeductStock.getChildren().add(hbOption);
		paneDeductStock.setBottom(vbDeductStock);
		
		productList.setOnAction(e -> {
			int i = productList.getItems().indexOf(productList.getValue());
			
			DeductStockInfo.setText(product.get(i).toString());
			
			pro.setText("Product name: " + product.get(i).getName());
			

			deduct.setOnAction( e1 -> {
				
				String amount = deductAmount.getText();
			    int numberDeduct;
			     
			    if (amount.isEmpty())
			    {
			    	deductInvalid.setText("*Please enter a number!!!");
			    	deductInvalid.setStyle("-fx-text-fill: red");
			    } 
			    else 
			    {
			    	
			        try
			        {
			       	    numberDeduct = Integer.parseInt(amount);
			            if (numberDeduct >= 0)
			            {
			            	
			            	DeductStockInfo.setText(product.get(i).toString());
			            	if (product.get(i).getQuantity() - numberDeduct <0)
			            	{
			            		deductInvalid.setText("*The number caused the stock become negative value !!!");
				            	deductAmount.setText("");
				            	deductInvalid.setStyle("-fx-text-fill: red");
				            }
			            	else
			            	{
			            		product.get(i).deduct_inventory(numberDeduct);
			            		DeductStockInfo.setText(product.get(i).toString());
			            		deductInvalid.setText("Quantity: " + deductAmount.getText() + ". Deduct successfully");
				            	deductAmount.setText("");
				            	deductInvalid.setStyle("-fx-text-fill: green");
			            	}
			            
			            	
			            	
		                 } 
			            else
			            {
			            	deductInvalid.setText("*Input values only accepts positive values (0 and above) !!!");
			            	deductAmount.setText("");
			            	deductInvalid.setStyle("-fx-text-fill: red");
			            }
			        }
			        catch (NumberFormatException e2)
		            {
			        	deductInvalid.setText("*Invalid input! Please enter a number!!");
			        	deductAmount.setText("");
			    	    deductInvalid.setStyle("-fx-text-fill: red");		        
			    	}
			       
			    }
			});
			
		});
		
		cancel.setOnAction( e -> {
			stageDeductStock.close();
		});
		Scene scene = new Scene(paneDeductStock, 600, 600);// pane, width, height
		stageDeductStock.setTitle("Deduct Stock");
		stageDeductStock.setScene(scene);
		stageDeductStock.show();
	}
	
	public static void setStatus()
	{
		Stage stageStatus = new Stage();
		
		BorderPane paneStatus = new BorderPane();
		paneStatus.setPadding(new Insets(15));
		
		HBox hbStatus = new HBox(15);
		hbStatus.getChildren().add(new Label ("Please select the product that you want to set status"));
		
		ComboBox<String> productList= new ComboBox<String> ();
		for(Product products : product)
		{
			productList.getItems().add(Integer.toString(products.getNumber()));
		}
		productList.setValue("-");
		productList.setPrefWidth(100);
		productList.setPrefHeight(10);
		hbStatus.getChildren().add(productList);
		
		paneStatus.setTop(hbStatus);
		BorderPane.setAlignment(hbStatus, Pos.CENTER);
		
		Pane pStockInfo = new Pane();
		Text StockInfo = new Text();
		pStockInfo.getChildren().add(StockInfo);
		paneStatus.setCenter(pStockInfo);
		BorderPane.setMargin(pStockInfo, new Insets(50, 0, 0, 0));
		BorderPane.setAlignment(pStockInfo, Pos.CENTER);
		
		GridPane gpStatus = new GridPane();
		gpStatus.setPadding(new Insets(15));
		gpStatus.setVgap(20);
		Label pro = new Label("Product name: " + "-");
		gpStatus.add(pro, 0, 0);
		
		HBox hbSetStatus = new HBox(15);
		hbSetStatus.getChildren().add(new Label ("Set Status : "));
		
		RadioButton rbActive = new RadioButton("Active");
		
		
		RadioButton rbDiscontinues = new RadioButton("Discontinues");
		
		ToggleGroup tg = new ToggleGroup();
		rbActive.setToggleGroup(tg);
		rbDiscontinues.setToggleGroup(tg);
		
		hbSetStatus.getChildren().addAll(rbActive, rbDiscontinues);
		gpStatus.add(hbSetStatus, 1, 1);
		GridPane.setHalignment(hbSetStatus, HPos.CENTER);
		
		
		Button cancel = new Button("Cancel");
		cancel.setPrefSize(80, 25);
		gpStatus.add(cancel, 1, 2);
		GridPane.setHalignment(cancel, HPos.CENTER);
		BorderPane.setAlignment(gpStatus, Pos.CENTER);
		
		paneStatus.setBottom(gpStatus);
		
		productList.setOnAction(e -> {
			
			rbActive.setSelected(false);
			rbDiscontinues.setSelected(false);
			
			int i = productList.getItems().indexOf(productList.getValue());
			
			StockInfo.setText(product.get(i).toString());
			
			pro.setText("Product name: " + product.get(i).getName());
			

			rbActive.setOnAction( e1 -> {
				if (rbActive.isSelected())
				{
					product.get(i).setStatus(true);
					StockInfo.setText(product.get(i).toString());
				}
			});
			
			rbDiscontinues.setOnAction( e1 -> {
				if (rbDiscontinues.isSelected())
				{
					product.get(i).setStatus(false);
					StockInfo.setText(product.get(i).toString());
				}
			});
			
		});
		
		cancel.setOnAction( e -> {
			stageStatus.close();
		});
		Scene scene = new Scene(paneStatus, 600, 600);// pane, width, height	
		BorderPane.setAlignment(paneStatus, Pos.CENTER);
		stageStatus.setTitle("Set Status");
		stageStatus.setScene(scene);
		stageStatus.show();
	}
	
	public static void exit(Stage primaryStage)
	{
		primaryStage.close();
		
		Stage id = new Stage();
		
		GridPane userInfo = new GridPane();
		userInfo.setVgap(20);
		Text name = new Text("User name\t: " + user.getName());
		Text ID = new Text("User ID\t\t: " + user.getUserID());
		userInfo.add(name, 0, 0);
		userInfo.add(ID, 0, 1);
		
		Button ok = new Button("OK");
		ok.setPrefSize(80, 25);
		ok.setOnAction( e -> {
			id.close();
		});
		
		userInfo.add(ok, 0, 2);
		GridPane.setHalignment(ok, HPos.CENTER);
		userInfo.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(userInfo, 300, 300);
		id.setTitle("Exit");
		id.setScene(scene);
		id.show();
		
		
	}
	
	public static boolean duplicatedProNumber(int proNumber)
	{
		for(Product products : product)
		{
			if (products.getNumber() == proNumber)
			{
				return false;
			}
		
		}
		return true;
	}
	
	public static void displayProductArray()
	{
		Stage secondStage = new Stage();
		
		GridPane productArray = new GridPane();
		productArray.setAlignment(Pos.TOP_LEFT);
		productArray.setPadding(new Insets(15));
		productArray.setHgap(15);
		productArray.setVgap(15);
		
		Label updated = new Label("Updated Successfully!!");
		updated.setStyle("-fx-font-weight: bold; -fx-font-size: 18pt; -fx-font-posture: italic; -fx-text-fill: green");
		productArray.add(updated, 0, 0);
		GridPane.setHalignment(updated, HPos.CENTER);
		
		productArray.add(new Text("Index of product array "), 0, 1);
		productArray.add(new Text("Name of the product "), 1, 1);
		for (int i=0; i<product.size(); i++)
		{
			if (product.get(i) instanceof Refrigerator)
			{
				productArray.add(new Text(Integer.toString(i)), 0, i+2);
				productArray.add(new Text("Refrigerator"), 1, i+2);
				
			}
			else if (product.get(i) instanceof TV)
			{
				productArray.add(new Text(Integer.toString(i)), 0, i+2);
				productArray.add(new Text("TV"), 1, i+2);
			}
			else if (product.get(i) instanceof Laptop)
			{
				productArray.add(new Text(Integer.toString(i)), 0, i+2);
				productArray.add(new Text("Laptop"), 1, i+2);
			}
			else if (product.get(i) instanceof WashingMachine)
			{
				productArray.add(new Text(Integer.toString(i)), 0, i+2);
				productArray.add(new Text("Washing Machine"), 1, i+2);
			}
		}
		
		Button close = new Button("Close");
		close.setPrefSize(80, 25);
		close.setOnAction( e -> {
			secondStage.close();
		});
		productArray.add(close, 1, product.size()+3);
		GridPane.setHalignment(close, HPos.LEFT);
		
		ScrollPane scArray = new ScrollPane(productArray);
		
		Scene secondScene = new Scene(scArray, 600, 300);
		secondStage.setTitle("Contents of product array");
		secondStage.setScene(secondScene);	
		secondStage.show();
		
	}
	public static void main(String[] args)
	{
		launch(args);
	}

}