public class BangunDatarDemo {

    public static void main(String args[]) {
        BangunDatar square = new BangunDatar(7);

        BangunDatar rectangle1 = new BangunDatar();
        rectangle1.setLength(10);
        rectangle1.setWidth(5);

        BangunDatar rectangle2 = new BangunDatar(8, 6);

        System.out.println("Area of square with side " + square.getSide() + " = " + square.calculateSquareArea());
        System.out.println("Perimeter of square with side " + square.getSide() + " = " + square.calculateSquarePerimeter());
        System.out.println("");

        System.out.println("Area of rectangle with length " + rectangle1.getLength() + " and width " + rectangle1.getWidth() + " = " + rectangle1.calculateRectangleArea());
        System.out.println("Perimeter of rectangle with length " + rectangle1.getLength() + " and width " + rectangle1.getWidth() + " = " + rectangle1.calculateRectanglePerimeter());
        System.out.println("");

        System.out.println("Area of rectangle with length " + rectangle2.getLength() + " and width " + rectangle2.getWidth() + " = " + rectangle2.calculateRectangleArea());
        System.out.println("Perimeter of rectangle with length " + rectangle2.getLength() + " and width " + rectangle2.getWidth() + " = " + rectangle2.calculateRectanglePerimeter());
    }
}
