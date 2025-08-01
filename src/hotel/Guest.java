package hotel;

import java.util.Objects;

public class Guest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private BedType bedPreference;
    private SmokingPreference smokingPreference;

    public Guest(String firstName, String lastName, String phone, String email,
                 BedType bedPreference, SmokingPreference smokingPreference) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.bedPreference = bedPreference;
        this.smokingPreference = smokingPreference;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public BedType getBedPreference() {
        return bedPreference;
    }

    public SmokingPreference getSmokingPreference() {
        return smokingPreference;
    }

    // ✅ NEW METHOD for compatibility with Hotel.java
    public RoomPreferences getPreferences() {
        return new RoomPreferences(bedPreference, smokingPreference);
    }

    @Override
    public String toString() {
        return getFullName() + " (" + phone + ", " + email + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return Objects.equals(firstName, guest.firstName)
                && Objects.equals(lastName, guest.lastName)
                && Objects.equals(phone, guest.phone)
                && Objects.equals(email, guest.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, email);
    }
}
