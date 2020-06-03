package school.cesar.cobranca;

public class CobrancaModel {
    private String sku;
    private double price;

    public CobrancaModel(String sku, double price) {
        this.sku = sku;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }
}