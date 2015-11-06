/**
 * Created by danieltam on 10/22/15.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SC_View extends Application {

    SC_Controller_v1_franny franny = new SC_Controller_v1_franny();

    boolean arialBool;
    boolean georgiaBool;
    boolean size12Bool;
    boolean size14Bool;
    boolean size16Bool;
    boolean size20Bool;
    TextArea originalArea = new TextArea();
    TextArea filteredArea = new TextArea();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Build up main scene window and applying BorderPane layout
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 1200, 600);
        primaryStage.setTitle("String Conductor");
        primaryStage.setScene(scene);
        primaryStage.show();


        //Creating the MenuBar
        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Options");
        Menu menu3 = new Menu("Help");

        //Add import menu item to under File
        MenuItem importBut = new MenuItem("Import");
        menu1.getItems().add(importBut);


        //Adds menu bar to window and set to top of BorderPane
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        bp.setTop(menuBar);


        //Creates the original text area at center of BorderPane

        //TextArea originalArea = new TextArea();
        bp.setCenter(originalArea);
        bp.setAlignment(originalArea,Pos.CENTER);
        bp.setMargin(originalArea, new Insets(12,0,12,12));
        originalArea.setMaxSize(500, Region.USE_COMPUTED_SIZE);
        originalArea.setMinSize(200, 250);
        originalArea.setScaleShape(true);
        originalArea.setWrapText(true);



        //Creates the filtered field area at right of BorderPane
        bp.setRight(filteredArea);
        bp.setAlignment(filteredArea, Pos.CENTER);
        bp.setMargin(filteredArea, new Insets(12,12,12, 12));
        filteredArea.setMaxSize(470, Region.USE_COMPUTED_SIZE);
        filteredArea.setMinSize(200, 250);
        filteredArea.setScaleShape(true);
        filteredArea.setWrapText(true);

        //Creates VBox for left of BorderPane
        VBox leftPane = new VBox();
        bp.setLeft(leftPane);
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
        MenuItem arialBut = new MenuItem("Arial");
        MenuItem georgiaBut = new MenuItem("Georgia");
        fontsMenu.getItems().addAll(arialBut, georgiaBut);

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
        MenuItem size12But = new MenuItem("12");
        MenuItem size14But = new MenuItem("14");
        MenuItem size16But = new MenuItem("16");
        MenuItem size20But = new MenuItem("20");
        fontSizeMenu.getItems().addAll(size12But, size14But, size16But, size20But);

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

        //creates the phrase length drop menu
        MenuButton pLengthMenu = new MenuButton("Select");
        MenuItem optionFour = new MenuItem("4");
        MenuItem optionFive = new MenuItem("5");
        MenuItem optionSix = new MenuItem("6");
        MenuItem optionSeven = new MenuItem("7");
        MenuItem optionEight = new MenuItem("8");
        MenuItem optionNine = new MenuItem("9");
        MenuItem optionTen = new MenuItem("10");
        pLengthMenu.getItems().addAll(optionFour, optionFive, optionSix, optionSeven,
                                      optionEight, optionNine, optionTen);

        //pLengthInput.setPrefWidth(27);

        //Creates the phrase length HBox
        HBox pLengthBox = new HBox();
        pLengthBox.getChildren().addAll(pLengthLabel, pLengthMenu);

        //Creates the clear filters and apply button
        Button cFiltersBut = new Button("Clear filters");
        Button applyBut = new Button("Apply");



        leftPane.setMargin(cFiltersBut, new Insets(20, 0, 0, 80));
        leftPane.setMargin(applyBut, new Insets(15, 0, 0, 100));
        leftPane.setMargin(editorLabel, new Insets(10, 0, 0, 0));
        leftPane.setMargin(filtersLabel, new Insets(10, 0, 0, 0));
        leftPane.setMargin(searchBox, new Insets(10, 0, 0, 0));
        leftPane.getChildren().addAll(editorLabel, fontBox, fontSizeBox,
                                      filtersLabel, searchBox, pLengthBox,
                                      cFiltersBut, applyBut);



        //Actions of all buttons

        arialBut.setOnAction(e -> {
            fontsMenu.setText("Arial");
            setAllFontsFalse();
            arialBool = true;
            applyFont();
        });

        georgiaBut.setOnAction(e -> {
            fontsMenu.setText("Georgia");
            setAllFontsFalse();
            georgiaBool = true;
            applyFont();
        });

        size12But.setOnAction(e -> {
            fontSizeMenu.setText("12");
            setAllSizesFalse();
            size12Bool = true;
            applyFont();
        });

        size14But.setOnAction(e -> {
            fontSizeMenu.setText("14");
            setAllSizesFalse();
            size14Bool = true;
            applyFont();
        });

        size16But.setOnAction(e -> {
            fontSizeMenu.setText("16");
            setAllSizesFalse();
            size16Bool = true;
            applyFont();
        });

        size20But.setOnAction(e -> {
            fontSizeMenu.setText("20");
            setAllSizesFalse();
            size20Bool = true;
            applyFont();
        });

        cFiltersBut.setOnAction(e -> {
            //Creates an alert message dialog
            Alert importAlert = new Alert(Alert.AlertType.ERROR);
            importAlert.setTitle("An error has occurred");
            importAlert.setHeaderText("Importing failed!");
            importAlert.setContentText("Please check the file format!");
            importAlert.showAndWait();
        });

        //Sets action of apply button and returns path directory
        applyBut.setOnAction(e -> {

            String x = "-fx-text-fill: red;";

            originalArea.setStyle(x + "-fx-font-size: 30px;" + "-fx-font-family: Courier New;");

        });

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

    public void setAllFontsFalse(){
        arialBool = false;
        georgiaBool = false;
    }

    public void setAllSizesFalse(){
        size12Bool = false;
        size14Bool = false;
        size16Bool = false;
        size20Bool = false;
    }

    public void applyFont(){
        String size = "";
        String font = "";

        if(arialBool == true){
            font = "-fx-font-family: Arial;";
        }
        else if(georgiaBool == true){
            font = "-fx-font-family: Georgia;";
        }

        if(size12Bool == true){
            size = "-fx-font-size: 12px;";
        }
        else if(size14Bool == true){
            size = "-fx-font-size: 14px;";
        }
        else if(size16Bool == true){
            size = "-fx-font-size: 16px;";
        }
        else if(size20Bool == true){
            size = "-fx-font-size: 20px;";
        }

        originalArea.setStyle(font + size);
        filteredArea.setStyle(font + size);
    }

    public String importFile(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse File");
        final File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println("getCurrentDirectory(): " + file.getPath());
        return file.getPath();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void runApp(String[] args) {
        launch(args);
    }

}
