module template_bank_account {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens edu.eam.logica to javafx.fxml;
    exports edu.eam.logica;
}