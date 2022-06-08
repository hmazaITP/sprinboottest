package itpatagonia.com.sprinboottest.utils;

public class Ejemplo {
    public static void main(String[] args) {
        IOperation iOperation = new IOperation() {
            @Override
            public float get() {
                return 5*5;
            }
        };
        //Anonima
        System.out.println(iOperation.get());

        //Lambda
        IOperation resultado = () -> 6+6;
        System.out.println(resultado.get());
    }
}
