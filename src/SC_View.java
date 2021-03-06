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
import javafx.stage.Stage;
import java.io.IOException;

public class SC_View extends Application {

    ControlLayer franny = new ControlLayer();
    SC_ViewAction va = new SC_ViewAction();

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
        Menu menuFile = new Menu("File");

        //Add import and save menu item to under file
        MenuItem importBut = new MenuItem("Import");
        MenuItem saveBut = new MenuItem("Save results");
        menuFile.getItems().addAll(importBut, saveBut);

        //Adds menu bar to window and set to top of BorderPane
        menuBar.getMenus().add(menuFile);
        bp.setTop(menuBar);

        //Creates the original text area at center of BorderPane
        bp.setCenter(va.originalArea);
        bp.setAlignment(va.originalArea, Pos.CENTER);
        bp.setMargin(va.originalArea, new Insets(12, 0, 12, 12));
        va.originalArea.setMaxSize(500, Region.USE_COMPUTED_SIZE);
        va.originalArea.setMinSize(200, 250);
        va.originalArea.setScaleShape(true);

        //Creates the filtered field area at right of BorderPane
        bp.setRight(va.filteredArea);
        bp.setAlignment(va.filteredArea, Pos.CENTER);
        bp.setMargin(va.filteredArea, new Insets(12, 12, 12, 12));
        va.filteredArea.setMaxSize(470, Region.USE_COMPUTED_SIZE);
        va.filteredArea.setMinSize(200, 250);
        va.filteredArea.setScaleShape(true);

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
        fontsMenu.setPrefWidth(90);
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
        MenuButton fontSizeMenu = new MenuButton("Default");
        fontSizeMenu.setPrefWidth(90);
        MenuItem size12But = new MenuItem("12");
        MenuItem size13But = new MenuItem("13");
        MenuItem size14But = new MenuItem("14");
        MenuItem size16But = new MenuItem("16");
        MenuItem size20But = new MenuItem("20");
        fontSizeMenu.getItems().addAll(size12But, size13But, size14But, size16But, size20But);

        //Creates the font size HBox
        HBox fontSizeBox = new HBox();
        fontSizeBox.getChildren().addAll(fontSizeLabel, fontSizeMenu);
        fontSizeBox.setMargin(fontSizeLabel, new Insets(6, 0, 0, 35));
        fontSizeBox.setMargin(fontSizeMenu, new Insets(10, 0, 0, 26));

        //Creates the filters text
        Text filtersLabel = new Text("Filters");
        filtersLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        filtersLabel.setWrappingWidth(250);
        filtersLabel.setTextAlignment(TextAlignment.CENTER);

        //Creates the search text
        Text searchLabel = new Text("Search:");
        searchLabel.setFont(Font.font("System", 24));

        //Creates the search HBox
        HBox searchBox = new HBox();
        searchBox.getChildren().addAll(searchLabel, va.searchInput);

        //Creates the phrase length text
        Text pLengthLabel = new Text("Phrase Length:");
        pLengthLabel.setFont(Font.font("System", 24));

        //creates the phrase length drop menu
        MenuItem optionZero = new MenuItem("0");
        MenuItem optionFour = new MenuItem("4");
        MenuItem optionFive = new MenuItem("5");
        MenuItem optionSix = new MenuItem("6");
        MenuItem optionSeven = new MenuItem("7");
        MenuItem optionEight = new MenuItem("8");
        MenuItem optionNine = new MenuItem("9");
        MenuItem optionTen = new MenuItem("10");
        va.pLengthMenu.getItems().addAll(optionZero, optionFour, optionFive, optionSix, optionSeven,
                                         optionEight, optionNine, optionTen);

        //Creates the phrase length HBox
        HBox pLengthBox = new HBox();
        pLengthBox.getChildren().addAll(pLengthLabel, va.pLengthMenu);

        //Creates the clear filters and apply button
        Button cFiltersBut = new Button("Clear filters");
        Button applyBut = new Button("Apply");
        applyBut.setDisable(true);

        leftPane.setMargin(cFiltersBut, new Insets(20, 0, 0, 80));
        leftPane.setMargin(applyBut, new Insets(15, 0, 0, 100));
        leftPane.setMargin(editorLabel, new Insets(10, 0, 0, 0));
        leftPane.setMargin(filtersLabel, new Insets(10, 0, 0, 0));
        leftPane.setMargin(searchBox, new Insets(10, 0, 0, 0));
        leftPane.getChildren().addAll(editorLabel, fontBox, fontSizeBox,
                                      filtersLabel, searchBox, pLengthBox,
                                      cFiltersBut, applyBut);

        /**
         * Actions of all buttons
         */
        arialBut.setOnAction(e -> {
            fontsMenu.setText("Arial");
            va.setAllFontsFalse();
            va.arialBool = true;
            va.applyFont();
        });

        georgiaBut.setOnAction(e -> {
            fontsMenu.setText("Georgia");
            va.setAllFontsFalse();
            va.georgiaBool = true;
            va.applyFont();
        });

        size12But.setOnAction(e -> {
            fontSizeMenu.setText("12");
            va.setAllSizesFalse();
            va.size12Bool = true;
            va.applyFont();
        });

        size13But.setOnAction(e -> {
            fontSizeMenu.setText("13");
            va.setAllSizesFalse();
            va.size13Bool = true;
            va.applyFont();
        });

        size14But.setOnAction(e -> {
            fontSizeMenu.setText("14");
            va.setAllSizesFalse();
            va.size14Bool = true;
            va.applyFont();
        });

        size16But.setOnAction(e -> {
            fontSizeMenu.setText("16");
            va.setAllSizesFalse();
            va.size16Bool = true;
            va.applyFont();
        });

        size20But.setOnAction(e -> {
            fontSizeMenu.setText("20");
            va.setAllSizesFalse();
            va.size20Bool = true;
            va.applyFont();
        });

        optionZero.setOnAction(e -> {
            va.pLengthMenu.setText("0");
            va.setAllLengthFalse();
            va.pLengthHaveValue = false;
        });

        optionFour.setOnAction(e -> {
            va.pLengthMenu.setText("4");
            va.setAllLengthFalse();
            va.optionFourBool = true;
            va.pLengthHaveValue = true;
        });

        optionFive.setOnAction(e -> {
            va.pLengthMenu.setText("5");
            va.setAllLengthFalse();
            va.optionFiveBool = true;
            va.pLengthHaveValue = true;
        });

        optionSix.setOnAction(e -> {
            va.pLengthMenu.setText("6");
            va.setAllLengthFalse();
            va.optionSixBool = true;
            va.pLengthHaveValue = true;
        });

        optionSeven.setOnAction(e -> {
            va.pLengthMenu.setText("7");
            va.setAllLengthFalse();
            va.optionSevenBool = true;
            va.pLengthHaveValue = true;
        });
        optionEight.setOnAction(e -> {
            va.pLengthMenu.setText("8");
            va.setAllLengthFalse();
            va.optionEightBool = true;
            va.pLengthHaveValue = true;
        });
        optionNine.setOnAction(e -> {
            va.pLengthMenu.setText("9");
            va.setAllLengthFalse();
            va.optionNineBool = true;
            va.pLengthHaveValue = true;
        });
        optionTen.setOnAction(e -> {
            va.pLengthMenu.setText("10");
            va.setAllLengthFalse();
            va.optionTenBool = true;
            va.pLengthHaveValue = true;
        });

        cFiltersBut.setOnAction(e -> {
            va.clearFilter();
        });

        /**
         * Sets action of apply button
         */
        applyBut.setOnAction(e -> {

            va.checkFilters();

            if (((va.searchHaveValue == false) && (va.pLengthHaveValue == false)) || ((va.searchHaveValue == true) && (va.pLengthHaveValue == true))) {
                Alert importAlert = new Alert(Alert.AlertType.ERROR);
                importAlert.setTitle("An error has occurred");
                importAlert.setHeaderText("Select correctly!");
                importAlert.setContentText("Please use only either \"Search\" or \"Phrase Length\"\nPick one only! Use \"Clear filters\" button!");
                importAlert.showAndWait();
            }

            else if(va.pLengthHaveValue == true) {
                va.filteredArea.setText(franny.getFilteredResultsByWordCount(va.returnPLength()));
            }
            else if(va.searchHaveValue == true) {
                va.filteredArea.setText(franny.getFilteredResultsBySearchPhrase(va.searchInput.getText()));
            }

        });

        /**
         * Sets action of import button
         */
        importBut.setOnAction(e -> {
            try {
                va.filteredArea.setText(franny.getImportResults(va.getImportFileDirectory(primaryStage)));
                va.originalArea.setText(franny.getOriginalDocument());
            }
            catch(IOException eo){}

            if(va.importSuccessful == true) {
                applyBut.setDisable(false);
            }
        });

        /**
         * Sets action of save button
         */
        saveBut.setOnAction(e -> {
            va.saveFile(primaryStage);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
