/**
 * Created by danieltam on 10/22/15.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
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


        //Creates the original text area at center of BorderPane

        TextArea originalArea = new TextArea();
        bp.setCenter(originalArea);
        bp.setAlignment(originalArea,Pos.CENTER);
        bp.setMargin(originalArea, new Insets(12,0,12,12));
        originalArea.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        originalArea.setMinSize(400, 550);
        originalArea.setScaleShape(true);
        originalArea.setWrapText(true);

        //Creates the filtered field area at right of BorderPane
        TextArea filteredArea = new TextArea();
        bp.setRight(filteredArea);
        bp.setAlignment(filteredArea, Pos.CENTER_RIGHT);
        bp.setMargin(filteredArea, new Insets(12,12,12,12));
        filteredArea.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        filteredArea.setMinSize(400, 550);
        filteredArea.setScaleShape(true);
        filteredArea.setWrapText(true);

        //Creates VBox for left of BorderPane
        VBox leftPane = new VBox();
        leftPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        leftPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        //Creates the editor text
        Text editorLabel = new Text("Editor");
        editorLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        editorLabel.setWrappingWidth(250);
        editorLabel.setTextAlignment(TextAlignment.CENTER);

        //Creates the fonts text
        Text fontsLabel = new Text("Fonts:");
        fontsLabel.setFont(Font.font("System", 24));

        //Creates the fonts drop menu
        MenuButton fontsMenu = new MenuButton("Default");
        MenuItem arial = new MenuItem("Arial");
        MenuItem NTR = new MenuItem("New Times Roman");
        fontsMenu.getItems().addAll(arial, NTR);

        //Creates the fonts HBox
        HBox fontBox = new HBox();
        fontBox.getChildren().addAll(fontsLabel, fontsMenu);
        fontBox.setMargin(fontsMenu, new Insets(13, 0, 0, 10));
        fontBox.setMargin(fontsLabel, new Insets(10, 0, 0, 35));

        //Creates the font size text
        Text fontSizeLabel = new Text("Size:");
        fontSizeLabel.setFont(Font.font("System", 24));

        //Creates the font size drop menu
        MenuButton fontSizeMenu = new MenuButton("12");
        MenuItem size1 = new MenuItem("14");
        MenuItem size2 = new MenuItem("16");
        fontSizeMenu.getItems().addAll(size1, size2);

        //Creates the font size HBox
        HBox fontSizeBox = new HBox();
        fontSizeBox.getChildren().addAll(fontSizeLabel, fontSizeMenu);
        fontSizeBox.setMargin(fontSizeLabel, new Insets(6, 0, 0, 35));
        fontSizeBox.setMargin(fontSizeMenu, new Insets(10, 0, 0, 35));

        //Creates the filters text
        Text filtersLabel = new Text("Filters");
        filtersLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        filtersLabel.setWrappingWidth(250);
        filtersLabel.setTextAlignment(TextAlignment.CENTER);

        //Creates the search text
        Text searchLabel = new Text("Search:");
        searchLabel.setFont(Font.font("System", 24));

        //Creates the search input area
        TextField searchInput = new TextField();


        //Creates the search HBox
        HBox searchBox = new HBox();
        searchBox.getChildren().addAll(searchLabel, searchInput);

        //Creates the phrase length text
        Text pLengthLabel = new Text("Phrase Length:");
        pLengthLabel.setFont(Font.font("System", 24));

        //creates the phrase length input area
        TextField pLengthInput = new TextField();
        pLengthInput.setPrefWidth(27);

        //Creates the phrase length HBox
        HBox pLengthBox = new HBox();
        pLengthBox.getChildren().addAll(pLengthLabel, pLengthInput);

        //Creates the clear filters and apply button
        Button cFiltersBut = new Button("Clear filters");
        Button applyBut = new Button("Apply");



        leftPane.setMargin(cFiltersBut, new Insets(20, 0, 0, 80));
        leftPane.setMargin(applyBut, new Insets(20, 0, 0, 100));
        leftPane.setMargin(editorLabel, new Insets(10, 0, 0, 0));
        leftPane.setMargin(filtersLabel, new Insets(10, 0, 0, 0));
        leftPane.setMargin(searchBox, new Insets(10, 0, 0, 0));
        leftPane.getChildren().addAll(editorLabel, fontBox, fontSizeBox,
                                      filtersLabel, searchBox, pLengthBox,
                                      cFiltersBut, applyBut);

        bp.setLeft(leftPane);





  /*     Text editorName = new Text("Editor");
        Text filtersName = new Text("Filters");
        Button applyBut = new Button("Apply");
        Button clearBut = new Button("Clear All");
        VBox leftV = new VBox();
        leftV.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        leftV.getChildren().addAll(editorName, filtersName, applyBut, clearBut);
        bp.setLeft(leftV);
*/


        //Actions of all buttons

        //Sets action of import button and returns path directory
        importBut.setOnAction(e -> {

            importFile(primaryStage);
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