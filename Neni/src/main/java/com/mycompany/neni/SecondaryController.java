/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.neni;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author leoan
 */
public class SecondaryController implements Initializable {

    //private int numeroAncho = (int) (Math.random() * 500 + 1);
    //private int numeroAltura = (int) (Math.random() * 300 + 1);
    //private int numeroFoto = (int) (Math.random() * 40 + 1);
    @FXML
    private Pane pane;
    private ArrayList<String> listaNumeros;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaAleatoria();
        ubicarFotos();
        mostrarPregunta(pane);
    }

    private void mostrarPregunta(Pane pane) {
        pane.setOnMouseClicked((MouseEvent t) -> {
            pregunta(pane);
        });
    }

    private void ubicarFotos() {
        Thread hilo1 = new Thread(() -> {
            mostrarFotos(listaNumeros);
        });
        hilo1.setDaemon(true);
        hilo1.start();
    }

    private void mostrarFotos(ArrayList<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            String imagen = App.pathImg + "Imagen" + lista.get(i) + ".jpg";
            Platform.runLater(() -> {
                int numeroAncho = (int) (Math.random() * 1000 + 1);
                int numeroAltura = (int) (Math.random() * 400 + 1);
                ImageView vistaImagen = null;
                try ( FileInputStream fis = new FileInputStream(imagen)) {
                    Image foto = new Image(fis, 300, 300, false, false);
                    vistaImagen = new ImageView(foto);
                    vistaImagen.relocate(numeroAncho, numeroAltura);
                } catch (IOException e) {
                    System.out.println("No se encuentra la imagen");
                }
                pane.getChildren().add(vistaImagen);
            });
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

    private ArrayList<String> listaAleatoria() {
        listaNumeros = new ArrayList<>();
        for (int i = 1; i < 53; i++) {
            listaNumeros.add(String.valueOf(i));
        }
        Collections.shuffle(listaNumeros);
        return listaNumeros;
    }

    private void pregunta(Pane pane) {
        pane.getChildren().clear();
        VBox vbox1 = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        Button btSi = new Button("Sí");
        btSi.setFont(Font.font("Book Antiqua", 36));
        hbox2.getChildren().add(btSi);
        hbox2.setSpacing(20);
        Label preglb = new Label("Puedo ser tu novio?");
        hbox1.getChildren().add(preglb);
        vbox1.getChildren().addAll(hbox1, hbox2);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setMinHeight(100);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setMinHeight(100);
        vbox1.setMinWidth(600);
        preglb.setFont(Font.font("Book Antiqua", 48));
        vbox1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        pane.getChildren().add(vbox1);
        vbox1.setLayoutX(350);
        vbox1.setLayoutY(250);
        btSi.setOnAction((ActionEvent t) -> {
            acepta();
        });
    }

    public void acepta() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("YA SOMOS NOVIOS");
        info.setHeaderText("Ahora somos novios <3");
        info.setContentText("Te amo neni");
        info.showAndWait();
    }

}
