package entity;

import javafx.beans.property.SimpleStringProperty;

public class DiscountDetail {
    private SimpleStringProperty discountId;
    private SimpleStringProperty categoryId;

    public DiscountDetail(String discountId, String categoryId) {
        this.discountId = new SimpleStringProperty(discountId);
        this.categoryId = new SimpleStringProperty(categoryId);
    }

    public String getDiscountId() {
        return discountId.get();
    }

    public SimpleStringProperty discountIdProperty() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId.set(discountId);
    }

    public String getCategoryId() {
        return categoryId.get();
    }

    public SimpleStringProperty categoryIdProperty() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId.set(categoryId);
    }
}
