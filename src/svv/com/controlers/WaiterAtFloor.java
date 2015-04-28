package svv.com.controlers;

public class WaiterAtFloor {
    private int floor;

    private Orientation orientation;

    public WaiterAtFloor(int floor, Orientation orientation) {
        this.floor = floor;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaiterAtFloor that = (WaiterAtFloor) o;

        if (floor != that.floor) return false;
        return orientation == that.orientation;
    }

    @Override
    public int hashCode() {
        int result = floor;
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        return result;
    }
}
