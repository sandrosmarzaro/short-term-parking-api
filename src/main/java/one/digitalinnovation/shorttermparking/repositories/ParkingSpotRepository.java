package one.digitalinnovation.shorttermparking.repositories;

import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, String> {

}

