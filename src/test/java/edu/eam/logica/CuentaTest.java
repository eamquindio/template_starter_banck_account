package edu.eam.logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {
    
    private static final double DELTA = 0.01;
    
    @Test
    public void testSituacion1_CuentaBasica() {
        Cuenta cuenta = new Cuenta(0.0, "Básica");
        
        assertEquals(0.0, cuenta.getSaldo(), DELTA);
        assertEquals(0, cuenta.getTransaccionesMensuales());
        
        assertTrue(cuenta.depositar(100.0));
        assertEquals(100.0, cuenta.getSaldo(), DELTA);
        assertEquals(1, cuenta.getTransaccionesMensuales());
        
        assertTrue(cuenta.retirar(20.0));
        assertEquals(80.0, cuenta.getSaldo(), DELTA);
        assertEquals(2, cuenta.getTransaccionesMensuales());
        
        assertTrue(cuenta.depositar(50.0));
        assertEquals(130.0, cuenta.getSaldo(), DELTA);
        assertEquals(3, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testSituacion2_CuentaBasicaConComision() {
        Cuenta cuenta = new Cuenta(0.0, "Básica");
        
        assertTrue(cuenta.depositar(200.0));
        assertEquals(200.0, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.retirar(50.0));
        assertEquals(150.0, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.depositar(20.0));
        assertEquals(170.0, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.retirar(30.0));
        assertEquals(138.50, cuenta.getSaldo(), DELTA);
        assertEquals(4, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testSituacion3_CuentaPremium() {
        Cuenta cuenta = new Cuenta(1000.0, "Premium");
        
        assertEquals(1000.0, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.depositar(500.0));
        assertEquals(1500.0, cuenta.getSaldo(), DELTA);
        assertEquals(1, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testSituacion4_CuentaPremiumConMultiplesComisiones() {
        Cuenta cuenta = new Cuenta(50.0, "Premium");
        
        assertTrue(cuenta.retirar(10.0));
        assertEquals(40.0, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.depositar(5.0));
        assertEquals(45.0, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.retirar(10.0));
        assertEquals(35.0, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.retirar(5.0));
        assertEquals(29.25, cuenta.getSaldo(), DELTA);
        
        assertTrue(cuenta.depositar(20.0));
        assertEquals(48.50, cuenta.getSaldo(), DELTA);
        assertEquals(5, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testSituacion5_RetiroInvalido() {
        Cuenta cuenta = new Cuenta(30.0, "Básica");
        
        assertFalse(cuenta.retirar(50.0));
        assertEquals(30.0, cuenta.getSaldo(), DELTA);
        assertEquals(0, cuenta.getTransaccionesMensuales());
        
        assertTrue(cuenta.depositar(20.0));
        assertEquals(50.0, cuenta.getSaldo(), DELTA);
        assertEquals(1, cuenta.getTransaccionesMensuales());
        
        assertTrue(cuenta.retirar(10.0));
        assertEquals(40.0, cuenta.getSaldo(), DELTA);
        assertEquals(2, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testDepositoInvalido() {
        Cuenta cuenta = new Cuenta();
        
        assertFalse(cuenta.depositar(0));
        assertFalse(cuenta.depositar(-100));
        assertEquals(0.0, cuenta.getSaldo(), DELTA);
        assertEquals(0, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testRetiroMayorQueSaldo() {
        Cuenta cuenta = new Cuenta(100.0, "Básica");
        
        assertFalse(cuenta.retirar(150.0));
        assertEquals(100.0, cuenta.getSaldo(), DELTA);
        assertEquals(0, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testConstructorPorDefecto() {
        Cuenta cuenta = new Cuenta();
        
        assertEquals(0.0, cuenta.getSaldo(), DELTA);
        assertEquals("Básica", cuenta.getTipoCuenta());
        assertEquals(0, cuenta.getTransaccionesMensuales());
    }
    
    @Test
    public void testRedondeoADosDecimales() {
        Cuenta cuenta = new Cuenta(10.999, "Básica");
        assertEquals(11.0, cuenta.getSaldo(), DELTA);
        
        cuenta.depositar(0.001);
        assertEquals(11.0, cuenta.getSaldo(), DELTA);
    }
}