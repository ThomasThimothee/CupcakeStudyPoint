package business;

public class Cupcake {
    
    private Topping topping;
    private Bottom bottom;
    private double price;
    
    public Cupcake(Topping topping, Bottom bottom) {
        this.topping = topping;
        this.bottom = bottom;
        this.price = topping.getPrice() + bottom.getPrice();
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }

    public double getPrice() {
        return price;
    }
    
    
    
}
