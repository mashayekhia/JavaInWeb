package ir.man.JavaInWeb.model;

import ir.man.JavaInWeb.model.builder.ProductCategoryBuilder;

public class ProductCategory extends SharedMembers {

    private Integer id;
    private String name;
    private String description;

    public ProductCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static ProductCategoryBuilder newBuilder() {
        return new ProductCategoryBuilder();
    }

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }
}
