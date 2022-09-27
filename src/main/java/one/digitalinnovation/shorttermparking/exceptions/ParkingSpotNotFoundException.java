package one.digitalinnovation.shorttermparking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParkingSpotNotFoundException extends RuntimeException {
    public ParkingSpotNotFoundException(String id) {
        super("Parking Spot not found with id: " + id);
    }
}
