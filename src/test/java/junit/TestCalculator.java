/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit;

import br.com.cursojavanow.dynamicreports.junit.exemplos.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author michel adriano medeiros
 */
public class TestCalculator {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        Assertions.assertEquals(60, result, 0);
    }

}
