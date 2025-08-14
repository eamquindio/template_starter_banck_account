package edu.eam.logica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BankAccountApp extends Application {
    
    private Cuenta cuenta;
    private Label saldoLabel;
    private Label transaccionesLabel;
    private Label tipoCuentaLabel;
    private TextField montoField;
    private TextArea historialArea;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cuenta Bancaria");
        
        // Layout principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        
        // Sección de creación de cuenta
        HBox crearCuentaBox = new HBox(10);
        crearCuentaBox.setAlignment(Pos.CENTER);
        
        TextField saldoInicialField = new TextField("0");
        saldoInicialField.setPromptText("Saldo inicial");
        saldoInicialField.setPrefWidth(100);
        
        ComboBox<String> tipoCuentaCombo = new ComboBox<>();
        tipoCuentaCombo.getItems().addAll("Básica", "Premium");
        tipoCuentaCombo.setValue("Básica");
        
        Button crearCuentaBtn = new Button("Crear Cuenta");
        
        crearCuentaBox.getChildren().addAll(
            new Label("Saldo inicial:"), saldoInicialField,
            new Label("Tipo:"), tipoCuentaCombo,
            crearCuentaBtn
        );
        
        // Información de la cuenta
        GridPane infoCuenta = new GridPane();
        infoCuenta.setHgap(10);
        infoCuenta.setVgap(10);
        infoCuenta.setAlignment(Pos.CENTER);
        
        saldoLabel = new Label("$0.00");
        saldoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        transaccionesLabel = new Label("0");
        tipoCuentaLabel = new Label("-");
        
        infoCuenta.add(new Label("Saldo:"), 0, 0);
        infoCuenta.add(saldoLabel, 1, 0);
        infoCuenta.add(new Label("Transacciones del mes:"), 0, 1);
        infoCuenta.add(transaccionesLabel, 1, 1);
        infoCuenta.add(new Label("Tipo de cuenta:"), 0, 2);
        infoCuenta.add(tipoCuentaLabel, 1, 2);
        
        // Sección de transacciones
        VBox transaccionesBox = new VBox(10);
        transaccionesBox.setAlignment(Pos.CENTER);
        
        HBox montoBox = new HBox(10);
        montoBox.setAlignment(Pos.CENTER);
        montoField = new TextField();
        montoField.setPromptText("Monto");
        montoField.setPrefWidth(150);
        montoBox.getChildren().addAll(new Label("Monto:"), montoField);
        
        HBox botonesBox = new HBox(10);
        botonesBox.setAlignment(Pos.CENTER);
        Button depositarBtn = new Button("Depositar");
        Button retirarBtn = new Button("Retirar");
        depositarBtn.setDisable(true);
        retirarBtn.setDisable(true);
        botonesBox.getChildren().addAll(depositarBtn, retirarBtn);
        
        transaccionesBox.getChildren().addAll(montoBox, botonesBox);
        
        // Área de historial
        historialArea = new TextArea();
        historialArea.setEditable(false);
        historialArea.setPrefHeight(150);
        historialArea.setPromptText("Historial de transacciones aparecerá aquí...");
        
        // Separadores
        Separator sep1 = new Separator();
        Separator sep2 = new Separator();
        Separator sep3 = new Separator();
        
        // Agregar todos los componentes
        root.getChildren().addAll(
            new Label("SISTEMA DE CUENTA BANCARIA"),
            sep1,
            crearCuentaBox,
            sep2,
            infoCuenta,
            sep3,
            transaccionesBox,
            new Label("Historial:"),
            historialArea
        );
        
        // Event Handlers
        crearCuentaBtn.setOnAction(e -> {
            try {
                double saldoInicial = Double.parseDouble(saldoInicialField.getText());
                String tipo = tipoCuentaCombo.getValue();
                cuenta = new Cuenta(saldoInicial, tipo);
                actualizarInterfaz();
                depositarBtn.setDisable(false);
                retirarBtn.setDisable(false);
                historialArea.appendText("Cuenta creada con saldo inicial: $" + 
                    String.format("%.2f", saldoInicial) + " - Tipo: " + tipo + "\n");
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "Por favor ingrese un número válido para el saldo inicial");
            }
        });
        
        depositarBtn.setOnAction(e -> {
            try {
                double monto = Double.parseDouble(montoField.getText());
                if (cuenta.depositar(monto)) {
                    actualizarInterfaz();
                    historialArea.appendText("Depósito exitoso: $" + 
                        String.format("%.2f", monto) + " - Nuevo saldo: $" + 
                        String.format("%.2f", cuenta.getSaldo()) + "\n");
                    montoField.clear();
                } else {
                    mostrarAlerta("Error", "El monto debe ser mayor a 0");
                }
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "Por favor ingrese un monto válido");
            }
        });
        
        retirarBtn.setOnAction(e -> {
            try {
                double monto = Double.parseDouble(montoField.getText());
                if (cuenta.retirar(monto)) {
                    actualizarInterfaz();
                    historialArea.appendText("Retiro exitoso: $" + 
                        String.format("%.2f", monto) + " - Nuevo saldo: $" + 
                        String.format("%.2f", cuenta.getSaldo()) + "\n");
                    montoField.clear();
                } else {
                    mostrarAlerta("Error", "Monto inválido o saldo insuficiente");
                }
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "Por favor ingrese un monto válido");
            }
        });
        
        Scene scene = new Scene(root, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void actualizarInterfaz() {
        saldoLabel.setText("$" + String.format("%.2f", cuenta.getSaldo()));
        transaccionesLabel.setText(String.valueOf(cuenta.getTransaccionesMensuales()));
        tipoCuentaLabel.setText(cuenta.getTipoCuenta());
        
        // Mostrar advertencia de comisiones
        if (cuenta.getTransaccionesMensuales() == 3) {
            historialArea.appendText("⚠️ AVISO: Las próximas transacciones tendrán comisión\n");
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}