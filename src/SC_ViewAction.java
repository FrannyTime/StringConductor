/**
 * Created by danieltam on 11/5/15.
 */

import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SC_ViewAction {

    /**
     * Creates all the booleans for user selections
     */
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

    /**
     * Creates the the text area and field
     */
    TextArea originalArea = new TextArea();
    TextArea filteredArea = new TextArea();
    TextField searchInput = new TextField();
    MenuButton pLengthMenu = new MenuButton("0");

    /**
     * Sets all font booleans to false
     */
    public void setAllFontsFalse() {
        arialBool = false;
        georgiaBool = false;
    }

    /**
     * Sets all size booleans to false
     */
    public void setAllSizesFalse() {
        size12Bool = false;
        size14Bool = false;
        size13Bool = false;
        size16Bool = false;
        size20Bool = false;
    }

    /**
     * Sets all length booleans to false
     */
    public void setAllLengthFalse() {
        optionFourBool = false;
        optionFiveBool = false;
        optionSixBool = false;
        optionSevenBool = false;
        optionEightBool = false;
        optionNineBool = false;
        optionTenBool = false;
    }

    /**
     * Sets font based on user selection
     */
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

    /**
     * Clears up the search input and phrase length - changes them to default
     */
    public void clearFilter() {
        searchHaveValue = false;
        pLengthHaveValue = false;
        searchInput.setText("");
        pLengthMenu.setText("0");
    }

    /**
     * Checks if user has met our precondition - only search or phrase length
     */
    public void checkFilters() {
        if (searchInput.getText().length() > 0) {
            searchHaveValue = true;
        } else searchHaveValue = false;
    }


    /**
     * Returns which phrase length option the user selected
     * @return The phrase length specified by the user
     */
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

    /**
     *Creates file chooser window for user to select their file to import
     */
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

    /**
     * Allow users too save their results into a .txt file
     * @param primaryStage
     */
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

    /**
     * Creates the save file at chosen location
     * @param content The content of the filtered results pane
     * @param file The chosen to be saved as by the user
     */
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
