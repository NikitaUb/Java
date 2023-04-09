public class Point3d extends Point2d {

    private double zCoord;

    public Point3d(double x, double y, double z) {
        super(x,y);
        zCoord = z;
    }

    public Point3d() {
        super(0,0);
        zCoord = 0;
    }

    public double getZ() {
        return zCoord;
    }

    public void setZ(double val) {
        zCoord = val;
    }


    public static boolean Check(Point3d t1, Point3d t2, Point3d t3) {
        return ((t1.getX() != t2.getX()) || (t1.getY() != t2.getY()) || (t1.getZ() != t2.getZ())) &&
                ((t2.getX() != t3.getX()) || (t2.getY() != t3.getY()) || (t2.getZ() != t3.getZ())) &&
                ((t1.getX() != t3.getX()) || (t1.getY() != t3.getY()) || (t1.getZ() != t3.getZ()));
    }

    public double distanceTo(Point3d point) {

        double X = this.getX() - point.getX();

        double Y = this.getY() - point.getY();

        double Z = this.zCoord - point.zCoord;

        return (Math.sqrt(X * X + Y * Y + Z * Z));

    }
}
