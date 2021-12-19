package ir.man.JavaInWeb.model.builder;

import ir.man.JavaInWeb.model.Product;
import ir.man.JavaInWeb.model.ProductCategory;

public class ProductBuilder {
    private Integer id;
    private String name;
    private ProductCategory category;

    public ProductBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setCategory(ProductCategory category) {
        this.category = category;
        return this;
    }

    public Product createProduct() {
        return new Product(id, name, category);
    }
}
