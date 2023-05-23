import java.util.HashMap;
import java.util.Map;


public class CurrencyConverter {
    private Map<String, Double> conversionRates;

    public CurrencyConverter() {
        conversionRates = new HashMap<>();

        conversionRates.put("EUR", 1.0);
        conversionRates.put("USD", 1.12);
        conversionRates.put("GBP", 0.89);
        conversionRates.put("AFN", 93.21);
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double conversionRateFrom = conversionRates.getOrDefault(fromCurrency, 1.0);
        double conversionRateTo = conversionRates.getOrDefault(toCurrency, 1.0);
        return amount * (conversionRateTo / conversionRateFrom);
    }
}
