package mattresssales;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalesTest {

    // Test 1: Check whether VAT is calculated correctly
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {

        // Arrange: Create an object with a price of R3500
        MattressSales calculation =
            new MattressSales(3500);

        // Act: Calculate VAT
        double result = calculation.GetVat();

        // Assert: 15% of 3500 must equal 525
        assertEquals(525.00, result, 0.01);
    }

    // Test 2: Check whether total is calculated correctly
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {

        // Arrange: Create an object with a price of R3500
        MattressSales  calculation =
            new MattressSales (3500);

        // Act: Calculate total
        double result = calculation.GetTotal();

        // Assert: 3500 + 525 + 175 = 4200
        assertEquals(4200.00, result, 0.01);
    }
}