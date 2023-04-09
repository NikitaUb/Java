import java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Point3d[] myPoint = new Point3d[3];
        for (int i = 0; i <= 2; i++) {
            myPoint[i] = new Point3d();
            myPoint[i].setX(scanner.nextDouble());
            myPoint[i].setY(scanner.nextDouble());
            myPoint[i].setZ(scanner.nextDouble());
            System.out.println(("(" + myPoint[i].getX() + " " + myPoint[i].getY() + " " + myPoint[i].getZ() + ")"));
        }
        if (Point3d.Check(myPoint[0], myPoint[1], myPoint[2])) {
            System.out.println("Площать: " + computeArea(myPoint[0], myPoint[1], myPoint[2]));
        } else {
            System.out.println("Точки находяться в одном месте");
        }
    }

    public static double computeArea(Point3d t1, Point3d t2, Point3d t3) {

        double a = t1.distanceTo(t2);

        double b = t2.distanceTo(t3);

        double c = t3.distanceTo(t1);

        double LowPer = ((a + b + c) / 2);

        return (Math.sqrt(LowPer * (LowPer - a) * (LowPer - b) * (LowPer - c)));

    }
}