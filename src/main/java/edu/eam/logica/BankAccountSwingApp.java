package edu.eam.logica;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BankAccountSwingApp extends JFrame {
    
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private static final Color WARNING_COLOR = new Color(243, 156, 18);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);
    
    private Cuenta cuenta;
    private JLabel saldoLabel;
    private JLabel transaccionesLabel;
    private JLabel tipoCuentaLabel;
    private JTextField montoField;
    private JTextArea historialArea;
    private JButton depositarBtn;
    private JButton retirarBtn;
    
    public BankAccountSwingApp() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Sistema de Cuenta Bancaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Panel principal con scroll
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JPanel titlePanel = createTitlePanel();
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Sección de creación de cuenta
        JPanel crearCuentaPanel = createCrearCuentaPanel();
        mainPanel.add(crearCuentaPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Información de la cuenta
        JPanel infoCuentaPanel = createInfoCuentaPanel();
        mainPanel.add(infoCuentaPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Sección de transacciones
        JPanel transaccionesPanel = createTransaccionesPanel();
        mainPanel.add(transaccionesPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Historial
        JPanel historialPanel = createHistorialPanel();
        mainPanel.add(historialPanel);
        
        // Agregar scroll
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
        
        // Configurar ventana
        setSize(600, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500, 600));
    }
    
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PRIMARY_COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel titleLabel = new JLabel("SISTEMA DE CUENTA BANCARIA");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);
        
        return panel;
    }
    
    private JPanel createCrearCuentaPanel() {
        JPanel panel = createCard("Crear Nueva Cuenta");
        
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(CARD_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Saldo inicial
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(new JLabel("Saldo inicial:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField saldoInicialField = new JTextField("0", 10);
        saldoInicialField.setFont(NORMAL_FONT);
        contentPanel.add(saldoInicialField, gbc);
        
        // Tipo de cuenta
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(new JLabel("Tipo:"), gbc);
        
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JComboBox<String> tipoCuentaCombo = new JComboBox<>(new String[]{"Básica", "Premium"});
        tipoCuentaCombo.setFont(NORMAL_FONT);
        contentPanel.add(tipoCuentaCombo, gbc);
        
        // Botón crear
        gbc.gridx = 4;
        JButton crearCuentaBtn = createStyledButton("Crear Cuenta", PRIMARY_COLOR);
        contentPanel.add(crearCuentaBtn, gbc);
        
        panel.add(contentPanel);
        
        // Event handler
        crearCuentaBtn.addActionListener(e -> {
            try {
                double saldoInicial = Double.parseDouble(saldoInicialField.getText());
                String tipo = (String) tipoCuentaCombo.getSelectedItem();
                cuenta = new Cuenta(saldoInicial, tipo);
                actualizarInterfaz();
                depositarBtn.setEnabled(true);
                retirarBtn.setEnabled(true);
                historialArea.append("✓ Cuenta creada con saldo inicial: $" + 
                    String.format("%.2f", saldoInicial) + " - Tipo: " + tipo + "\n");
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingrese un número válido para el saldo inicial");
            }
        });
        
        return panel;
    }
    
    private JPanel createInfoCuentaPanel() {
        JPanel panel = createCard("Información de la Cuenta");
        
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 20, 10));
        infoPanel.setBackground(CARD_COLOR);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Saldo
        infoPanel.add(createInfoLabel("Saldo:"));
        saldoLabel = new JLabel("$0.00");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        saldoLabel.setForeground(SUCCESS_COLOR);
        infoPanel.add(saldoLabel);
        
        // Transacciones
        infoPanel.add(createInfoLabel("Transacciones del mes:"));
        transaccionesLabel = new JLabel("0");
        transaccionesLabel.setFont(HEADER_FONT);
        infoPanel.add(transaccionesLabel);
        
        // Tipo de cuenta
        infoPanel.add(createInfoLabel("Tipo de cuenta:"));
        tipoCuentaLabel = new JLabel("-");
        tipoCuentaLabel.setFont(HEADER_FONT);
        infoPanel.add(tipoCuentaLabel);
        
        panel.add(infoPanel);
        return panel;
    }
    
    private JPanel createTransaccionesPanel() {
        JPanel panel = createCard("Realizar Transacciones");
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(CARD_COLOR);
        
        // Campo de monto
        JPanel montoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        montoPanel.setBackground(CARD_COLOR);
        montoPanel.add(new JLabel("Monto: $"));
        montoField = new JTextField(15);
        montoField.setFont(NORMAL_FONT);
        montoPanel.add(montoField);
        contentPanel.add(montoPanel);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botonesPanel.setBackground(CARD_COLOR);
        
        depositarBtn = createStyledButton("Depositar", SUCCESS_COLOR);
        depositarBtn.setEnabled(false);
        depositarBtn.setPreferredSize(new Dimension(120, 40));
        
        retirarBtn = createStyledButton("Retirar", DANGER_COLOR);
        retirarBtn.setEnabled(false);
        retirarBtn.setPreferredSize(new Dimension(120, 40));
        
        botonesPanel.add(depositarBtn);
        botonesPanel.add(retirarBtn);
        contentPanel.add(botonesPanel);
        
        panel.add(contentPanel);
        
        // Event handlers
        depositarBtn.addActionListener(e -> {
            try {
                double monto = Double.parseDouble(montoField.getText());
                if (cuenta.depositar(monto)) {
                    actualizarInterfaz();
                    historialArea.append("➕ Depósito exitoso: $" + 
                        String.format("%.2f", monto) + " - Nuevo saldo: $" + 
                        String.format("%.2f", cuenta.getSaldo()) + "\n");
                    montoField.setText("");
                } else {
                    mostrarError("El monto debe ser mayor a 0");
                }
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingrese un monto válido");
            }
        });
        
        retirarBtn.addActionListener(e -> {
            try {
                double monto = Double.parseDouble(montoField.getText());
                if (cuenta.retirar(monto)) {
                    actualizarInterfaz();
                    historialArea.append("➖ Retiro exitoso: $" + 
                        String.format("%.2f", monto) + " - Nuevo saldo: $" + 
                        String.format("%.2f", cuenta.getSaldo()) + "\n");
                    montoField.setText("");
                } else {
                    mostrarError("Monto inválido o saldo insuficiente");
                }
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingrese un monto válido");
            }
        });
        
        return panel;
    }
    
    private JPanel createHistorialPanel() {
        JPanel panel = createCard("Historial de Transacciones");
        
        historialArea = new JTextArea(8, 40);
        historialArea.setEditable(false);
        historialArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        historialArea.setBackground(new Color(250, 250, 250));
        
        JScrollPane scrollPane = new JScrollPane(historialArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        
        panel.add(scrollPane);
        return panel;
    }
    
    private JPanel createCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Agregar sombra
        card.setBorder(BorderFactory.createCompoundBorder(
            new ShadowBorder(),
            card.getBorder()
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        
        return card;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(color.brighter());
                } else {
                    g2.setColor(color);
                }
                
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                
                super.paintComponent(g);
            }
        };
        
        button.setForeground(Color.WHITE);
        button.setFont(NORMAL_FONT);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(NORMAL_FONT);
        label.setForeground(new Color(100, 100, 100));
        return label;
    }
    
    private void actualizarInterfaz() {
        saldoLabel.setText("$" + String.format("%.2f", cuenta.getSaldo()));
        transaccionesLabel.setText(String.valueOf(cuenta.getTransaccionesMensuales()));
        tipoCuentaLabel.setText(cuenta.getTipoCuenta());
        
        // Color del saldo según el monto
        if (cuenta.getSaldo() > 1000) {
            saldoLabel.setForeground(SUCCESS_COLOR);
        } else if (cuenta.getSaldo() > 100) {
            saldoLabel.setForeground(PRIMARY_COLOR);
        } else {
            saldoLabel.setForeground(WARNING_COLOR);
        }
        
        // Mostrar advertencia de comisiones
        if (cuenta.getTransaccionesMensuales() == 3) {
            historialArea.append("⚠️ AVISO: Las próximas transacciones tendrán comisión\n");
        }
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Clase para crear efecto de sombra
    private static class ShadowBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillRoundRect(x + 3, y + 3, width - 3, height - 3, 5, 5);
            g2.dispose();
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 4, 4, 4);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            BankAccountSwingApp app = new BankAccountSwingApp();
            app.setVisible(true);
        });
    }
}