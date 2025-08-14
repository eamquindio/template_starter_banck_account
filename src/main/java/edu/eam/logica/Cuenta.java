package edu.eam.logica;

public class Cuenta {
    private static final int TRANSACCIONES_GRATUITAS = 3;
    private static final double COMISION_BASICA = 1.50;
    private static final double COMISION_PREMIUM = 0.75;
    
    private double saldo;
    private String tipoCuenta;
    private int transaccionesMensuales;
    
    public Cuenta(double saldoInicial, String tipoCuenta) {
        this.saldo = saldoInicial;
        this.tipoCuenta = tipoCuenta;
        this.transaccionesMensuales = 0;
    }
    
    public Cuenta() {
        this(0.0, "Básica");
    }
    
    public boolean depositar(double monto) {

        
        return true;
    }
    
    public boolean retirar(double monto) {

        
        return true;
    }
    
    private double calcularComision() {
       return 0.0;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public int getTransaccionesMensuales() {
        return transaccionesMensuales;
    }
    
    public String getTipoCuenta() {
        return tipoCuenta;
    }
}