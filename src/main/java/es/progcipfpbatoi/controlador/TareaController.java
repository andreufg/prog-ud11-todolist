package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.modelo.dto.Categoria;
import es.progcipfpbatoi.modelo.dto.Tarea;
import es.progcipfpbatoi.modelo.repositorios.TareaRepository;
import es.progcipfpbatoi.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TareaController implements Initializable {
    @FXML
    private ListView<Tarea> tareaListView;

    @FXML
    private TextField nuevaTareaTextField;

    @FXML
    private ComboBox<Categoria> categorySelector;

    @FXML
    private TextField searchBar;

    private TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    private ObservableList<Tarea> getData() {
        try {
            return FXCollections.observableArrayList(tareaRepository.findAll());
        }catch (DatabaseErrorException ex) {
            ex.printStackTrace();
            AlertMessages.mostrarAlertError(ex.getMessage());
            return null;
        }
    }

    @FXML
    private void addNewTask() {
        try {
            Categoria categoria = categorySelector.getSelectionModel().getSelectedItem();
            String descripcion = nuevaTareaTextField.getText();
            if (categoria == null) {
                AlertMessages.mostrarAlertError("Debe seleccionar una categoría");
            } else if (descripcion.equals("")) {
                AlertMessages.mostrarAlertError("Debe introducir una descripción");
            } else {
                Tarea tarea = new Tarea(getNewTaskIndex(), nuevaTareaTextField.getText(), categoria);
                tareaRepository.save(tarea);
                tareaListView.getItems().add(tarea);
                nuevaTareaTextField.setText("");
                categorySelector.getSelectionModel().clearSelection();
            }
        }catch (DatabaseErrorException ex) {
            ex.printStackTrace();
            AlertMessages.mostrarAlertError("No se ha podido guardar la tarea. Error en el acceso a la base de datos.");
        }
    }

    private int getNewTaskIndex() {
        if (tareaListView.getItems().size() == 0) {
            return 0;
        } else {
            Tarea ultimaTarea = tareaListView.getItems().get(tareaListView.getItems().size() - 1);
            return ultimaTarea.getId() + 1;
        }
    }

    @FXML
    private void searchTasks() {

        try {
            tareaListView.getItems().clear();
            String texto = searchBar.getText();
            ArrayList<Tarea> tareas = tareaRepository.findAll(texto);
            tareaListView.getItems().addAll(tareas);
        } catch (DatabaseErrorException ex) {
            ex.printStackTrace();
            AlertMessages.mostrarAlertError(ex.getMessage());
        }
    }

    @FXML
    private void handleSelectedItem(MouseEvent event) {
        try {
            Tarea tarea = tareaListView.getSelectionModel().getSelectedItem();
            TareaDetailController tareaDetailController = new TareaDetailController(
                    tarea, tareaRepository, this, "/vistas/tarea_list.fxml");
            ChangeScene.change(event, tareaDetailController, "/vistas/tarea_detail.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaListView.setItems(getData());
        tareaListView.setCellFactory((ListView<Tarea> l) -> new TaskListViewCellController());
        categorySelector.setItems(FXCollections.observableArrayList(Categoria.values()));
    }
}
