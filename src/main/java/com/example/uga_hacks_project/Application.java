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
import javafx.scene.text.Text;
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
import com.example.uga_hacks_project.Api; 

public class Application extends javafx.application.Application {

    private Stage stage;
    private Scene scene;
    private String qrUrl;
    private Image qrImg;
    private ImageView viewImg;
    private VBox root;
    private TextFlow displayQrUrl;
    private Button searchButton;
    private Button copyUrl;
    private Button copyImg;
    private TextField searchInput;
    private ComboBox<Rectangle> changeColor;
    private ComboBox<String> changeSize;
    private int codeSize; // for size dimensions
    private Button b1; // ".com"
    private Button b2; // "www."

    @Override
    public void init() {
        this.root = new VBox();
        this.qrUrl = "";
        this.viewImg = new ImageView();
        this.displayQrUrl = new TextFlow(new Text("Enter a website URL or a search result and the link to the QR code will appear here!"));
        this.searchButton = new Button("search");
        this.copyUrl = new Button("Copy the URL to clipboard");
        this.copyImg = new Button("Copy image to the clipboard");
        this.searchInput = new TextField();
        this.changeColor = new ComboBox<Rectangle>();
        this.changeSize = new ComboBox<String>();
        this.codeSize = 100;
        this.b1 = new Button(".com");
        this.b2 = new Button("www.");

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        this.scene = new Scene(fxmlLoader.load(), 320, 240);
        this.stage = stage;
        this.stage.setTitle("Hello!");
        this.stage.setScene(scene);


        this.stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
