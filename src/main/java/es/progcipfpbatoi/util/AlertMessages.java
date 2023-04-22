package es.progcipfpbatoi.util;

import javafx.scene.control.Alert;

public class AlertMessages {
    public static void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("No se ha podido guardar la tarea. Error en el acceso a la base de datos.");
        alert.showAndWait();
    }
}
