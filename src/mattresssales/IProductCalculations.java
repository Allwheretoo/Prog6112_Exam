package mattresssales;

public interface IProductCalculations {
    //Calculates 15% VAT on the product price
    double GetVat();
    
    //Calculates 5% commision on the product price
    double GetCommission();
    
    //Calculates the total sales amount
    double GetTotal();
}
