/**
 * Created by danieltam on 10/22/15.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SC_View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane bp = new BorderPane();

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
        //originalArea.setWrapText(true);
        bp.setMargin(originalArea, new Insets(12, -120, 12, 12));
        bp.setRight(originalArea);


        Scene scene = new Scene(bp, 700, 500);
        primaryStage.setTitle("String Conductor");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
