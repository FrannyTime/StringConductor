import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by danieltam on 11/5/15.
 */
public class SC_ViewAction {

    boolean arialBool;
    boolean georgiaBool;
    boolean size12Bool;
    boolean size13Bool;
    boolean size14Bool;
    boolean size16Bool;
    boolean size20Bool;
    boolean optionFourBool;
    boolean optionFiveBool;
    boolean optionSixBool;
    boolean optionSevenBool;
    boolean optionEightBool;
    boolean optionNineBool;
    boolean optionTenBool;
    boolean searchHaveValue = false;
    boolean pLengthHaveValue = false;

    TextArea originalArea = new TextArea();
    TextArea filteredArea = new TextArea();
    TextField searchInput = new TextField();

    public void setAllFontsFalse(){
        arialBool = false;
        georgiaBool = false;
    }

    public void setAllSizesFalse(){
        size12Bool = false;
        size14Bool = false;
        size13Bool = false;
        size16Bool = false;
        size20Bool = false;
    }

    public void setAllLengthFalse(){
        boolean optionFourBool = false;
        boolean optionFiveBool = false;
        boolean optionSixBool = false;
        boolean optionSevenBool = false;
        boolean optionEightBool = false;
        boolean optionNineBool = false;
        boolean optionTenBool = false;
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
        else if(size13Bool == true){
            size = "-fx-font-size: 13px;";
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

    public void clearFilter(){
        searchHaveValue = false;
        pLengthHaveValue = false;
        searchInput.setText("");
    }

    public String getInputField(){
        return searchInput.getText();
    }

    public void isSearchEmpty(){
        if(searchInput.getText().length() > 0){
            searchHaveValue = true;
        }
    }

    public void checkFilters(){
        if((searchHaveValue == false) && (pLengthHaveValue == false)){
            Alert importAlert = new Alert(Alert.AlertType.ERROR);
            importAlert.setTitle("An error has occurred");
            importAlert.setHeaderText("Select an option!");
            importAlert.setContentText("Please use only either \"Search\" or \"Phrase Length\"\nPick one only!");
            importAlert.showAndWait();
        }
    }

    public String importFile(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse File");
        final File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println("getCurrentDirectory(): " + file.getPath());
        return file.getPath();
    }


}
