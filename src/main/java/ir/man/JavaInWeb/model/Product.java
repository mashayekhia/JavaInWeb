package ir.man.JavaInWeb.model;

import ir.man.JavaInWeb.model.builder.ProductBuilder;

public class Product {
    private Integer id;
    private String name;
    private ProductCategory category;

    public Product() {
    }

    public Product(Integer id, String name, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public static ProductBuilder newBuilder() {
        return new ProductBuilder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
