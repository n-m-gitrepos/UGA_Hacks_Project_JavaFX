// user enters link/specified search
// shows what image will look like
// can copy the link to use in web browser
// search, has “.com”, “www” buttons for convenience
// dropdown menus for color and desired size
// option to copy image or copy url link

package com.example.uga_hacks_project;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.TextFlow;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private Stage stage;
    private Scene scene;
    private Label displayQrUrl;
    private String qrUrl;
    private Image qrImg;
    private ImageView viewImg;
    private VBox root;
    private Button searchButton;
    private Button copyUrl;
    private Button copyImg;
    private TextField searchInput;
    private ColorPicker colorPicker;
    private ColorPicker bgColorPicker;
    private ComboBox<String> changeSize;
    private int codeSize; // for size dimensions
    private Button b1; // "www."
    private Button b2; // ".com"
    private HBox panels;
    private VBox vbox1;
    private VBox vbox2;
    private HBox searchHBox;
    private Separator sepVert;
    private Separator sepHoriz;
    private ImageView superScanImgView;
    private static String baseURL = "http://api.qrserver.com/v1/create-qr-code/";
    private String color;
    private String bgColor;

    @Override
    public void init() {
        this.root = new VBox(3);
        this.qrUrl = "";
        this.viewImg = new ImageView();
        this.displayQrUrl = new Label("Enter a website URL or a search result and the link to the QR code will appear here!");
        this.searchButton = new Button("Generate QR Code");
        this.copyUrl = new Button("Copy the URL to clipboard");
        this.copyImg = new Button("Copy image to clipboard");
        this.searchInput = new TextField();

        this.changeSize = new ComboBox<String>();
        this.codeSize = 100;
        this.b1 = new Button("www.");
        this.b2 = new Button(".com");
        this.panels = new HBox(3);
        this.vbox1 = new VBox(3);
        this.vbox2 = new VBox(3);
        this.searchHBox = new HBox(3);
        this.sepVert = new Separator();
        this.sepHoriz = new Separator();
        this.superScanImgView = new ImageView();
        this.colorPicker = new ColorPicker();
        this.color = "";
        this.bgColorPicker = new ColorPicker();
        this.bgColor = "";
    }

    boolean isValidURL(String url) throws MalformedURLException, URISyntaxException {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        this.stage.setTitle("SuperScan");
        this.stage.setScene(scene);
        this.root.setPadding(new Insets(3));
        HBox.setHgrow(this.searchInput, Priority.ALWAYS);
        this.sepHoriz.setOrientation(Orientation.VERTICAL);
        this.searchInput.setText("https://");

        Image defaultImg = new Image("file:resources/defaultImage1.png");
        this.viewImg.setImage(defaultImg);
        Image superScanImg = new Image("file:resources/superScanImg.jpg");
        this.superScanImgView.setImage(superScanImg);
        this.superScanImgView.setFitWidth(200);
        this.superScanImgView.setFitHeight(200);
        this.viewImg.setFitWidth(100);
        this.viewImg.setFitHeight(100);

        this.copyImg.setDisable(true);
        this.copyUrl.setDisable(true);

        this.searchHBox.getChildren().addAll(b1, b2, searchInput);
        this.vbox1.getChildren().addAll(colorPicker, bgColorPicker, changeSize, searchButton, superScanImgView);
        this.vbox2.getChildren().addAll(viewImg, displayQrUrl, copyUrl, copyImg);
        this.panels.getChildren().addAll(vbox1, sepHoriz, vbox2);
        this.root.getChildren().addAll(searchHBox, sepVert, panels);

        //this.changeColor.getItems().addAll()
        this.changeSize.getItems().addAll(
                "100x100",
                "150x150",
                "200x200",
                "250x250",
                "300x300",
                "350x350"
        );

        this.colorPicker.setOnAction(e -> {
            Color c = colorPicker.getValue();
            this.color = String.format("%s-%s-%s",
                    ((int)c.getRed())*255,
                    ((int)c.getGreen())*255,
                    ((int)c.getBlue())*255);
        });

        this.bgColorPicker.setOnAction(e -> {
            Color c = colorPicker.getValue();
            this.bgColor = String.format("%s-%s-%s",
                    ((int)c.getRed())*255,
                    ((int)c.getGreen())*255,
                    ((int)c.getBlue())*255);
        });


        this.searchButton.setOnAction(e -> {
            try {
                if (this.isValidURL(this.searchInput.getText())) {
                    this.copyUrl.setDisable(false);
                    this.copyImg.setDisable(false);
                    String query = String.format("?data=%s&size=%s&color=%s&bgcolor=%s",
                            this.searchInput.getText(),
                            this.changeSize.getValue(),
                            this.color,
                            this.bgColor
                    );
                    String qrURI = baseURL + query;
                    this.viewImg.setImage(new Image(qrURI));

                } else {
                    this.displayQrUrl.setText("Please enter a valid URL.");
                }
            } catch (MalformedURLException ex) {
                this.displayQrUrl.setText("Please enter a valid URL.");
            } catch (URISyntaxException ex) {
                this.displayQrUrl.setText("Please enter a valid URL.");
            }

        });

        this.copyImg.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();

            String imageUrl = this.searchInput.getText();
            String targetPath = "resources/defaultImage1.png";

            try (InputStream in = new URL(imageUrl).openStream()){
                Files.copy(in, Path.of(targetPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception exc) {
                    exc.printStackTrace();
            }

            Image newImage = new Image("file:resources/defaultImage1.png");
            content.putImage(newImage);
            clipboard.setContent(content);
        });

        this.copyUrl.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(this.searchInput.getText());
            clipboard.setContent(content);
        });

        this.b1.setOnAction(e ->
                this.searchInput.setText(searchInput.getText() + "www."));
        this.b2.setOnAction(e ->
                this.searchInput.setText(searchInput.getText() + ".com"));

        this.scene = new Scene(root);
        this.stage.sizeToScene();
        this.stage.setScene(scene);


        this.stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
