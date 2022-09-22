package one.digitalinnovation.shorttermparking.models.mappers;

import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;
import one.digitalinnovation.shorttermparking.models.dto.ParkingSpotDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingSpotMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingSpotDTO toDTO(ParkingSpotModel parkingSpotModel) {
        return MODEL_MAPPER.map(parkingSpotModel, ParkingSpotDTO.class);
    }

    public List<ParkingSpotDTO> toDTOList(List<ParkingSpotModel> parkingList) {
        return parkingList.stream()
                .map(this::toDTO)
                .toList();
    }
}
