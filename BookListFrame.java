/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication120;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author sraa
 */

 class LoginFrame extends JFrame {
    private JButton loginButton;
    private JButton signUpButton;

    public LoginFrame() {
        initializeComponents();
        setLayout(new FlowLayout());
        add(loginButton);
        add(signUpButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm().setVisible(true);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpForm().setVisible(true);
            }
        });
    }
}

class LoginForm extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        initializeComponents();
        setLayout(new GridLayout(3, 2));
        add(new JLabel("User ID:"));
        add(userIdField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(loginButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        userIdField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = Integer.parseInt(userIdField.getText());
                    String password = new String(passwordField.getPassword());

                    // Validate login
                    if (validateLogin(userId, password)) {
                        new BookListFrame(userId).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid login credentials", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid user ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validateLogin(int userId, String password) {
        // Perform actual login validation logic here
        // For simplicity, always return true in this example
        return true;
    }
}

class SignUpForm extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    public SignUpForm() {
        initializeComponents();
        setLayout(new GridLayout(7, 2));
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("Phone Number:"));
        add(phoneNumberField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(signUpButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        emailField = new JTextField(10);
        addressField = new JTextField(10);
        phoneNumberField = new JTextField(10);
        passwordField = new JPasswordField(10);
        signUpButton = new JButton("Sign Up");

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String password = new String(passwordField.getPassword());

                User user = new UserBuilder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .password(password)
                        .build();

                SessionManager.getInstance().getUserBookListMap().put(user.getUserId(), user.getBookList());

                JOptionPane.showMessageDialog(null, "User ID: " + user.getUserId(), "Sign Up Successful", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });
    }
}

class BookListFrame extends JFrame {
    private JButton addToCartButton;
    private JButton viewCartButton;
    private int userId;

    public BookListFrame(int userId) {
        this.userId = userId;
        initializeComponents();
        setLayout(new FlowLayout());
        add(new JLabel("Book List Page"));
        add(addToCartButton);
        add(viewCartButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddToCartForm(userId).setVisible(true);
            }
        });

        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCart();
            }
        });
    }

    private void viewCart() {
    int userId = this.userId;
    LinkedList<Book> userBookList = SessionManager.getInstance().getUserBookListMap().get(userId);

    if (userBookList != null && !userBookList.isEmpty()) {
        StringBuilder cartContents = new StringBuilder("Cart Contents:\n");

        double totalPrice = 0;

        for (Book book : userBookList) {
            cartContents.append("- ").append(book.getTitle()).append(" ($").append(book.getPrice()).append(")\n");
            totalPrice += book.getPrice();
        }

        cartContents.append("\nTotal Price: $").append(totalPrice);

        // Create a JFrame for displaying the cart contents
        JFrame cartFrame = new JFrame("View Cart");
        cartFrame.setLayout(new BorderLayout());

        // Display the cart contents and total price in a JTextArea
        JTextArea cartTextArea = new JTextArea(cartContents.toString());
        cartTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);

        // Create "OK" and "Complete Order" buttons
        JButton okButton = new JButton("OK");
        JButton completeOrderButton = new JButton("Complete Order");

        // Add action listener for the "Complete Order" button
        completeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Complete Order" action (e.g., show payment page)
                showPaymentPage();
            }
        });

        // Add components to the cartFrame
        cartFrame.add(scrollPane, BorderLayout.CENTER);
        cartFrame.add(okButton, BorderLayout.SOUTH);
        cartFrame.add(completeOrderButton, BorderLayout.SOUTH);

        cartFrame.setSize(400, 300);
        cartFrame.setLocationRelativeTo(null);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(null, "Your cart is empty", "View Cart", JOptionPane.INFORMATION_MESSAGE);
    }
}







    private void showPaymentPage() {
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.setVisible(true);
        paymentPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    }
}


class AddToCartForm extends JFrame {
    private JComboBox<String> bookComboBox;
    private JButton addToCartButton;
    private int userId;

    public AddToCartForm(int userId) {
        this.userId = userId;
        initializeComponents();
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Select a Book:"));
        add(bookComboBox);
        add(new JLabel());
        add(addToCartButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        populateBookComboBox();
    }

    private void initializeComponents() {
        bookComboBox = new JComboBox<>();
        addToCartButton = new JButton("Add to Cart");

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBook = (String) bookComboBox.getSelectedItem();
                if (selectedBook != null) {
                    addToCart(userId, selectedBook);
                }
            }
        });
    }

    private void populateBookComboBox() {
        // Populate the book combo box with dummy book data
        // In a real application, you would fetch this data from a database or another source
        bookComboBox.addItem("Book 1");
        bookComboBox.addItem("Book 2");
        bookComboBox.addItem("Book 3");
    }

    private void addToCart(int userId, String bookTitle) {
        LinkedList<Book> userBookList = SessionManager.getInstance().getUserBookListMap().get(userId);
        if (userBookList != null) {
            Book book = new Book(bookTitle, "Author", 19.99);
            userBookList.add(book);
            JOptionPane.showMessageDialog(null, "Book added to cart", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class PayPalPaymentForm extends JFrame {
    public PayPalPaymentForm() {
        setTitle("PayPal Payment Form");
        setLayout(new GridLayout(4, 2));
        setSize(300, 150);

        add(new JLabel("PayPal Email:"));
        add(new JTextField());
        add(new JLabel());
        JButton submitButton = new JButton("Submit Payment");
        add(submitButton);

        // Add action listener for the "Submit Payment" button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment("PayPal");
            }
        });
    }

    private void processPayment(String paymentMethod) {
        // Additional payment processing logic goes here

        // After processing payment, show order confirmation
        PaymentPage.showOrderConfirmation(paymentMethod);

        // Close the current payment form
        dispose();
    }
}

class VisaPaymentForm extends JFrame {
    public VisaPaymentForm() {
        setTitle("Visa Payment Form");
        setLayout(new GridLayout(5, 2));
        setSize(300, 150);

        add(new JLabel("Card Number:"));
        add(new JTextField());
        add(new JLabel("Expiration Date:"));
        add(new JTextField());
        add(new JLabel("CVV:"));
        add(new JTextField());
        add(new JLabel());
        JButton submitButton = new JButton("Submit Payment");
        add(submitButton);

        // Add action listener for the "Submit Payment" button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment("Visa");
            }
        });
    }

    private void processPayment(String paymentMethod) {
        // Additional payment processing logic goes here

        // After processing payment, show order confirmation
        PaymentPage.showOrderConfirmation(paymentMethod);

        // Close the current payment form
        dispose();
    }
}
class PaymentPage extends JFrame {
    private static final int FONT_SIZE = 16;

    public PaymentPage() {
        setTitle("Payment Page");
        setLayout(new FlowLayout());
        setSize(400, 200);

        JButton visaButton = new JButton("Pay with Visa");
        JButton paypalButton = new JButton("Pay with PayPal");

        Font buttonFont = new Font("Arial", Font.PLAIN, FONT_SIZE);
        visaButton.setFont(buttonFont);
        paypalButton.setFont(buttonFont);

        visaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showVisaPaymentForm();
            }
        });

        paypalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPayPalPaymentForm();
            }
        });

        add(visaButton);
        add(paypalButton);
    }

    private void showVisaPaymentForm() {
        VisaPaymentForm visaPaymentForm = new VisaPaymentForm();
        visaPaymentForm.setVisible(true);
    }

    private void showPayPalPaymentForm() {
        PayPalPaymentForm payPalPaymentForm = new PayPalPaymentForm();
        payPalPaymentForm.setVisible(true);
    }

    public static void showOrderConfirmation(String paymentMethod) {
        JFrame confirmationFrame = new JFrame("Order Confirmation");
        confirmationFrame.setLayout(new BorderLayout());
        confirmationFrame.setSize(400, 200);

        JTextArea confirmationText = new JTextArea("Completed Order\nPayment Method: " + paymentMethod + "\nOrder ID: " + generateOrderID());
        confirmationText.setEditable(false);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmationFrame.dispose();
            }
        });

        confirmationFrame.add(confirmationText, BorderLayout.CENTER);
        confirmationFrame.add(okButton, BorderLayout.SOUTH);

        confirmationFrame.setSize(400, 200);
        confirmationFrame.setLocationRelativeTo(null);
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmationFrame.setVisible(true);
    }

    private static int generateOrderID() {
        return new Random().nextInt(90000) + 10000;
    }
}