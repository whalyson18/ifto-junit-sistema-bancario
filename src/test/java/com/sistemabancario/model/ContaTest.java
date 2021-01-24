package com.sistemabancario.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    void testSetNumeroValido() {
        final Conta instance = new Conta();
        final String esperado = "12345-6";
        instance.setNumero(esperado);
        final String obtido = instance.getNumero();
        assertEquals(esperado, obtido);
    }

//    @Test
//    void testSetNumeroInvalidoExcecao() {
//        final Conta instance = new Conta();
//        final String invalido = "123";
//        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));
//    }
    
    @Test
    void testSetNumeroinvalidoNaoArmazena(){
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }
    
    @Test
    void testSetNumeroNulo(){
        final Conta instance = new Conta();
        assertThrows(NullPointerException.class, () -> instance.setNumero(null));
    }
    
    @Test
    void testInstaciaPadraoPoupanca(){
        final Conta instance = new Conta();
        assertFalse(instance.isPoupanca());
    }
    
    @Test
    void testSetLimiteContaEspecial(){
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 1000;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testSetLimiteContaNaoEspecial(){
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
    }
    
    @Test
    void testHistoricoNotNull(){
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());
    }
    
    @Test
    void testGetSaldoTotal(){
        final double limite = 500;
        final double esperado = limite;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testDepositoDinheiro(){
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }
    
    @Test
    void testDepositoDinheiroCredito(){
        final char esperado = 'C';
        final Movimentacao instance = new Movimentacao(new Conta());
        instance.setTipo('C');
        
        final char obtido = instance.getTipo();
        
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testDepositoDinheiroConfirmado(){
        final boolean esperado = true;
        final Movimentacao instance = new Movimentacao(new Conta());
        instance.setConfirmada(true);
        
        final boolean obtido = instance.isConfirmada();
        
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testDepositoDinheiroConfirmarValor(){
        final double esperado = 500;
        final Movimentacao instance = new Movimentacao(new Conta());
        
        instance.setValor(esperado);
        
        final double obtido = instance.getValor();
        
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testDepositoDinheiroMovimentacoes(){
        final double limite = 500, deposito = 500;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        
        final List<Movimentacao> obtido = instance.getMovimentacoes();
        
        assertNotNull(obtido);
        
        //assertEquals(esperado, obtido);
    }
}
