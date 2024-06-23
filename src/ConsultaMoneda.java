import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    String apiKey = "a0f61c9cb188743d95ec09e1";

    public Moneda buscaMoneda (String baseCode, String targetCode, double monto) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCode + "/" + targetCode + "/" + monto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Moneda.class, new MonedaAdapter())
                    .create();

            return gson.fromJson(response.body(), Moneda.class);

        } catch (Exception e) {
            throw new RuntimeException("La moneda ingresada no es valida", e);
        }
    }
}