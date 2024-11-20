package org.example.model;

public class Product {
    private String name;
    private String category;
    private double pricePerUnit;
    private int quantity;
    private String unit;

    // Конструктор
    public Product(String name, String category, double pricePerUnit, int quantity, String unit) {
        this.name = name;
        this.category = category;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getUnit() {
        return unit;
    }


    @Override
    public String toString() {
        String unitInfo = (unit != null) ? " за "  + unit : "";
        return "Product" +
                 name + '\'' +
                 category + '\'' +
                 pricePerUnit +
                 unitInfo + '\'' +
                 quantity
                ;
    }
}




