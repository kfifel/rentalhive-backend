package com.rentalhive.dto;

import com.rentalhive.domain.Location;
import com.rentalhive.dto.request.EquipmentRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    @Valid
    @NotNull(message = "equipment cannot be null")
    List<EquipmentRequestDTO> equipments;
    @DateTimeFormat
    @NotNull(message = "startDate cannot be null")
    LocalDateTime startDate;
    @DateTimeFormat
    @NotNull(message = "endDate cannot be null")
    LocalDateTime endDate;
    @Nullable
    Location location;
}
