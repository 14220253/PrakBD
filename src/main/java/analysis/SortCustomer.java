package analysis;

import javafx.beans.property.SimpleStringProperty;

public class SortCustomer {
    SimpleStringProperty name;
    SimpleStringProperty count;

    public SortCustomer(String name, String count) {
        this.name = new SimpleStringProperty(name);
        this.count = new SimpleStringProperty(count);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCount() {
        return count.get();
    }

    public SimpleStringProperty countProperty() {
        return count;
    }

    public void setCount(String count) {
        this.count.set(count);
    }
}
