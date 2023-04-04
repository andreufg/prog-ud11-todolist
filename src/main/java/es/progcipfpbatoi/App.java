package es.progcipfpbatoi;

import es.progcipfpbatoi.controlador.TareaController;
import es.progcipfpbatoi.modelo.repositorios.InMemoryTareaRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Creamos la capa de acceso de a datos
        InMemoryTareaRepository inMemoryTareaRepository = new InMemoryTareaRepository();
        TareaController tareaController = new TareaController(inMemoryTareaRepository);

        // Preparamos la vista junto a su controlador
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("vistas/tarea_list.fxml"));
        fxmlLoader.setController(tareaController);

        // Mostramos la vista ya preparada
        AnchorPane rootLayout = fxmlLoader.load();
        stage.setScene(new Scene(rootLayout));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}