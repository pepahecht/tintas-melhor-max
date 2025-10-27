package com.tintas.TintasShop.model;

import jakarta.persistence.*;

@Entity
public class Tinta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String type;
    private String finish;
    private String colorCode;
    private String colorPicker;
    private String description;
    private Double price;
    private Integer stock;
    private String productImage;
    private String sku;
    private String size;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFinish() { return finish; }
    public void setFinish(String finish) { this.finish = finish; }

    public String getColorCode() { return colorCode; }
    public void setColorCode(String colorCode) { this.colorCode = colorCode; }

    public String getColorPicker() { return colorPicker; }
    public void setColorPicker(String colorPicker) { this.colorPicker = colorPicker; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
}
