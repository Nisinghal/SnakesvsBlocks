package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

/**
 * The Class Main.
 */
public class Main extends Application {
    
    /** The main stage. */
    static Stage mainStage;
    
    /** The resumed. */
    static boolean resumed = false;
    
    /** The first time. */
    static boolean firstTime = true;
    
    /** The sg. */
    static SaveGame _sg = new SaveGame();

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml"));
        primaryStage.setTitle("Snake VS Blocks");
        primaryStage.setScene(new Scene(root, 320, 480));
        primaryStage.show();
    }


    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        try {
            Main.load();
        }
        catch (Exception e) {}
        launch(args);
    }

    /**
     * Save.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/resources/save/savegame"));
        out.writeObject(_sg);
        out.close();
    }


    /**
     * Load.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ClassNotFoundException the class not found exception
     */
    public static void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/resources/save/savegame"));
        _sg = (SaveGame) in.readObject();
        in.close();
    }
}