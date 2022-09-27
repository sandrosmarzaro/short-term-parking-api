package one.digitalinnovation.shorttermparking.services;

import one.digitalinnovation.shorttermparking.exceptions.ParkingSpotNotFoundException;
import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ParkingSpotService {
    private static Map<String, ParkingSpotModel> parkingSpots = new HashMap<>();

    static {
        ParkingSpotModel parking1 = new ParkingSpotModel(
                getUUID(),
                "ABC-1234",
                "SP",
                "Toyota",
                "Corolla",
                "Black"
        );

        ParkingSpotModel parking2 = new ParkingSpotModel(
                getUUID(),
                "DEF-5678",
                "RJ",
                "Honda",
                "Civic",
                "White"
        );
        parkingSpots.put(parking1.getId(), parking1);
        parkingSpots.put(parking2.getId(), parking2);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<ParkingSpotModel> findAll() {
        return List.copyOf(parkingSpots.values());
    }

    public ParkingSpotModel findById(String id) {
        ParkingSpotModel parkingSpotModel = parkingSpots.get(id);
        if (parkingSpotModel == null) {
            throw new ParkingSpotNotFoundException("Parking Spot not found");
        }
        return parkingSpotModel;
    }

    public ParkingSpotModel create(ParkingSpotModel parkingSpotModel) {
        parkingSpotModel.setId(getUUID());
        parkingSpotModel.setEntryDate(OffsetDateTime.now());
        parkingSpots.put(parkingSpotModel.getId(), parkingSpotModel);
        return parkingSpotModel;
    }

    public ParkingSpotModel update(String id, ParkingSpotModel parkingSpotModel) {
        ParkingSpotModel parkingSpotModelToUpdate = this.findById(id);
        parkingSpotModelToUpdate.setLicense(parkingSpotModel.getLicense());
        parkingSpotModelToUpdate.setState(parkingSpotModel.getState());
        parkingSpotModelToUpdate.setBrand(parkingSpotModel.getBrand());
        parkingSpotModelToUpdate.setModel(parkingSpotModel.getModel());
        parkingSpotModelToUpdate.setColor(parkingSpotModel.getColor());
        parkingSpots.replace(id, parkingSpotModelToUpdate);
        System.out.println(parkingSpotModelToUpdate);
        return parkingSpotModelToUpdate;
    }

    public void delete(String id) {
        ParkingSpotModel parkingSpotModel = this.findById(id);
        if (parkingSpotModel == null) {
            throw new ParkingSpotNotFoundException("Parking Spot not found");
        }
        parkingSpots.remove(id);
    }

    public ParkingSpotModel updateWhenExited(String id) {
        ParkingSpotModel parkingSpotModelToUpdate = this.findById(id);
        parkingSpotModelToUpdate.setExitDate(OffsetDateTime.now());
        final double RATE = 3.5;
        final int ENTRY_HOUR = parkingSpotModelToUpdate.getEntryDate().getHour();
        final int EXIT_HOUR = parkingSpotModelToUpdate.getExitDate().getHour();
        final int ENTRY_MINUTE = parkingSpotModelToUpdate.getEntryDate().getMinute();
        final int EXIT_MINUTE = parkingSpotModelToUpdate.getExitDate().getMinute();
        final int HOUR_SPENT = EXIT_HOUR - ENTRY_HOUR;
        final double MINUTE_SPENT = (EXIT_MINUTE - ENTRY_MINUTE) / 60.0;
        final double TIME_SPENT = HOUR_SPENT + MINUTE_SPENT;
        parkingSpotModelToUpdate.setBill(TIME_SPENT * RATE);
        parkingSpots.replace(id, parkingSpotModelToUpdate);
        return parkingSpotModelToUpdate;
    }
}
