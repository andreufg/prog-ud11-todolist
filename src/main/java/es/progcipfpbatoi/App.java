package es.progcipfpbatoi;

import es.progcipfpbatoi.controlador.ChangeScene;
import es.progcipfpbatoi.controlador.TareaController;
import es.progcipfpbatoi.controlador.TareaSearchController;
import es.progcipfpbatoi.modelo.dao.*;
import es.progcipfpbatoi.modelo.repositorios.TareaRepository;
import es.progcipfpbatoi.services.MySqlConnection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Creación del el DAO correspondiente. Aquí se selecciona el tipo de persistencia que utilizará la app

        //InMemoryTareaDAO tareaDAO = new InMemoryTareaDAO();
        //InMemoryCategoriaDAO categoriaDAO = new InMemoryCategoriaDAO();

        //FileTareaDAO tareaDAO = new FileTareaDAO();
        //FileCategoriaDAO categoriaDAO = new FileCategoriaDAO();

        SQLTareaDAO tareaDAO = new SQLTareaDAO();
        SQLCategoriaDAO categoriaDAO = new SQLCategoriaDAO();


        // Creación del repositorio que será el que interactuará con el controlador.
        TareaRepository tareaRepository = new TareaRepository(tareaDAO, categoriaDAO);

        // Se crea al controlador proporcionando el/los repositorio/s que necesita
        TareaController tareaController = new TareaController(tareaRepository);
        // Muestra de la escena principal.
        ChangeScene.change(stage, tareaController, "/vistas/tarea_list.fxml");

        // Cerramos la conexión al cerrar la aplicación
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.out.println("App closed");
                new MySqlConnection("localhost", "tasks_db", "root", "123456").closeConnection();
            }
        });
    }



    public static void main(String[] args) {
        launch();
    }
}