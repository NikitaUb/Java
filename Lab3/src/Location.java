import java.util.Objects;

public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Location location)) return false;

        return (yCoord == location.yCoord) && (xCoord == location.xCoord);
    }

    @Override
    public int hashCode() {
        int result = yCoord;
        result = 31 * result + xCoord;
        return result;
    }
}