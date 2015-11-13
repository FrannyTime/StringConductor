import javafx.application.Application;
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
        //setAllFontsFalse();
        //setAllSizesFalse();
        searchInput.setText("");
        //applyFont();
    }

    public String importFile(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse File");
        final File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println("getCurrentDirectory(): " + file.getPath());
        return file.getPath();
    }


}
