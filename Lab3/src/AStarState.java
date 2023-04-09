import java.util.*;

public class AStarState {
    /**
     * This is a reference to the map that the A* algorithm is navigating.
     **/
    private final Map2D map;

    HashMap<Location, Waypoint> open_top = new HashMap();
    HashMap<Location, Waypoint> close_top = new HashMap();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map) {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /**
     * Returns the map that the A* pathfinder is navigating.
     **/
    public Map2D getMap() {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() {
        //Проверка размера
        if (numOpenWaypoints() == 0) return null;

        //колекция ключей
        Set<Location> open_top_keys = open_top.keySet();
        //итератор
        Iterator<Location> iterator = open_top_keys.iterator();
        //лучшая вершина
        Waypoint best_top = null;
        float best_cost = Float.MAX_VALUE;

        while (iterator.hasNext()) {
            // сохраняет текущее местоположение
            Location location = (Location) iterator.next();
            // сохраняет текущий waypoint.
            Waypoint waypoint = open_top.get(location);
            // сохраняет общую стоимость текущего waypoint.
            float waypoint_total_cost = waypoint.getTotalCost();

            // проверяем длину
            if (waypoint_total_cost < best_cost) {
                best_top = open_top.get(location);
                best_cost = waypoint_total_cost;
            }
        }

        return best_top;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP) {
        Location location = newWP.getLocation();

        // проверка на наличие открытых точек
        if (open_top.containsKey(location)) {

            Waypoint current_waypoint = open_top.get(location);
            //проверка на стоимость пути
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost()) {
                open_top.put(location, newWP);
                return true;
            }

            return false;
        }
        //если нету точек
        open_top.put(location, newWP);
        return true;
    }


    /**
     * Returns the current number of open waypoints.
     **/
    public int numOpenWaypoints() {
        return open_top.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc) {
        Waypoint waypoint = open_top.remove(loc);
        close_top.put(loc, waypoint);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc) {
        return close_top.containsKey(loc);
    }
}
