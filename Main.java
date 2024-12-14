package application;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button; // Correct import for JavaFX Button
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	static int loadBufferSize ;
    static int storeBufferSize;
    static int intAddSubSize;
    static int intMulDivSize;
    
    static int floatStoreLatency;
    static int intStoreLatency;
    static int floatLoadLatency;
    static int intLoadLatency;
    static int floatAddLatency;
    static int intAddLatency;
    static int floatDivLatency;
    static int intDivLatency;
    static int floatMulLatency;
    static int intMulLatency;
    static int floatSubLatency;
    static int intSubLatency;
    static int branchLatency;
    static int cacheBlockSize;
    static long cacheSize;
    static StringBuilder instructions = new StringBuilder();
    static StringBuilder regFile = new StringBuilder();
    static TomasuloAlgorithmLogic tomasulo=new TomasuloAlgorithmLogic();
    
    
    public void start(Stage primaryStage) {
        try {
            // Create a BorderPane as the root layout
            BorderPane root = new BorderPane();
            // Create a JavaFX Button
            Button button = new Button("DIVE DEEPER!");
            root.setCenter(button);
            button.setOnAction(e->chooseBufferSize(primaryStage));
            // Create a Scene
            Scene scene = new Scene(root, 1000, 800);
            // Add the CSS file (optional)
            scene.getStylesheets().add(getClass().getResource("start.css").toExternalForm());
            // Set up the Stage
            primaryStage.setTitle("Tomasulo Architecture Simulator");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void chooseBufferSize(Stage stage) {	
        Label title = new Label("Please choose your reservation station size");
        title.getStyleClass().add("title-label");
        Label loadBufferLabel = new Label("Load Buffer Size:");
        TextField loadBufferInput = new TextField();
        loadBufferInput.setPromptText("Enter size...");
        Label storeBufferLabel = new Label("Store Buffer Size:");
        TextField storeBufferInput = new TextField();
        storeBufferInput.setPromptText("Enter size...");    
        Label intAddSubLabel = new Label("Integer Add/Sub Buffer Size:");
        TextField intAddSubInput = new TextField();
        intAddSubInput.setPromptText("Enter size...");
        Label intMulDivLabel = new Label("Integer Multiply/Divide Buffer Size:");
        TextField intMulDivInput = new TextField();
        intMulDivInput.setPromptText("Enter size...");    
        HBox loadBufferBox = new HBox(10, loadBufferLabel, loadBufferInput);
        HBox storeBufferBox = new HBox(10, storeBufferLabel, storeBufferInput);
        HBox intAddSubBox = new HBox(10, intAddSubLabel, intAddSubInput);
        HBox intMulDivBox = new HBox(10, intMulDivLabel, intMulDivInput);
          
        VBox form = new VBox(15, loadBufferBox, storeBufferBox, intAddSubBox, intMulDivBox);
        form.setStyle("-fx-alignment: center;");        
        Button button = new Button("Confirm");       
        VBox root = new VBox(20, title, form,button); 
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");        
        button.setOnAction(e -> {
            try {
                // Parse the input values into integers
                 loadBufferSize = Integer.parseInt(loadBufferInput.getText());
                 storeBufferSize = Integer.parseInt(storeBufferInput.getText());
                 intAddSubSize = Integer.parseInt(intAddSubInput.getText());
                 intMulDivSize = Integer.parseInt(intMulDivInput.getText());               
                if(loadBufferSize>3 || storeBufferSize>3 || intAddSubSize>3 || intMulDivSize>3) {               	
                	throw new NumberFormatException("Maximum Buffer Size is 3");
                }
                // Print the stored values (You can store them elsewhere as needed)
                tomasulo.setAddSubReservationStationsize(intAddSubSize);
                tomasulo.setMulDivReservationStationsize(intMulDivSize);
                tomasulo.setLoadReservationStationsize(loadBufferSize);
                tomasulo.setStoreReservationStationsize(storeBufferSize);
                
                  
                loadLatency(stage);
            } catch (NumberFormatException ex) {
            	System.out.println(ex.getMessage());
                System.out.println("Please enter valid integer values!");
            }
        });
       
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(Main.class.getResource("chooseBufferSize.css").toExternalForm());
        stage.setScene(scene);
        stage.show();    	
    }    
    


    
    public static void loadLatency(Stage stage) {
    	
    	Label title  = new Label("Please enter latency for each type of instruction");
    	
    
    	
     
        

        // Float Store
        Label floatStoreLabel = new Label("Float Store Latency:");
        TextField floatStoreInput = new TextField();
        floatStoreInput.setPromptText("Enter latency...");
        HBox floatStore = new HBox(10,floatStoreLabel,floatStoreInput);
        
       
        
        // Integer Store
        Label intStoreLabel = new Label("Integer Store Latency:");
        TextField intStoreInput = new TextField();
        intStoreInput.setPromptText("Enter latency...");
        HBox intStore = new HBox(10,intStoreLabel,intStoreInput);

        // Float Load
        Label floatLoadLabel = new Label("Float Load Latency:");
        TextField floatLoadInput = new TextField();
        floatLoadInput.setPromptText("Enter latency...");
        HBox floatLoad = new HBox(10,floatLoadLabel,floatLoadInput);

        // Integer Load
        Label intLoadLabel = new Label("Integer Load Latency:");
        TextField intLoadInput = new TextField();
        intLoadInput.setPromptText("Enter latency...");
        HBox intLoad = new HBox(10,intLoadLabel,intLoadInput);

        // Float Add
        Label floatAddLabel = new Label("Float Add Latency:");
        TextField floatAddInput = new TextField();
        floatAddInput.setPromptText("Enter latency...");
        HBox floatAdd = new HBox(10,floatAddLabel,floatAddInput);

        // Integer Add
        Label intAddLabel = new Label("Integer Add Latency:");
        TextField intAddInput = new TextField();
        intAddInput.setPromptText("Enter latency...");
        HBox intAdd = new HBox(10,intAddLabel,intAddInput);
        // Float Division
        Label floatDivLabel = new Label("Float Division Latency:");
        TextField floatDivInput = new TextField();
        floatDivInput.setPromptText("Enter latency...");
        HBox floatDiv = new HBox(10,floatDivLabel,floatDivInput);

     // Integer Division
        Label intDivLabel = new Label("Integer Division Latency:");
        TextField intDivInput = new TextField();
        intDivInput.setPromptText("Enter latency...");
        HBox intDivRow = new HBox(10, intDivLabel, intDivInput);

        // Float Multiplication
        Label floatMulLabel = new Label("Float Multiplication Latency:");
        TextField floatMulInput = new TextField();
        floatMulInput.setPromptText("Enter latency...");
        HBox floatMulRow = new HBox(10, floatMulLabel, floatMulInput);

        // Integer Multiplication
        Label intMulLabel = new Label("Integer Multiplication Latency:");
        TextField intMulInput = new TextField();
        intMulInput.setPromptText("Enter latency...");
        HBox intMulRow = new HBox(10, intMulLabel, intMulInput);

        // Float Subtraction
        Label floatSubLabel = new Label("Float Subtraction Latency:");
        TextField floatSubInput = new TextField();
        floatSubInput.setPromptText("Enter latency...");
        HBox floatSubRow = new HBox(10, floatSubLabel, floatSubInput);

        // Integer Subtraction
        Label intSubLabel = new Label("Integer Subtraction Latency:");
        TextField intSubInput = new TextField();
        intSubInput.setPromptText("Enter latency...");
        HBox intSubRow = new HBox(10, intSubLabel, intSubInput);

        // Branches
        Label branchLabel = new Label("Branches Latency:");
        TextField branchInput = new TextField();
        branchInput.setPromptText("Enter latency...");
        HBox branchRow = new HBox(10, branchLabel, branchInput);
        
        Button b = new Button("next");
        b.setOnAction(e->{
        	try {
        	
        	    floatStoreLatency = Integer.parseInt(floatStoreInput.getText());
        	    intStoreLatency = Integer.parseInt(intStoreInput.getText());
        	    floatLoadLatency = Integer.parseInt(floatLoadInput.getText());
        	    intLoadLatency = Integer.parseInt(intLoadInput.getText());
        	    floatAddLatency = Integer.parseInt(floatAddInput.getText());
        	    intAddLatency = Integer.parseInt(intAddInput.getText());
        	    floatDivLatency = Integer.parseInt(floatDivInput.getText());
        	    intDivLatency = Integer.parseInt(intDivInput.getText());
        	    floatMulLatency = Integer.parseInt(floatMulInput.getText());
        	    intMulLatency = Integer.parseInt(intMulInput.getText());
        	    floatSubLatency = Integer.parseInt(floatSubInput.getText());
        	    intSubLatency = Integer.parseInt(intSubInput.getText());
        	    branchLatency = Integer.parseInt(branchInput.getText());

        	  
        	    if (floatStoreLatency == 0 || intStoreLatency == 0 ||
        	        floatLoadLatency == 0 || intLoadLatency == 0 ||
        	        floatAddLatency == 0 || intAddLatency == 0 ||
        	        floatDivLatency == 0 || intDivLatency == 0 ||
        	        floatMulLatency == 0 || intMulLatency == 0 ||
        	        floatSubLatency == 0 || intSubLatency == 0 ||
        	        branchLatency == 0) {

        	        throw new NumberFormatException("Please enter valid non-zero latency values.");
        	    }
        	    tomasulo.setFloatAddLatency(floatAddLatency);
        	    tomasulo.setIntAddLatency(intAddLatency);
        	    tomasulo.setFloatSubLatency(floatSubLatency);
        	    tomasulo.setIntSubLatency(intSubLatency);
        	    tomasulo.setFloatMulLatency(floatMulLatency);
        	    tomasulo.setIntMulLatency(intMulLatency);
        	    tomasulo.setFloatDivLatency(floatDivLatency);
        	    tomasulo.setIntDivLatency(intDivLatency);
        	    tomasulo.setFloatLoadLatency(floatLoadLatency);
        	    tomasulo.setIntLoadLatency(intLoadLatency);
        	    tomasulo.setFloatStoreLatency(floatStoreLatency);
        	    tomasulo.setIntStoreLatency(intStoreLatency);
        	   
        	    
        	    

        	    
        	    

        	    cacheProperties(stage);
        	
        	} catch (NumberFormatException ex) {
        	    System.out.println(ex.getMessage());
        	    System.out.println("Please enter valid integer values!");
        	}
       });
        
        

        VBox root = new VBox( 
        	    title, 
        	    floatStore, 
        	    intStore, 
        	    floatLoad, 
        	    intLoad, 
        	    floatAdd, 
        	    intAdd, 
        	    floatDiv, 
        	    intDivRow, 
        	    floatMulRow, 
        	    intMulRow, 
        	    floatSubRow, 
        	    intSubRow, 
        	    branchRow,b
        	);


        floatStoreLabel.getStyleClass().add("instruction-label");
        intStoreLabel.getStyleClass().add("instruction-label");
        floatLoadLabel.getStyleClass().add("instruction-label");
        intLoadLabel.getStyleClass().add("instruction-label");
        floatAddLabel.getStyleClass().add("instruction-label");
        intAddLabel.getStyleClass().add("instruction-label");
        floatDivLabel.getStyleClass().add("instruction-label");
        intDivLabel.getStyleClass().add("instruction-label");
        floatMulLabel.getStyleClass().add("instruction-label");
        intMulLabel.getStyleClass().add("instruction-label");
        floatSubLabel.getStyleClass().add("instruction-label");
        intSubLabel.getStyleClass().add("instruction-label");
        branchLabel.getStyleClass().add("instruction-label");
        // Scene and Stage setup
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(Main.class.getResource("chooseLatency.css").toExternalForm());

        stage.setTitle("Latency Input");
        stage.setScene(scene);
        stage.show();	
    }
    
    public static void cacheProperties(Stage stage) {

    	
    	 Label title = new Label("Choose Cache Configuration");
         title.getStyleClass().add("title-label");

      
         Label blockSizeLabel = new Label("Cache Block Size:");
         blockSizeLabel.getStyleClass().add("label");
         
         ComboBox<String> blockSizeDropdown = new ComboBox<>();
         blockSizeDropdown.getItems().addAll("8 bytes", "16 bytes", "32 bytes", "64 bytes", "128 bytes", "256 bytes");
         blockSizeDropdown.setPromptText("Select Block Size");
         blockSizeDropdown.getStyleClass().add("dropdown");

        
         Label cacheSizeLabel = new Label("Cache Size:");
         cacheSizeLabel.getStyleClass().add("label");
         
         ComboBox<String> cacheSizeDropdown = new ComboBox<>();
         cacheSizeDropdown.getItems().addAll(
                 "4 KB", "8 KB", "16 KB", "32 KB", "64 KB", 
                 "128 KB", "256 KB", "512 KB", "1 MB", "2 MB", 
                 "4 MB", "8 MB", "16 MB", "32 MB", "64 MB");
         cacheSizeDropdown.setPromptText("Select Cache Size");
         cacheSizeDropdown.getStyleClass().add("dropdown");
         
         Label label = new Label();

         Button b = new Button("next");
         b.setOnAction(e->{
        	 
        	 String blockSize = blockSizeDropdown.getValue();
        	 if(blockSize!=null) {
        		 cacheBlockSize = Integer.parseInt(blockSize.split(" ")[0]);
        		 label.setText("");
        		 
        	 }
        	 else {
        		 label.setText("please enter cache block size");
        		 return ;
        		 
        	 }
        		
        	 
        	 String cacheSizeString = cacheSizeDropdown.getValue();
        	 System.out.println(cacheSizeString);
        	 if(cacheSizeString!=null) {
        		 label.setText("");
        		 
        		 int number = Integer.parseInt(cacheSizeString.split(" ")[0]);
        		 System.out.println(number);
        		 String units = cacheSizeString.split(" ")[1];
        		 
        		 switch (units){
        		 case "KB": cacheSize= number * 1024L; break;
        		 case "MB": cacheSize = number * 1024L * 1024L;break;
        		 }
        	 }
        	 else {
        		 label.setText("please enter cache size");
        		 return ;
        	 }
        	 
        	 
             System.out.println("Block Size (bytes): " + cacheBlockSize);
             System.out.println("Cache Size (bytes): " + cacheSize);
             tomasulo.setCacheBlockSize(cacheBlockSize);
             tomasulo.setCachesize(cacheSize);
             tomasulo.initReservationStations();
         	tomasulo.loadInstructions("src/instructions.txt");
         	tomasulo.loadRegisters();
         	
         	for(Instruction  i : tomasulo.getInstructionQueue()) {
         		System.out.println(i);
         	}
         	drawTomasuloArchitecture(stage,tomasulo.getInstructionQueue());
        	 
         });
      
         VBox root = new VBox(15, title, blockSizeLabel, blockSizeDropdown, cacheSizeLabel, cacheSizeDropdown,b,label);
         root.getStyleClass().add("container");

        
         Scene scene = new Scene(root, 1000, 800);
         scene.getStylesheets().add(Main.class.getResource("cacheProperties.css").toExternalForm());
         stage.setTitle("Cache Properties");
         stage.setScene(scene);
         stage.show();
    }
    
    
    public static void drawTomasuloArchitecture(Stage stage, List<Instruction> instructionQueue) {
        // Title for the Tomasulo Architecture Visualization
        Label title = new Label("Cycle: " + tomasulo.getCycle());
        title.getStyleClass().add("title-label");

        // Instruction Queue Visualization
        TableView<Instruction> instructionQueueTable = createInstructionQueueTable(instructionQueue);

        // Reservation Stations Visualization
        VBox loadBuffer = drawReservationStations("Load Buffer", tomasulo.getLoadReservationStation().getStations());
        VBox storeBuffer = drawReservationStations("Store Buffer", tomasulo.getStoreReservationStation().getStations());
        VBox intAddSub = drawReservationStations("Int Add/Sub", tomasulo.getAddSubReservationStation().getStations());
        VBox intMulDiv = drawReservationStations("Int Mul/Div", tomasulo.getMulDivReservationStation().getStations());

        // Register File Visualization
        VBox registerFile = drawRegisterFile(tomasulo.getFloatRegfile(), tomasulo.getIntegerRegFile());

        // Bus Visualization
        VBox busBlock = drawBus(tomasulo.getDataBus());

        // Cache Visualization
     
        // Next Cycle Button
        Button nextCycleButton = new Button("Next Cycle");
        nextCycleButton.setOnAction(event -> {
            tomasulo.tomasulo(); // Advance cycle and update logic

            // Update only the necessary UI components
            title.setText("Cycle: " + tomasulo.getCycle());
            instructionQueueTable.setItems(FXCollections.observableArrayList(tomasulo.getInstructionQueue()));
            instructionQueueTable.refresh();

            loadBuffer.getChildren().setAll(drawReservationStations("Load Buffer", tomasulo.getLoadReservationStation().getStations()).getChildren());
            storeBuffer.getChildren().setAll(drawReservationStations("Store Buffer", tomasulo.getStoreReservationStation().getStations()).getChildren());
            intAddSub.getChildren().setAll(drawReservationStations("Int Add/Sub", tomasulo.getAddSubReservationStation().getStations()).getChildren());
            intMulDiv.getChildren().setAll(drawReservationStations("Int Mul/Div", tomasulo.getMulDivReservationStation().getStations()).getChildren());

            registerFile.getChildren().setAll(drawRegisterFile(tomasulo.getFloatRegfile(), tomasulo.getIntegerRegFile()).getChildren());
            busBlock.getChildren().setAll(drawBus(tomasulo.getDisplayBus()).getChildren());
            
        });

        // Layout
        VBox topSection = new VBox(10, title, instructionQueueTable);
        topSection.setStyle("-fx-padding: 10; -fx-alignment: center;");

        HBox reservationStations = new HBox(10, loadBuffer, storeBuffer, intAddSub, intMulDiv);
        reservationStations.setStyle("-fx-padding: 10; -fx-alignment: center;");

        VBox bottomSection = new VBox(10, busBlock, nextCycleButton); // Add the button below the bus block
        bottomSection.setStyle("-fx-padding: 10; -fx-alignment: center;");

        BorderPane layout = new BorderPane();
        layout.setTop(topSection);
        layout.setCenter(reservationStations);
        layout.setLeft(registerFile); // Add Register File to the left
       
        layout.setBottom(bottomSection); // Add Bottom Section (Bus + Button)

        // Scene and Stage setup
        Scene scene = new Scene(layout, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }








    @SuppressWarnings("unchecked")
    private static TableView<Instruction> createInstructionQueueTable(List<Instruction> instructionQueue) {
        TableView<Instruction> tableView = new TableView<>();

        // Define columns
        TableColumn<Instruction, String> opColumn = new TableColumn<>("Operation");
        opColumn.setCellValueFactory(new PropertyValueFactory<>("type")); // Maps to 'type'
        opColumn.setPrefWidth(100);

        TableColumn<Instruction, Integer> operand1Column = new TableColumn<>("Operand1");
        operand1Column.setCellValueFactory(new PropertyValueFactory<>("operand1Register")); // Direct mapping
        operand1Column.setPrefWidth(80);

        TableColumn<Instruction, Integer> operand2Column = new TableColumn<>("Operand2");
        operand2Column.setCellValueFactory(new PropertyValueFactory<>("operand2Register")); // Direct mapping
        operand2Column.setPrefWidth(80);

        TableColumn<Instruction, Integer> targetColumn = new TableColumn<>("Target");
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("TargetRegister")); // Direct mapping
        targetColumn.setPrefWidth(80);

        TableColumn<Instruction, Long> immediateColumn = new TableColumn<>("Immediate");
        immediateColumn.setCellValueFactory(new PropertyValueFactory<>("immediate")); // Direct mapping
        immediateColumn.setPrefWidth(100);

        // Add columns to TableView
        tableView.getColumns().addAll(opColumn, operand1Column, operand2Column, targetColumn, immediateColumn);

        // Populate TableView with the instructionQueue data
        ObservableList<Instruction> data = FXCollections.observableArrayList(instructionQueue);
        tableView.setItems(data);

        // Adjust table size
        tableView.setPrefWidth(450); // Adjusted to fit content
        tableView.setPrefHeight(Math.min(20 * data.size(), 200)); // Adjusted height

        tableView.setMinHeight(100); // Minimum height
        tableView.setMaxHeight(200); // Maximum height

        return tableView;
    }


    private static VBox drawReservationStations(String title, ReservationStationE [] reservationStation) {
        Label sectionTitle = new Label(title);
        sectionTitle.setStyle("-fx-font-size: 14px; -fx-padding: 5px; -fx-font-weight: bold;");

        // Create a GridPane to arrange reservation stations in rows and columns
        GridPane grid = new GridPane();
        grid.setHgap(5); // Horizontal gap between cells
        grid.setVgap(5); // Vertical gap between cells
        grid.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Add reservation stations to the grid
        for (int i = 0; i < reservationStation.length; i++) {
            // Create a VBox for each reservation station
            VBox stationBox = new VBox(2); // Vertical spacing between elements
            stationBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 5; -fx-alignment: center;");

            // Add fields of the reservation station
            Label opLabel = new Label("Op: " + (reservationStation[i].getOp() != null ? reservationStation[i].getOp() : "-"));
            Label timeLabel = new Label("Time: " + reservationStation[i].getTime());
            Label tagLabel = new Label("Tag: " + (reservationStation[i].getTag() != null ? reservationStation[i].getTag() : "-"));
            Label busyLabel = new Label("Busy: " + (reservationStation[i].isBusy() ? "1" : "0"));
            Label vjLabel = new Label("Vj: " + (reservationStation[i].getVj() != null ? reservationStation[i].getVj() : "-"));
            Label vkLabel = new Label("Vk: " + (reservationStation[i].getVk() != null ? reservationStation[i].getVk() : "-"));
            Label qjLabel = new Label("Qj: " + (reservationStation[i].getQj() != null ? reservationStation[i].getQj() : "-"));
            Label qkLabel = new Label("Qk: " + (reservationStation[i].getQk() != null ? reservationStation[i].getQk() : "-"));

            // Add all labels to the station box
            stationBox.getChildren().addAll(opLabel, timeLabel, tagLabel, busyLabel, vjLabel, vkLabel, qjLabel, qkLabel);

            // Add the station box to the grid
            grid.add(stationBox, i % 3, i / 3); // Arrange in 3 columns per row
        }

        // Wrap the grid in a VBox with a title
        VBox layout = new VBox(10, sectionTitle, grid);
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        return layout;
    }

    @SuppressWarnings("unused")
	private static VBox drawRegisterFile(ArrayList<FloatRegFile> floatRegFile, ArrayList<IntRegFile> integerRegFile) {
        // Title for the Register File
        Label registerFileTitle = new Label("Register File");
        registerFileTitle.setStyle("-fx-font-size: 14px; -fx-padding: 5px; -fx-font-weight: bold;");

        // Create a GridPane to arrange registers in a grid
        GridPane grid = new GridPane();
        grid.setHgap(5); // Horizontal gap between cells
        grid.setVgap(5); // Vertical gap between cells
        grid.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Add Integer Registers (R0-R31)
        for (int i = 0; i < integerRegFile.size(); i++) {
            IntRegFile reg = integerRegFile.get(i);

            // Create a VBox for each register
            VBox registerBox = new VBox(2); // Vertical spacing between elements
            registerBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 5; -fx-alignment: center;");

            // Add labels for the register's name, Q, and Value
            Label nameLabel = new Label("R" + i);
            nameLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

            Label qLabel = new Label("Q: " + (reg.getQ() != null ? reg.getQ() : "-"));
            qLabel.setStyle("-fx-font-size: 10px;");

            Label valueLabel = new Label("Value: " + reg.getValue());
            valueLabel.setStyle("-fx-font-size: 10px;");

            // Add the labels to the register box
            registerBox.getChildren().addAll(nameLabel, qLabel, valueLabel);

            // Add the register box to the grid
            grid.add(registerBox, i % 8, i / 8); // 8 registers per row
        }

        // Add Floating Point Registers (F0-F31)
        for (int i = 0; i < floatRegFile.size(); i++) {
            FloatRegFile reg = floatRegFile.get(i);

            // Create a VBox for each register
            VBox registerBox = new VBox(2); // Vertical spacing between elements
            registerBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 5; -fx-alignment: center;");

            // Add labels for the register's name, Q, and Value
            Label nameLabel = new Label("F" + i);
            nameLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

            Label qLabel = new Label("Q: " + (reg.getQ() != null ? reg.getQ() : "-"));
            qLabel.setStyle("-fx-font-size: 10px;");

            Label valueLabel = new Label("Value: " + reg.getValue());
            valueLabel.setStyle("-fx-font-size: 10px;");

            // Add the labels to the register box
            registerBox.getChildren().addAll(nameLabel, qLabel, valueLabel);

            // Add the register box to the grid
            grid.add(registerBox, i % 8, (i / 8) + 5); // Offset rows for float registers
        }

        // Wrap the grid in a VBox with a title
        VBox layout = new VBox(10, registerFileTitle, grid);
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        return layout;
    }


    private static VBox drawBus(Bus bus) {
        // Title for the Bus Block
        Label busTitle = new Label("Bus");
        busTitle.setStyle("-fx-font-size: 14px; -fx-padding: 5px; -fx-font-weight: bold;");

        // Labels for the Bus properties

        Label tagLabel = new Label("Tag: " + (bus.getTag() != null ? bus.getTag() : "-"));
        tagLabel.setStyle("-fx-font-size: 12px;");

        Label valueLabel = new Label("Value: " + (bus.getValue() != null ? bus.getValue() : "-"));
        valueLabel.setStyle("-fx-font-size: 12px;");

        // VBox for the Bus block
        VBox busBox = new VBox(5, busTitle, tagLabel, valueLabel);
        busBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-alignment: center;");

        return busBox;
    }


    public static void main(String[] args) {
    	
    	
        launch(args);
    }
}

