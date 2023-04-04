package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.modelo.entidades.Tarea;
import es.progcipfpbatoi.modelo.repositorios.TareaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class TareaController {
    @FXML
    private ListView<Tarea> tareaListView;

    @FXML
    private TextField nuevaTareaTextField;

    @FXML
    private TextField searchBar;

    private TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @FXML
    public void initialize() {
        System.out.println(nuevaTareaTextField.getPromptText());
        tareaListView.setItems(getData());
    }

    private ObservableList<Tarea> getData() {
        return FXCollections.observableArrayList(tareaRepository.findAll());
    }

    @FXML
    private void addNewTask() {
        Tarea tarea = new Tarea(
                tareaListView.getItems().size() + 1,
                nuevaTareaTextField.getText());
        if (tareaRepository.save(tarea)) {
            tareaListView.getItems().add(tarea);
            nuevaTareaTextField.setText("");
        }
    }

    @FXML
    private void searchTasks() {

        tareaListView.getItems().clear();
        String texto = searchBar.getText();
        ArrayList<Tarea> tareas = tareaRepository.findAll(texto);
        tareaListView.getItems().addAll(tareas);
    }


}
