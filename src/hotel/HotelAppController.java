package hotel;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HotelAppController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    @FXML private ComboBox<BedType> bedTypeComboBox;
    @FXML private ComboBox<SmokingPreference> smokingPreferenceComboBox;

    @FXML private ListView<Room> roomListView;
    @FXML private Button assignButton;
    @FXML private Label statusLabel;

    private Hotel hotel;
    private ObservableList<Room> availableRooms;

    @FXML
    public void initialize() {
        hotel = new Hotel();

        bedTypeComboBox.setItems(FXCollections.observableArrayList(BedType.values()));
        smokingPreferenceComboBox.setItems(FXCollections.observableArrayList(SmokingPreference.values()));

        availableRooms = FXCollections.observableArrayList();
        roomListView.setItems(availableRooms);

        roomListView.setVisible(false);
        roomListView.setManaged(false);
        assignButton.setDisable(true);

        // Enable assign button only when a room is selected and inputs are valid
        roomListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            assignButton.setDisable(newVal == null || !inputsAreValid());
        });
    }

    private boolean inputsAreValid() {
        return !firstNameField.getText().isEmpty()
                && !lastNameField.getText().isEmpty()
                && !phoneField.getText().isEmpty()
                && !emailField.getText().isEmpty()
                && bedTypeComboBox.getValue() != null
                && smokingPreferenceComboBox.getValue() != null;
    }

    @FXML
    public void handleSearchRooms() {
        if (!inputsAreValid()) {
            statusLabel.setText("Please fill in all fields.");
            roomListView.setVisible(false);
            roomListView.setManaged(false);
            assignButton.setDisable(true);
            return;
        }

        BedType bedType = bedTypeComboBox.getValue();
        SmokingPreference smokingPref = smokingPreferenceComboBox.getValue();

        RoomPreferences preferences = new RoomPreferences(bedType, smokingPref);
        availableRooms.setAll(hotel.findAvailableRooms(preferences));

        roomListView.setVisible(true);
        roomListView.setManaged(true);

        if (availableRooms.isEmpty()) {
            statusLabel.setText("No available rooms match your preferences.");
            assignButton.setDisable(true);
        } else {
            statusLabel.setText("Select a room to assign.");
        }
    }

    @FXML
    public void handleAssignRoom() {
        Room selectedRoom = roomListView.getSelectionModel().getSelectedItem();

        if (selectedRoom == null || !inputsAreValid()) {
            statusLabel.setText("Please select a room.");
            return;
        }

        String first = firstNameField.getText();
        String last = lastNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        BedType bedType = bedTypeComboBox.getValue();
        SmokingPreference smokingPref = smokingPreferenceComboBox.getValue();

        Guest guest = new Guest(first, last, phone, email, bedType, smokingPref);
        selectedRoom.checkIn(guest);

        roomListView.refresh();
        statusLabel.setText("Room assigned to " + guest.getFullName());

        assignButton.setDisable(true);
    }
}
