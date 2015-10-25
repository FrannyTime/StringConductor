/**
 * Created by danieltam on 10/22/15.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SC_View extends Application {

    SC_Controller_v1_franny franny = new SC_Controller_v1_franny();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Build up main scene window and applying BorderPane layout
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 1100, 600);
        primaryStage.setTitle("String Conductor");
        primaryStage.setScene(scene);
        primaryStage.show();


        //Initializing MenuBar
        MenuBar menuBar = new MenuBar();
        final Menu menu1 = new Menu("File");
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");

        //Add import menu item to under File
        MenuItem importBut = new MenuItem("Import");
        menu1.getItems().add(importBut);



        //Adds menu bar to window and set to top of BorderPane
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        bp.setTop(menuBar);


        VBox centerV = new VBox();
        TextArea originalArea = new TextArea();
        bp.setCenter(centerV);
        bp.setAlignment(centerV, Pos.CENTER);
        bp.setMargin(centerV, new Insets(12,12,12,230));
        bp.setScaleShape(true);
        centerV.getChildren().add(originalArea);
        centerV.setScaleShape(true);
        centerV.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        originalArea.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        originalArea.setMinSize(400, 500);
        originalArea.setScaleShape(true);
        centerV.setPrefSize(bp.getWidth() / 2, bp.getHeight() / 2);
        originalArea.setWrapText(true);


        //Creates the original text area
/*
        TextArea originalArea = new TextArea();
        bp.setCenter(originalArea);
        bp.setAlignment(originalArea,Pos.CENTER);
        bp.setMargin(originalArea, new Insets(12,12,12,230));
        originalArea.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        originalArea.setMinSize(400, 500);
        originalArea.setScaleShape(true);
*/
        //Creates the filtered field area
        TextArea filteredArea = new TextArea();
        bp.setRight(filteredArea);
        bp.setAlignment(filteredArea, Pos.CENTER_RIGHT);
        bp.setMargin(filteredArea, new Insets(12,30,12,12));
        filteredArea.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        filteredArea.setMinSize(400, 500);
        filteredArea.setScaleShape(true);

        //Creates the editor/filter and set to left of BorderPane
        Text editorName = new Text("Editor");
        Text filtersName = new Text("Filters");
        VBox leftV = new VBox();
        leftV.getChildren().addAll(editorName, filtersName);
        editorName.setTextAlignment(TextAlignment.CENTER);
        bp.setLeft(leftV);



        //Actions of all buttons

        //Sets action of import button and returns path directory
        importBut.setOnAction(e -> {

            //importFile(primaryStage);
            String x = new String();
            try {
                x = franny.giveItDaniel(importFile(primaryStage));
                originalArea.setText(x);
                //originalArea.setText(importFile(primaryStage));
            }
            catch (IOException IOE){

            }

        });
    }

//j

    public String importFile(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse File");
        final File file = fileChooser.showOpenDialog(primaryStage);
        //System.out.println("getCurrentDirectory(): " + file.getPath());
        return file.getPath();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void runApp(String[] args) {
        launch(args);
    }

}









/*
        BorderPane bp = new BorderPane();

        Scene scene = new Scene(bp, 1000, 600);
        primaryStage.setTitle("String Conductor");
        primaryStage.setScene(scene);
        primaryStage.show();

        Text yearLabel = new Text("Results");
        TextField yearInput = new TextField();
        VBox inputField = new VBox();
        inputField.getChildren().addAll(yearInput, yearLabel);
        inputField.setSpacing(12);

        bp.setCenter(inputField);
        bp.setAlignment(inputField, Pos.CENTER_RIGHT);
        bp.setMargin(inputField, new Insets(12,200,12,200));

        //Button to check leap year
        Button checkButton = new Button("Check");
        checkButton.setOnAction(e -> {
            if(Integer.parseInt(yearInput.getText()) % 400 == 0){
                yearLabel.setText(yearInput.getText() + " is a leap year!");
                System.out.println(yearInput.getText() + " is a leap year!");
            }

            else if(Integer.parseInt(yearInput.getText()) % 100 == 0){
                yearLabel.setText(yearInput.getText() + " is not a leap year!");
                System.out.println(yearInput.getText() + " is not a leap year!");
            }
            else if(Integer.parseInt(yearInput.getText()) % 4 == 0){
                yearLabel.setText(yearInput.getText() + " is a leap year!");
                System.out.println(yearInput.getText() + " is a leap year!");
            }
            else{
                yearLabel.setText(yearInput.getText() + " is not a leap year!");
                System.out.println(yearInput.getText() + " is not a leap year!");
            }
        });
        bp.setBottom(checkButton);
        bp.setAlignment(checkButton, Pos.BOTTOM_CENTER);
        bp.setMargin(checkButton, new Insets(12,12,12,12));

        MenuBar menuBar = new MenuBar();
        final Menu menu1 = new Menu("File");
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");

        MenuItem hi = new MenuItem("hi");
        MenuItem importBut = new MenuItem("Import");

        //Add Import to under file
        menu1.getItems().addAll(hi, importBut);

        //Sets action of import button and returns path directory

        importBut.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            final File file = fileChooser.showOpenDialog(primaryStage);
            System.out.println("getCurrentDirectory(): " + file.getPath());
        });

        //Adds menu bar to window and set to top of pane
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        bp.setTop(menuBar);

        //Adds text area to window and set to right of pane
        final TextArea originalArea = new TextArea();
        String entry = "entry";

        String filePath = "/Users/danieltam/Desktop/textFile.txt";
        SC_Model_v1_franny frannyObject = new SC_Model_v1_franny();
        String myString = frannyObject.readFile(filePath);

        hi.setOnAction(e -> originalArea.setText(myString));

        VBox vRight = new VBox();

        bp.setRight(vRight);
        vRight.setPrefSize(570,180);
        vRight.getChildren().add(originalArea);
        vRight.setMargin(originalArea,new Insets(12,300,12,12));
        originalArea.setMaxWidth(100);
        originalArea.setEditable(true);
        //originalArea.
        //vRight.setAlignment(Pos.CENTER_LEFT);
        //vRight.setMargin(originalArea, new Insets(12,12,12,12));

        originalArea.setWrapText(true);
        //bp.setMargin(originalArea, new Insets(12, -120, 12, 12));
        //bp.setRight(originalArea);
        //bp.setPadding(new Insets(12,12,12,12));
//hi
    }

    public static void runApp(String[] args )
    {
        launch(args);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
*/