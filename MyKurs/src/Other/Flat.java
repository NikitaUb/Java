package Other;

public class Flat {
    private String district;
    private String buildingCategory;
    private int buildingFloors;
    private int floor;
    private double totalArea;
    private double livingArea;
    private int rooms;
    private String address;
    private double price;


    public Flat(String district, String buildingCategory, int buildingFloors, int floor, double totalArea, double livingArea, int rooms, String address , double price) {
        this.district = district;
        this.buildingCategory = buildingCategory;
        this.buildingFloors = buildingFloors;
        this.floor = floor;
        this.totalArea = totalArea;
        this.livingArea = livingArea;
        this.rooms = rooms;
        this.address = address;
        this.price = price;
    }

    public String getDistrict() {
        return district;
    }

    public String getBuildingCategory() {
        return buildingCategory;
    }

    public int getBuildingFloors() {
        return buildingFloors;
    }

    public int getFloor() {
        return floor;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getLivingArea() {
        return livingArea;
    }

    public int getRooms() {
        return rooms;
    }

    public String getAddress() {
        return address;
    }

    public double getTotalPrice(){
        return price;
    }

}
