package one.digitalinnovation.shorttermparking.services;

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
        return UUID.randomUUID().toString();
    }

    public List<ParkingSpotModel> findAll() {
        return List.copyOf(parkingSpots.values());
    }

    public ParkingSpotModel findById(String id) {
        return parkingSpots.get(id);
    }

    public ParkingSpotModel create(ParkingSpotModel parkingSpotModel) {
        parkingSpotModel.setId(getUUID());
        parkingSpotModel.setEntryDate(OffsetDateTime.now());
        parkingSpots.put(parkingSpotModel.getId(), parkingSpotModel);
        return parkingSpotModel;
    }
}
