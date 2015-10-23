/**
 * Created by danieltam on 10/22/15.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SC_View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane bp = new BorderPane();

        Text yearLabel = new Text("Results");
        TextField yearInput = new TextField();
        VBox inputField = new VBox();
        inputField.getChildren().addAll(yearInput, yearLabel);
        inputField.setSpacing(12);

        bp.setCenter(inputField);
        bp.setAlignment(inputField, Pos.CENTER_RIGHT);
        bp.setMargin(inputField, new Insets(12,12,12,12));

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

        Scene scene = new Scene(bp, 300, 200);
        primaryStage.setTitle("Leap Year Checker");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
