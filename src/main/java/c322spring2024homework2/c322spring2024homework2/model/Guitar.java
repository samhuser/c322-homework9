package c322spring2024homework2.c322spring2024homework2.model;

import java.util.Objects;

public class Guitar {
    String serialNumber;
    double price;
    Builder builder;
    String model;
    Type type;
    Wood backwood;
    Wood topwood;

    public Guitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backwood, Wood topwood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backwood = backwood;
        this.topwood = topwood;
    }

    public String getSerialNumber(){
        return serialNumber;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Builder getBuilder(){
        return builder;
    }
    public String getModel() {
        return model;
    }
    public Type getType() {
        return type;
    }
    public Wood getBackwood() {
        return backwood;
    }
    public Wood getTopwood() {
        return topwood;
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "serialNumber='" + serialNumber + '\'' +
                ", price=" + price +
                ", builder='" + builder + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", backwood='" + backwood + '\'' +
                ", topwood='" + topwood + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Guitar otherGuitar = (Guitar) obj;
        return Objects.equals(builder, otherGuitar.builder)
                && Objects.equals(model, otherGuitar.model)
                && Objects.equals(type, otherGuitar.type)
                && Objects.equals(backwood, otherGuitar.backwood)
                && Objects.equals(topwood, otherGuitar.topwood);
    }
}
