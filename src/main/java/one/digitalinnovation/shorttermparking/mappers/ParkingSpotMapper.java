package one.digitalinnovation.shorttermparking.mappers;

import one.digitalinnovation.shorttermparking.dto.ParkingSpotRequest;
import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import one.digitalinnovation.shorttermparking.dto.ParkingSpotResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingSpotMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingSpotResponse toDTO(ParkingSpotModel parkingSpotModel) {
        return MODEL_MAPPER.map(parkingSpotModel, ParkingSpotResponse.class);
    }

    public ParkingSpotModel toModel(ParkingSpotRequest parkingSpotRequest) {
        return MODEL_MAPPER.map(parkingSpotRequest, ParkingSpotModel.class);
    }

    public List<ParkingSpotResponse> toDTOList(List<ParkingSpotModel> parkingList) {
        return parkingList.stream()
                .map(this::toDTO)
                .toList();
    }
}
