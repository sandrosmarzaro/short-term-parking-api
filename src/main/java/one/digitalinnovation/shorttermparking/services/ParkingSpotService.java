package one.digitalinnovation.shorttermparking.services;

import one.digitalinnovation.shorttermparking.exceptions.ParkingSpotNotFoundException;
import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import one.digitalinnovation.shorttermparking.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpotModel findById(String id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(() -> new ParkingSpotNotFoundException(id));
    }

    public ParkingSpotModel create(ParkingSpotModel parkingSpotModel) {
        String uuid = getUUID();
        parkingSpotModel.setId(uuid);
        parkingSpotModel.setEntryDate(OffsetDateTime.now());
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public ParkingSpotModel update(String id, ParkingSpotModel parkingSpotModel) {
        ParkingSpotModel parkingSpotModelToUpdate = findById(id);
        parkingSpotModelToUpdate.setLicense(parkingSpotModel.getLicense());
        parkingSpotModelToUpdate.setState(parkingSpotModel.getState());
        return parkingSpotRepository.save(parkingSpotModelToUpdate);
    }

    public void delete(String id) {
        ParkingSpotModel parkingSpotModelToDelete = findById(id);
        parkingSpotRepository.delete(parkingSpotModelToDelete);
    }

    public ParkingSpotModel updateWhenExited(String id) {
        ParkingSpotModel parkingSpotModelToUpdate = findById(id);
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
        return parkingSpotRepository.save(parkingSpotModelToUpdate);
    }
}
