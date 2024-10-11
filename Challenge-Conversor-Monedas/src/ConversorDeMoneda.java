import java.util.Scanner;

public class ConversorDeMoneda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        APIDeTasasDeCambio apiDeTasasDeCambio = new APIDeTasasDeCambio();

        while (true) {
            mostrarMenu();

            int opcion = scanner.nextInt();
            if (opcion == 7) {
                System.out.println("¡Gracias por usar el conversor de moneda!");
                break;
            }

            if (opcion < 1 || opcion > 7) {
                System.out.println("Por favor seleccione una opción válida.");
                continue;
            }

            System.out.print("Ingrese el valor que desea convertir: ");
            double valor = scanner.nextDouble();
            double valorConvertido = 0.0;

            switch (opcion) {
                case 1:
                    valorConvertido = apiDeTasasDeCambio.convertir("USD", "ARS", valor);
                    System.out.printf("El valor de %.1f [USD] corresponde al valor final de =>>> %.2f [ARS]%n",
                            valor, valorConvertido);
                    break;
                case 2:
                    valorConvertido = apiDeTasasDeCambio.convertir("ARS", "USD", valor);
                    System.out.printf("El valor de %.1f [ARS] corresponde al valor final de =>>> %.2f [USD]%n",
                            valor, valorConvertido);
                    break;
                case 3:
                    valorConvertido = apiDeTasasDeCambio.convertir("USD", "BRL", valor);
                    System.out.printf("El valor de %.1f [USD] corresponde al valor final de =>>> %.2f [BRL]%n",
                            valor, valorConvertido);
                    break;
                case 4:
                    valorConvertido = apiDeTasasDeCambio.convertir("BRL", "USD", valor);
                    System.out.printf("El valor de %.1f [BRL] corresponde al valor final de =>>> %.2f [USD]%n",
                            valor, valorConvertido);
                    break;
                case 5:
                    valorConvertido = apiDeTasasDeCambio.convertir("USD", "COP", valor);
                    System.out.printf("El valor de %.1f [USD] corresponde al valor final de =>>> %.2f [COP]%n",
                            valor, valorConvertido);
                    break;
                case 6:
                    valorConvertido = apiDeTasasDeCambio.convertir("COP", "USD", valor);
                    System.out.printf("El valor de %.1f [COP] corresponde al valor final de =>>> %.2f [USD]%n",
                            valor, valorConvertido);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        String menu = """
                **************************************************
                Sea bienvenido/a al Conversor de Moneda :)
                1) Dólar =>> Peso argentino
                2) Peso Argentino =>> Dólar
                3) Dólar =>> Real Brasileño
                4) Real Brasileño =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Salir
                Elija una opción por favor:
                **************************************************
                """;
        System.out.print(menu);
    }
}
