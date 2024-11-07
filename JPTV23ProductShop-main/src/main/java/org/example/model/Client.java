package org.example.model;
import java.io.Serializable;
import java.util.UUID;

public class Client implements Serializable {

    private UUID clientId;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    public Client(){
        this.clientId = UUID.randomUUID();
    }

    // Конструктор для инициализации клиента
    public Client(String firstname, String lastname, String email, String phone) {
        this.clientId = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    // Геттеры и сеттеры для полей
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }


    public String getName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Метод для обновления email клиента
    public void updateEmail(String newEmail) {
        this.email = newEmail;
        System.out.println("Email клиента " + firstname +" "+ lastname + " обновлен на " + newEmail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;
        return clientId.equals(client.clientId) && firstname.equals(client.firstname) && lastname.equals(client.lastname) && email.equals(client.email) && phone.equals(client.phone);
    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + clientId +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Object getFirstName() {
        return null;
    }
}
