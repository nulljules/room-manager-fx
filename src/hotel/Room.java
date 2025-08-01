package hotel;

public class Room {
    private int roomNumber;
    private BedType bedType;
    private SmokingPreference smokingPreference;
    private RoomStatus status;
    private Guest guest;

    public Room(int roomNumber, BedType bedType, SmokingPreference smokingPreference) {
        this.roomNumber = roomNumber;
        this.bedType = bedType;
        this.smokingPreference = smokingPreference;
        this.status = RoomStatus.VACANT;
    }

    public boolean isAvailable() {
        return status == RoomStatus.VACANT;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public BedType getBedType() {
        return bedType;
    }

    public SmokingPreference getSmokingPreference() {
        return smokingPreference;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public Guest getGuest() {
        return guest;
    }

    public void checkIn(Guest guest) {
        this.guest = guest;
        this.status = RoomStatus.OCCUPIED;
    }

    public void checkOut() {
        this.guest = null;
        this.status = RoomStatus.VACANT;
    }

    // ✅ NEW METHODS REQUIRED BY Hotel.java
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + bedType + ", " + smokingPreference + ", " + status + "]";
    }
}
