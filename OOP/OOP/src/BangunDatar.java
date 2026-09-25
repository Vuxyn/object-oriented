public class BangunDatar {
    private double side;
    private double length;
    private double width;

    public BangunDatar() {
        this.side = 0;
        this.length = 0;
        this.width = 0;
    }


    public BangunDatar(double side) {
        this.side = side;
    }
    public BangunDatar(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double calculateSquareArea() {
        return side * side;
    }

    public double calculateRectangleArea() {
        return length * width;
    }

    public double calculateSquarePerimeter() {
        return 4 * side;
    }

    public double calculateRectanglePerimeter() {
        return 2 * (length + width);
    }
}
