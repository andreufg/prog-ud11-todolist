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

public class TareaController {
    @FXML
    private ListView<Tarea> tareaListView;

    @FXML
    private TextField nuevaTareaTextField;

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
}
