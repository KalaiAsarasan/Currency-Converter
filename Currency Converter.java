import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class CurrencyConverter extends Frame implements ActionListener {
    
    // Define components
    Label labelFrom, labelTo, labelAmount, labelResult;
    Choice choiceFrom, choiceTo;
    TextField textAmount, textResult;
    Button buttonConvert;
    
    // Exchange rates (for simplicity, we'll use hardcoded rates)
    HashMap<String, Double> exchangeRates;

    // Declare an Image object for the background image
    Image backgroundImage;

    public CurrencyConverter() {
        // Set up the frame
        setTitle("Currency Converter");
        setSize(500, 350);
        setLayout(new FlowLayout());
        
        // Set the background color for the frame (it will be replaced by the image)
        setBackground(new Color(173, 216, 230)); // Light blue background

        // Initialize exchange rates
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("INR", 82.74);
        exchangeRates.put("EUR", 0.92);  // Example: 1 USD = 0.92 EUR
        exchangeRates.put("GBP", 0.77);  // Example: 1 USD = 0.77 GBP
        exchangeRates.put("JPY", 148.76); // Example: 1 USD = 148.76 JPY

        // Load the background image
        backgroundImage = new ImageIcon("currency_background.jpg").getImage(); // Provide the path to your image
        
        // Initialize components
        labelFrom = new Label("From Currency:");
        labelTo = new Label("To Currency:");
        labelAmount = new Label("Amount:");
        labelResult = new Label("Converted Amount:");
        
        choiceFrom = new Choice();
        choiceFrom.add("USD");
        choiceFrom.add("INR");
        choiceFrom.add("EUR");
        choiceFrom.add("GBP");
        choiceFrom.add("JPY");
        
        choiceTo = new Choice();
        choiceTo.add("USD");
        choiceTo.add("INR");
        choiceTo.add("EUR");
        choiceTo.add("GBP");
        choiceTo.add("JPY");
        
        textAmount = new TextField(10);
        textResult = new TextField(10);
        textResult.setEditable(false);
        
        buttonConvert = new Button("Convert");
        
        // Add components to the frame
        add(labelFrom);
        add(choiceFrom);
        add(labelTo);
        add(choiceTo);
        add(labelAmount);
        add(textAmount);
        add(labelResult);
        add(textResult);
        add(buttonConvert);
        
        // Add action listener to the button
        buttonConvert.addActionListener(this);
        
        // Window close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    
    // Override the paint() method to draw the background image
    public void paint(Graphics g) {
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
        // Call the superclass paint method to ensure components are drawn over the background
        super.paint(g);
    }

    // Action listener method
    public void actionPerformed(ActionEvent e) {
        try {
            // Get the input amount
            double amount = Double.parseDouble(textAmount.getText());
            
            // Get selected currencies
            String fromCurrency = choiceFrom.getSelectedItem();
            String toCurrency = choiceTo.getSelectedItem();
            
            double convertedAmount = 0;
            
            // Conversion logic based on selected currencies
            if (!fromCurrency.equals(toCurrency)) {
                double fromRate = exchangeRates.get(fromCurrency);
                double toRate = exchangeRates.get(toCurrency);
                
                // Conversion formula: (amount / fromCurrency rate) * toCurrency rate
                convertedAmount = (amount / fromRate) * toRate;
            } else {
                // No conversion needed if both currencies are the same
                convertedAmount = amount;
            }
            
            // Display the converted amount
            textResult.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException ex) {
            textResult.setText("Invalid input");
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        CurrencyConverter app = new CurrencyConverter();
        app.setVisible(true);
    }
}