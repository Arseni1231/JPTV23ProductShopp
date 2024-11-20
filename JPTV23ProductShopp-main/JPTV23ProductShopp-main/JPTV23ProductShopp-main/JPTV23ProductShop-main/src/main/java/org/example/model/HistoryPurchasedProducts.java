package org.example.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class HistoryPurchasedProducts implements Serializable {
    private UUID clientId;
    private Product product;
    private Client client;
    private LocalDate borrowedProductDate;
    private LocalDate purchasedProductDate;

    public HistoryPurchasedProducts() {
        this.clientId = UUID.randomUUID();
    }

    public HistoryPurchasedProducts(Product product, Client client, LocalDate borrowedProductDate, LocalDate purchasedProductDate) {
        this.clientId = UUID.randomUUID();
        this.product = product;
        this.client = client;
        this.borrowedProductDate = borrowedProductDate;
        this.purchasedProductDate = purchasedProductDate;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setId(UUID clientId){
        this.clientId = clientId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getBorrowedProductDate() {
        return borrowedProductDate;
    }

    public void setBorrowedProductDate(LocalDate borrowedProductDate) {
        this.borrowedProductDate = borrowedProductDate;
    }

    public LocalDate getPurchasedProductDate() {
        return purchasedProductDate;
    }

    public void setPurchasedProductDate(LocalDate purchasedProductDate) {
        this.purchasedProductDate = purchasedProductDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryPurchasedProducts that = (HistoryPurchasedProducts) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(product, that.product) && Objects.equals(client, that.client) && Objects.equals(borrowedProductDate, that.borrowedProductDate) && Objects.equals(purchasedProductDate, that.purchasedProductDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(clientId);
        result = 31 * result + Objects.hashCode(product);
        result = 31 * result + Objects.hashCode(client);
        result = 31 * result + Objects.hashCode(borrowedProductDate);
        result = 31 * result + Objects.hashCode(purchasedProductDate);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("History of purchased products{");
        stringBuilder.append("client ID=").append(clientId);
        stringBuilder.append(", product=").append(product);
        stringBuilder.append(", client=").append(client);
        stringBuilder.append(", borrowed book date=").append(borrowedProductDate);
        stringBuilder.append(", return book date=").append(purchasedProductDate);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

}
