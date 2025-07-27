package hotel;

import java.util.*;

public class Hotel {
    private Map<Integer, Room> roomMap;
    private List<Guest> guestList;

    public Hotel() {
        roomMap = new HashMap<>();
        guestList = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        for (int i = 1; i <= 30; i++) {
            BedType bedType = (i % 3 == 0) ? BedType.TWO_QUEENS : BedType.KING;
            SmokingPreference smokingPref = (i % 5 == 0) ? SmokingPreference.SMOKING : SmokingPreference.NON_SMOKING;
            roomMap.put(i, new Room(i, bedType, smokingPref));
        }
    }

    public Room findRoomByNumber(int roomNumber) {
        return roomMap.get(roomNumber);
    }

    public List<Room> findAvailableRooms(RoomPreferences preferences) {
        List<Room> matches = new ArrayList<>();
        for (Room room : roomMap.values()) {
            if (room.isAvailable() &&
                room.getBedType() == preferences.getBedType() &&
                room.getSmokingPreference() == preferences.getSmokingPreference()) {
                matches.add(room);
            }
        }
        return matches;
    }


    public boolean assignRoom(Guest guest) {
        List<Room> matches = findAvailableRooms(guest.getPreferences());
        if (!matches.isEmpty()) {
            Room assigned = matches.get(0);
            assigned.setGuest(guest);
            assigned.setStatus(RoomStatus.OCCUPIED);
            guestList.add(guest);
            return true;
        }
        return false;
    }


    public boolean assignRoom(int roomNumber, Guest guest) {
        Room room = roomMap.get(roomNumber);
        if (room != null && room.isAvailable()) {
            room.setGuest(guest);
            room.setStatus(RoomStatus.OCCUPIED);
            guestList.add(guest);
            return true;
        }
        return false;
    }

    public Guest findGuestByName(String firstName, String lastName) {
        String fullName = (firstName + " " + lastName).trim().toLowerCase();
        for (Guest guest : guestList) {
            if (guest.getFullName().toLowerCase().equals(fullName)) {
                return guest;
            }
        }
        return null;
    }


    public Collection<Room> getAllRooms() {
        return roomMap.values();
    }
}
