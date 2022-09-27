package one.digitalinnovation.shorttermparking.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import one.digitalinnovation.shorttermparking.dto.ParkingSpotRequest;
import one.digitalinnovation.shorttermparking.dto.ParkingSpotResponse;
import one.digitalinnovation.shorttermparking.mappers.ParkingSpotMapper;
import one.digitalinnovation.shorttermparking.services.ParkingSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Spot")
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
    @ApiOperation(value = "List all parking spots")
    public ResponseEntity<List<ParkingSpotResponse>> findAll() {
        List<ParkingSpotModel> parkingList = parkingSpotService.findAll();
        List<ParkingSpotResponse> parkingDTOSList = parkingSpotMapper.toDTOList(parkingList);
        return ResponseEntity.ok(parkingDTOSList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find parking spot by id")
    public ResponseEntity<ParkingSpotResponse> findById(@PathVariable String id) {
        ParkingSpotModel parkingSpotModel = parkingSpotService.findById(id);
        ParkingSpotResponse parkingSpotDTO = parkingSpotMapper.toDTO(parkingSpotModel);
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotDTO);
    }

    @PostMapping
    @ApiOperation(value = "Create a new parking spot")
    public ResponseEntity<ParkingSpotResponse> create(@RequestBody ParkingSpotRequest parkingSpotRequest) {
        ParkingSpotModel parkingSpotModel = parkingSpotMapper.toModel(parkingSpotRequest);
        ParkingSpotModel parkingSpotModelCreated = parkingSpotService.create(parkingSpotModel);
        ParkingSpotResponse parkingSpotDTOCreated = parkingSpotMapper.toDTO(parkingSpotModelCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotDTOCreated);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a parking spot")
    public ResponseEntity<ParkingSpotResponse> update(@PathVariable String id,
                                                      @RequestBody ParkingSpotRequest parkingSpotRequest) {
        ParkingSpotModel parkingSpotModel = parkingSpotMapper.toModel(parkingSpotRequest);
        ParkingSpotModel parkingSpotModelUpdated = parkingSpotService.update(id, parkingSpotModel);
        ParkingSpotResponse parkingSpotDTOUpdated = parkingSpotMapper.toDTO(parkingSpotModelUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotDTOUpdated);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a parking spot by id")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        parkingSpotService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Update a parking spot when it is exited")
    public ResponseEntity<ParkingSpotResponse> updateWhenExited(@PathVariable String id) {
        ParkingSpotModel parkingSpotModelUpdated = parkingSpotService.updateWhenExited(id);
        ParkingSpotResponse parkingSpotDTOUpdated = parkingSpotMapper.toDTO(parkingSpotModelUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotDTOUpdated);
    }
}
