package one.digitalinnovation.shorttermparking.controllers;

import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import one.digitalinnovation.shorttermparking.models.dto.ParkingSpotDTO;
import one.digitalinnovation.shorttermparking.models.mappers.ParkingSpotMapper;
import one.digitalinnovation.shorttermparking.services.ParkingSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;
    private final ParkingSpotMapper parkingSpotMapper;

    public ParkingSpotController(
            ParkingSpotService parkingSpotService,
            ParkingSpotMapper parkingSpotMapper
    ) {
        this.parkingSpotService = parkingSpotService;
        this.parkingSpotMapper = parkingSpotMapper;
    }


    @GetMapping
    public ResponseEntity<List<ParkingSpotDTO>> findAll() {
        List<ParkingSpotModel> parkingList = parkingSpotService.findAll();
        List<ParkingSpotDTO> parkingDTOSList = parkingSpotMapper.toDTOList(parkingList);
        return ResponseEntity.ok(parkingDTOSList);
    }
}
