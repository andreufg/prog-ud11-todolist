package es.progcipfpbatoi;

import es.progcipfpbatoi.controlador.ChangeScene;
import es.progcipfpbatoi.controlador.TareaController;
import es.progcipfpbatoi.controlador.TareaDetailController;
import es.progcipfpbatoi.modelo.entidades.Categoria;
import es.progcipfpbatoi.modelo.entidades.Tarea;
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
        ChangeScene.change(stage, tareaController, "/vistas/tarea_list.fxml");

       /* Tarea tarea = new Tarea(6, "Sacar la basura", Categoria.HOGAR);
        TareaDetailController tareaDetailController = new TareaDetailController(tarea, inMemoryTareaRepository);
        ChangeScene.change(stage, tareaDetailController, "/vistas/tarea_detail.fxml");*/
    }

    public static void main(String[] args) {
        launch();
    }

}