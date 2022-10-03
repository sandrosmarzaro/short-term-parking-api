package one.digitalinnovation.shorttermparking.services;

import one.digitalinnovation.shorttermparking.exceptions.ParkingSpotNotFoundException;
import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import one.digitalinnovation.shorttermparking.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ParkingSpotModel findById(String id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(() -> new ParkingSpotNotFoundException(id));
    }

    @Transactional
    public ParkingSpotModel create(ParkingSpotModel parkingSpotModel) {
        String uuid = getUUID();
        parkingSpotModel.setId(uuid);
        parkingSpotModel.setEntryDate(OffsetDateTime.now());
        return parkingSpotRepository.save(parkingSpotModel);
    }

    @Transactional
    public ParkingSpotModel update(String id, ParkingSpotModel parkingSpotModel) {
        ParkingSpotModel parkingSpotModelToUpdate = findById(id);
        parkingSpotModelToUpdate.setLicense(parkingSpotModel.getLicense());
        parkingSpotModelToUpdate.setState(parkingSpotModel.getState());
        return parkingSpotRepository.save(parkingSpotModelToUpdate);
    }

    @Transactional
    public void delete(String id) {
        ParkingSpotModel parkingSpotModelToDelete = findById(id);
        parkingSpotRepository.delete(parkingSpotModelToDelete);
    }

    @Transactional
    public ParkingSpotModel updateWhenExited(String id) {
        ParkingSpotModel parkingSpotModelToUpdate = findById(id);
        parkingSpotModelToUpdate.setExitDate(OffsetDateTime.now());
        parkingSpotModelToUpdate.setBill(ParkingSpotCheckOut.checkOut(parkingSpotModelToUpdate));
        return parkingSpotRepository.save(parkingSpotModelToUpdate);
    }
}
