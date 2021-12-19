package ir.man.JavaInWeb.model.builder;

import ir.man.JavaInWeb.model.ProductCategory;

public class ProductCategoryBuilder {
    private Integer id;
    private String name;
    private String description;

    public ProductCategoryBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProductCategoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductCategoryBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductCategory createProductCategory() {
        return new ProductCategory(id, name, description);
    }
}
