import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIDeTasasDeCambio {
    private static final String API_KEY = "c4ec57d31ab5c8a9c654f179"; // Reemplaza con tu clave propia de API
    private static final String URL_API = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double convertir(String monedaDesde, String monedaHasta, double cantidad) {
        try {
            String urlStr = URL_API + monedaDesde;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Comprobar si la respuesta es correcta
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error: " + conn.getResponseCode());
            }

            // Leer la respuesta de la API
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder respuesta = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                respuesta.append(inputLine);
            }
            in.close();

            // Parsear el JSON para obtener la tasa de cambio
            return parsearTasaDeCambio(respuesta.toString(), monedaHasta, cantidad);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private double parsearTasaDeCambio(String jsonRespuesta, String monedaHasta, double cantidad) {
        JsonObject jsonObject = JsonParser.parseString(jsonRespuesta).getAsJsonObject();
        JsonObject tasasDeCambio = jsonObject.getAsJsonObject("conversion_rates");

        if (tasasDeCambio.has(monedaHasta)) {
            double tasaDeCambio = tasasDeCambio.get(monedaHasta).getAsDouble();
            return cantidad * tasaDeCambio;
        } else {
            throw new RuntimeException("Moneda no encontrada: " + monedaHasta);
        }
    }
}
