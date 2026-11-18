package mattresssales;

public class ProductCalculations implements IProductCalculations{
    // Stores the product price
    private double productPrice;

    // Constructor receives the product price
    public ProductCalculations(double productPrice) {
        this.productPrice = productPrice;
    }

    // Calculates VAT at 15%
    @Override
    public double GetVat() {
        return productPrice * 0.15;
    }

    // Calculates commission at 5%
    @Override
    public double GetCommission() {
        return productPrice * 0.05;
    }

    // Calculates the total sales amount
    @Override
    public double GetTotal() {
        return productPrice + GetVat() + GetCommission();
    }

}



