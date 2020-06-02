package school.cesar.estoque;

public class EstoqueModel {
    private String sku;
    private double price;
    private int quantity;

    public EstoqueModel(String sku, int quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }
}