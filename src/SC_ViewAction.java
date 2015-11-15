import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by danieltam on 11/5/15.
 */
public class SC_ViewAction {

    //Creates all the boolean for user selections
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
    boolean importSuccessful = false;

    //Creates the component the user can input
    TextArea originalArea = new TextArea();
    TextArea filteredArea = new TextArea();
    TextField searchInput = new TextField();
    MenuButton pLengthMenu = new MenuButton("0");

    //Boolean controller methods
    public void setAllFontsFalse() {
        arialBool = false;
        georgiaBool = false;
    }

    public void setAllSizesFalse() {
        size12Bool = false;
        size14Bool = false;
        size13Bool = false;
        size16Bool = false;
        size20Bool = false;
    }

    public void setAllLengthFalse() {
        optionFourBool = false;
        optionFiveBool = false;
        optionSixBool = false;
        optionSevenBool = false;
        optionEightBool = false;
        optionNineBool = false;
        optionTenBool = false;
    }

    //Sets font based on user selection
    public void applyFont() {
        String size = "";
        String font = "";

        if (arialBool == true) {
            font = "-fx-font-family: Arial;";
        } else if (georgiaBool == true) {
            font = "-fx-font-family: Georgia;";
        }
        if (size12Bool == true) {
            size = "-fx-font-size: 12px;";
        } else if (size13Bool == true) {
            size = "-fx-font-size: 13px;";
        } else if (size14Bool == true) {
            size = "-fx-font-size: 14px;";
        } else if (size16Bool == true) {
            size = "-fx-font-size: 16px;";
        } else if (size20Bool == true) {
            size = "-fx-font-size: 20px;";
        }

        originalArea.setStyle(font + size);
        filteredArea.setStyle(font + size);
    }

    //Clears up the search input and phrase length - changes them to default
    public void clearFilter() {
        searchHaveValue = false;
        pLengthHaveValue = false;
        searchInput.setText("");
        pLengthMenu.setText("0");
    }

    //Checks if search input is empty or not
    public void isSearchEmpty() {
        if (searchInput.getText().length() > 0) {
            searchHaveValue = true;
        } else searchHaveValue = false;
    }

    //Checks if user has met our precondition - only search or phrase length
    public void checkFilters() {
        isSearchEmpty();
        if (((searchHaveValue == false) && (pLengthHaveValue == false)) || ((searchHaveValue == true) && (pLengthHaveValue == true))) {
            Alert importAlert = new Alert(Alert.AlertType.ERROR);
            importAlert.setTitle("An error has occurred");
            importAlert.setHeaderText("Select correctly!");
            importAlert.setContentText("Please use only either \"Search\" or \"Phrase Length\"\nPick one only! Use \"Clear filters\" button!");
            importAlert.showAndWait();
        }
    }

    //Returns which phrase length option the user selected
    public Integer returnPLength() {
        if (optionFourBool == true) return 4;
        else if (optionFiveBool == true) return 5;
        else if (optionSixBool == true) return 6;
        else if (optionSevenBool == true) return 7;
        else if (optionEightBool == true) return 8;
        else if (optionNineBool == true) return 9;
        else if (optionTenBool == true) return 10;
        else return null;
    }

    //Creates file chooser window for user to select their file to import
    public String getImportFileDirectory(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse File");
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file == null){
            return "";
        }
        else {
            importSuccessful = true;
            return file.getPath();
        }
    }

    //Allow users too save their results into a .txt file
    public void saveFile(Stage primaryStage) {
        FileChooser saveFileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        saveFileChooser.getExtensionFilters().add(extFilter);
        saveFileChooser.setTitle("Save to...");
        File file = saveFileChooser.showSaveDialog(primaryStage);

        if(file == null){
            System.out.println("Canceled");
        }
        else {
            makeSaveFile(filteredArea.getText(), file);
        }
    }

    public void makeSaveFile(String content, File file) {
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
        }

    }
}
