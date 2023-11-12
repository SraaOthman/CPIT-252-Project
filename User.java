
package javaapplication120;

import java.util.LinkedList;
import java.util.Random;

class User {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private int userId;
    private LinkedList<Book> bookList;

    User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.password = builder.password;
        this.userId = generateRandomId();
        this.bookList = new LinkedList<>();
    }

    private int generateRandomId() {
        return new Random().nextInt(90000) + 10000; // Five-digit random number
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public LinkedList<Book> getBookList() {
        return bookList;
    }
}
