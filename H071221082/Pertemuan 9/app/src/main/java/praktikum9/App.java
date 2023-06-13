/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package praktikum9;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private Stage primaryStage;

    DropShadow dropShadow = new DropShadow();
    BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(10), null);
    Background background = new Background(backgroundFill);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Label label = new Label("Master Math");
        label.setAlignment(Pos.CENTER);

        label.setPrefHeight(40);
        label.setPrefWidth(200);
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        Image logoImage = new Image("MM.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(90);
        logoImageView.setFitHeight(100);

        Button startButton = new Button("START");
        startButton.setPrefWidth(120);  
        startButton.setPrefHeight(50);  
        startButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        startButton.setOnAction(event -> {
            showMainMenu();;
        });

        label.setEffect(dropShadow); startButton.setEffect(dropShadow);        
        label.setBackground(background); startButton.setBackground(background);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, logoImageView, startButton);

        Scene scene1 = new Scene(root, 300,400);
        primaryStage.setTitle("MaMath");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    private void showMainMenu() {
        Label label = new Label("PILIH MENU");
        label.setAlignment(Pos.CENTER);
        
        label.setPrefHeight(40);
        label.setPrefWidth(200);
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        Button backButton = new Button("BACK");
        backButton.setPrefWidth(80);  
        backButton.setPrefHeight(40);  
        backButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        backButton.setOnAction(event -> {
            start(primaryStage);
        });

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Konversi Suhu", "Hitung Kata");
        
        String defaultText = "---";
        comboBox.setPromptText(defaultText);
        comboBox.setPrefWidth(150);
        comboBox.setPrefHeight(10);

        comboBox.setOnAction(event -> {
            String selectedMenu = comboBox.getSelectionModel().getSelectedItem();
            comboBox.setPromptText(selectedMenu);
        });
        
        Button okButton = new Button("OK");
        okButton.setPrefWidth(28);
        okButton.setPrefHeight(25);
        okButton.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");
        
        okButton.setOnAction(event -> {
            String selectedMenu = comboBox.getSelectionModel().getSelectedItem();
            if (selectedMenu.equals("Konversi Suhu")) {
                temperatureScene();
            } else if (selectedMenu.equals("Hitung Kata")) {
                wordsCountScene();
            }
        });

        label.setEffect(dropShadow); backButton.setEffect(dropShadow); 
        label.setBackground(background); backButton.setBackground(background); okButton.setBackground(background);
        
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(comboBox, okButton);

        VBox root = new VBox(20);
        root.getChildren().addAll(label, hbox, backButton);

        root.setAlignment(Pos.CENTER); hbox.setAlignment(Pos.CENTER);
        Scene scene2 = new Scene(root, 300, 400);

        primaryStage.setScene(scene2);
    }

    private void wordsCountScene() {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefWidth(350);
        textArea.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        Button backButton = new Button("BACK");
        backButton.setPrefWidth(80);  
        backButton.setPrefHeight(40);  
        backButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        backButton.setOnAction(e -> showMainMenu());

        Label totalHuruf = new Label("Huruf: \n");
        totalHuruf.setPrefHeight(30);
        totalHuruf.setPrefWidth(100);
        totalHuruf.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        Label totalAngka = new Label("Angka: \n");
        totalAngka.setPrefHeight(30);
        totalAngka.setPrefWidth(100);
        totalAngka.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        Label totalKata = new Label("Kata: \n");
        totalKata.setPrefHeight(30);
        totalKata.setPrefWidth(100);
        totalKata.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");
       
        Button countButton = new Button("Hitung");
        countButton.setOnAction(e -> {
            String text = textArea.getText();
            if (text == ""){
                totalHuruf.setText("Huruf: 0" );
                totalAngka.setText("Angka: 0");
                totalKata.setText("Kata: 0");
            } else{
                totalHuruf.setText("Huruf: " + hitungHuruf(text));
                totalAngka.setText("Angka: " + hitungAngka(text));
                totalKata.setText("Kata: " + hitungKata(text));
            }
        });
        Button clearButton = new Button("CLEAR");
        clearButton.setPrefWidth(80);  
        clearButton.setPrefHeight(40);  
        clearButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        clearButton.setBackground(background);
        clearButton.setOnAction(e -> {
            textArea.clear();
            totalHuruf.setText("Huruf: \n");
            totalAngka.setText("Angka: \n");
            totalKata.setText("Kata: \n");
        });

        HBox hasil = new HBox(10);
        hasil.getChildren().addAll(totalHuruf, totalAngka, totalKata);
        
        HBox menuButton = new HBox(10);
        menuButton.getChildren().addAll(backButton, clearButton);
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(textArea, countButton, hasil, menuButton);

        totalHuruf.setEffect(dropShadow); totalAngka.setEffect(dropShadow); totalKata.setEffect(dropShadow); backButton.setEffect(dropShadow); clearButton.setEffect(dropShadow);
        backButton.setBackground(background); totalHuruf.setBackground(background); totalAngka.setBackground(background); totalKata.setBackground(background);
        totalHuruf.setAlignment(Pos.CENTER); totalAngka.setAlignment(Pos.CENTER); totalKata.setAlignment(Pos.CENTER); hasil.setAlignment(Pos.CENTER); menuButton.setAlignment(Pos.CENTER);

        Scene scene3 = new Scene(root, 300, 400);
        primaryStage.setTitle("MaMath");
        primaryStage.setScene(scene3);
        primaryStage.show();
    }

    private int hitungHuruf(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }
        return count;
    }

    private int hitungAngka(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    private int hitungKata(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    private void temperatureScene() {
        Label label1 = new Label("Temperature \n Conversion");
        label1.setPrefHeight(80);
        label1.setPrefWidth(190);
        label1.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        ComboBox<String> suhu = new ComboBox<>();
        suhu.setPromptText("Suhu Yang Akan Dikonversi...");
        suhu.getItems().addAll("Celcius", "Fahrenheit", "Kelvin", "Reamur");

        Text hasilText = new Text("Hasil");
        hasilText.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");

        Label hasil1 = new Label("Celcius\n");
        hasil1.setPrefHeight(40);
        hasil1.setPrefWidth(60);
        hasil1.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        Label hasil2 = new Label("Fahrenheit\n");
        hasil2.setPrefHeight(40);
        hasil2.setPrefWidth(75);
        hasil2.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        Label hasil3 = new Label("Kelvin\n");
        hasil3.setPrefHeight(40);
        hasil3.setPrefWidth(60);
        hasil3.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        Label hasil4 = new Label("Reamur\n");
        hasil4.setPrefHeight(40);
        hasil4.setPrefWidth(60);
        hasil4.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        TextField nilai = new TextField();
        nilai.setMaxWidth(70);

        Button convertButton = new Button("Convert");
        convertButton.setPrefWidth(60);
        convertButton.setPrefHeight(25);
        convertButton.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");

        convertButton.setOnAction(event -> {
            String input = nilai.getText();
            String convert = suhu.getSelectionModel().getSelectedItem();
            double temperature = Double.parseDouble(input);
            if (convert.equals("Celcius")){
                double fahrenheit = (temperature * 9/5) + 32;
                double kelvin = temperature + 273;
                double reamur = temperature * 4/5;

                hasil1.setText("  Celcius:\n  " + String.format("%.2f", temperature) + "°C");
                hasil2.setText("  Fahrenheit:\n  " + String.format("%.2f", fahrenheit) + "°F");
                hasil3.setText("  Kelvin:\n  " + String.format("%.2f", kelvin) + " K");
                hasil4.setText("  Reamur:\n  " + String.format("%.2f", reamur) + "°R");

            } else if (convert.equals("Fahrenheit")){
                double celcius = (temperature - 32) * 5/9;
                double kelvin = (temperature + 460) * 5/9;
                double reamur = (temperature - 32 * 4/9);

                hasil1.setText("  Celcius:\n  " + String.format("%.2f", celcius) + "°C");
                hasil2.setText("  Fahrenheit:\n  " + String.format("%.2f", temperature) + "°F");
                hasil3.setText("  Kelvin:\n  " + String.format("%.2f", kelvin) + " K");
                hasil4.setText("  Reamur:\n  " + String.format("%.2f", reamur) + "°R");

            } else if (convert.equals("Kelvin")){
                double celcius = temperature - 273;
                double fahrenheit = (temperature * 9/5) - 460;
                double reamur = (temperature - 273 ) * 4/5;

                hasil1.setText("  Celcius:\n  " + String.format("%.2f", celcius) + "°C");
                hasil2.setText("  Fahrenheit:\n  " + String.format("%.2f", fahrenheit) + "°F");
                hasil3.setText("  Kelvin:\n  " + String.format("%.2f", temperature) + " K");
                hasil4.setText("  Reamur:\n  " + String.format("%.2f", reamur) + "°R");

            } else if (convert.equals("Reamur")){
                double celcius = temperature * 5/42;
                double fahrenheit = (temperature * 9/4) + 32;
                double kelvin = (temperature * 5/4) + 273;

                hasil1.setText("  Celcius:\n  " + String.format("%.2f", celcius) + "°C");
                hasil2.setText("  Fahrenheit:\n  " + String.format("%.2f", fahrenheit) + "°F");
                hasil3.setText("  Kelvin:\n  " + String.format("%.2f", kelvin) + " K");
                hasil4.setText("  Reamur:\n  " + String.format("%.2f", temperature) + "°R");
            }
        });

        Button backButton = new Button("BACK");
        backButton.setPrefWidth(80);  
        backButton.setPrefHeight(40);  
        backButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        backButton.setOnAction(e -> showMainMenu());

        Button clearButton = new Button("CLEAR");
        clearButton.setPrefWidth(80);  
        clearButton.setPrefHeight(40);  
        clearButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        clearButton.setOnAction(e -> {
            nilai.clear();
            hasil1.setText("Celcius:\n");
            hasil2.setText("Fahrenheit:\n");
            hasil3.setText("Kelvin:\n");
            hasil4.setText("Reamur:\n");
        });

        backButton.setEffect(dropShadow); clearButton.setEffect(dropShadow);

        HBox input = new HBox(10);
        input.getChildren().addAll(nilai, convertButton);
        
        HBox hasil = new HBox(10);
        hasil.getChildren().addAll(hasil1, hasil2, hasil3, hasil4);
        
        HBox menu = new HBox(10);
        menu.getChildren().addAll(backButton, clearButton);
        
        VBox root1 = new VBox(15);
        root1.getChildren().addAll(label1, suhu, input, hasilText, hasil, menu);

        input.setAlignment(Pos.CENTER); hasil.setAlignment(Pos.CENTER); menu.setAlignment(Pos.CENTER); root1.setAlignment(Pos.CENTER);
        label1.setAlignment(Pos.CENTER); hasil1.setAlignment(Pos.CENTER); hasil2.setAlignment(Pos.CENTER); hasil3.setAlignment(Pos.CENTER); hasil4.setAlignment(Pos.CENTER);
        label1.setBackground(background); hasil1.setBackground(background); hasil2.setBackground(background); hasil3.setBackground(background); hasil4.setBackground(background); convertButton.setBackground(background); backButton.setBackground(background); clearButton.setBackground(background);
        
        Scene scene3 = new Scene(root1, 300, 400);

        primaryStage.setScene(scene3);
    }
}