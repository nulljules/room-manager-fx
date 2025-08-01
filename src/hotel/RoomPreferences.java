package hotel;

public class RoomPreferences {
    private BedType bedType;
    private SmokingPreference smokingPreference;

    public RoomPreferences(BedType bedType, SmokingPreference smokingPreference) {
        this.bedType = bedType;
        this.smokingPreference = smokingPreference;
    }

    public BedType getBedType() {
        return bedType;
    }

    public SmokingPreference getSmokingPreference() {
        return smokingPreference;
    }
}
