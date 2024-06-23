import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class MonedaAdapter extends TypeAdapter<Moneda> {
    @Override
    public void write(JsonWriter out, Moneda moneda) throws IOException {
        out.beginObject();
        out.name("base_code").value(moneda.base_code());
        out.name("target_code").value(moneda.target_code());
        out.name("conversion_rates").value(moneda.conversion_rates());
        out.name("conversion_result").value(moneda.conversion_result());
        out.endObject();
    }

    @Override
    public Moneda read(JsonReader in) throws IOException {
        in.beginObject();
        String baseCode = null;
        String targetCode = null;
        Double conversionRates = null;
        Double conversionResult = null;

        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "base_code":
                    baseCode = in.nextString();
                    break;
                case "target_code":
                    targetCode = in.nextString();
                    break;
                case "conversion_rates":
                    conversionRates = in.nextDouble();
                    break;
                case "conversion_result":
                    conversionResult = in.nextDouble();
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return new Moneda(baseCode, targetCode, conversionRates, conversionResult);
    }
}