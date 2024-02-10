// user enters link/specified search
// shows what image will look like
// can copy the link to use in web browser
// search, has “.com”, “www” buttons for convenience
// dropdown menus for color and desired size
// option to copy image or copy url link

package com.example.uga_hacks_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private String qrUrl;
    private Image qrImg;
    private ImageView viewImg;
    private VBox root;
    private TextFlow finalQrUrlTextFlow;
    private Button searchButton;
    private Button copyUrl;
    private Button copyImg;
    private TextField searchInput;
    private ComboBox<Rectangle> changeColor;
    private ComboBox<String> changeSize;
    private String sizeA; // for size dimensions
    private String sizeB; // for size dimensions
    private Button b1; // ".com"
    private Button b2; // "www."

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("YOUQR");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}