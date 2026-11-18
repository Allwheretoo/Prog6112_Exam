package mattresssales;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import mattresssales.ProductCalculations;

public class MattressSales extends JFrame {

    // Declare GUI components
    private JTextField txtCustomerName;
    private JTextField txtPrice;
    private JComboBox<String> cmbProduct;
    private JButton btnProcess;

    // Used to format currency to two decimal places
    private DecimalFormat df = new DecimalFormat("0.00");

    // Constructor
    public MattressSales(int par) {

        // Set window properties
        setTitle("Product App");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the menu bar
        createMenu();

        // Create the form
        createForm();
    }

    // Method to create the menu bar
    private void createMenu() {

        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");

        // Close the application when Exit is clicked
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitItem);

        // Tools menu
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        // Process menu item calls the process method
        processItem.addActionListener(e -> processSales());

        // Clear menu item clears the form
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }

    // Method to create the GUI form
    private void createForm() {

        // Create a panel with a grid layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));

        panel.setBorder(
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        // Customer name label and text field
        JLabel lblCustomer = new JLabel("Customer Name:");
        txtCustomerName = new JTextField();

        // Product selection label and combo box
        JLabel lblProduct = new JLabel("Select Product:");

        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        cmbProduct = new JComboBox<>(products);

        // Product price label and text field
        JLabel lblPrice = new JLabel("Enter Price:");
        txtPrice = new JTextField();

        // Process button
        btnProcess = new JButton("PROCESS");

        // Add components to the panel
        panel.add(lblCustomer);
        panel.add(txtCustomerName);

        panel.add(lblProduct);
        panel.add(cmbProduct);

        panel.add(lblPrice);
        panel.add(txtPrice);

        // Empty label to position the button
        panel.add(new JLabel(""));
        panel.add(btnProcess);

        // Add button functionality
        btnProcess.addActionListener(e -> processSales());

        // Add panel to the window
        add(panel);
    }

    // Method to process the sales information
    private void processSales() {

        try {

            // Get customer name
            String customerName =
                txtCustomerName.getText().trim();

            // Get selected product
            String productName =
                (String) cmbProduct.getSelectedItem();

            // Get product price
            String priceText = txtPrice.getText().trim();

            // Validate customer name
            if (customerName.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter the customer name."
                );
                return;
            }

            // Validate price
            double price = Double.parseDouble(priceText);

            if (!Double.isFinite(price) || price <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid positive price."
                );
                return;
            }

            // Create ProductCalculations object
            ProductCalculations calculation =
                new ProductCalculations(price);

            // Calculate VAT, commission and total
            double vat = calculation.GetVat();
            double commission = calculation.GetCommission();
            double total = calculation.GetTotal();

            // Save sales information to data.txt
            saveToFile(
                customerName,
                productName,
                vat,
                commission,
                total
            );

            // Display the results in a message box
            String message =
                "Customer Name: " + customerName +
                "\nProduct: " + productName +
                "\nVAT: R " + df.format(vat) +
                "\nCOMMISSION: R " + df.format(commission) +
                "\nTOTAL: R " + df.format(total);

            JOptionPane.showMessageDialog(
                this,
                message,
                "Sales Information",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            // Display error if the price is not numeric
            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric price."
            );

        } catch (IOException e) {

            // Display error if the file cannot be saved
            JOptionPane.showMessageDialog(
                this,
                "Error saving data to file: " + e.getMessage()
            );
        }
    }

    // Method to save sales information to data.txt
    private void saveToFile(
        String customerName,
        String productName,
        double vat,
        double commission,
        double total
    ) throws IOException {

        // FileWriter with true appends information
        // instead of replacing previous records
        try (PrintWriter writer = new PrintWriter(
            new FileWriter("data.txt", true))) {

            writer.println("DATA LOG");
            writer.println("****************************************");
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + productName);
            writer.println("VAT: R " + df.format(vat));
            writer.println("COMMISSION: R " + df.format(commission));
            writer.println("TOTAL: R " + df.format(total));
            writer.println("****************************************");
            writer.println();
        }
    }

    // Method to clear all fields
    private void clearForm() {

        // Clear customer name
        txtCustomerName.setText("");

        // Clear product price
        txtPrice.setText("");

        // Set combo box index back to zero
        cmbProduct.setSelectedIndex(0);
    }

    // Main method - starts the application
    public static void main(String[] args) {

        // Start GUI on the Swing event dispatch thread
        SwingUtilities.invokeLater(() -> {

            MattressSales app = new MattressSales(3500);

            app.setVisible(true);
        });
    }

    
}