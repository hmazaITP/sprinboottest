package itpatagonia.com.sprinboottest.utils;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void init(){
        System.out.println("Inicio de cada metodo");
        calculator = new Calculator();
    }

    @Test
    @Order(3)
    void sumar() {
        float expected = 10;
        System.out.println("sumar");
        assertEquals(expected ,calculator.sumar(5,5));
    }

    @Test
    @Order(4)
    void restar(){
        System.out.println("restar");
        assertEquals(2, calculator.restar(4,2));
    }

    @Test
    @Order(2)
    void multiplicar(){
        System.out.println("multiplicar");
        assertEquals(8, calculator.multiplicar(4,2));
    }

    @Test
    @Order(1)
    @DisplayName("Test de Dividir ")
    void dividir(){
        System.out.println("dividir");
        assertEquals(9, calculator.dividir(18,2));
    }

    @AfterEach
    void end(){
        System.out.println("Fin de cada metodo");

    }

    @Test
    void testAll(){
        assertAll(
                () -> assertEquals(10, calculator.sumar(5,5)),
                () -> assertEquals(0, calculator.restar(5,5)),
                () -> assertEquals(25, calculator.multiplicar(5,5)),
                () -> assertEquals(1, calculator.dividir(5,5))
                );
    }

    @Test
    void testAll2(){
       assertEquals(10, calculator.sumar(5,5));
       assertEquals(0, calculator.restar(5,5));

    }
}