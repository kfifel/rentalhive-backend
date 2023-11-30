package com.rentalhive.mapper;

import com.rentalhive.domain.Contract;
import com.rentalhive.domain.Reservation;
import com.rentalhive.dto.request.ContractRequestDto;
import com.rentalhive.dto.response.ContractResponseDto;

public class ContractDtoMapper {

    public static Contract mapToContract(ContractRequestDto contractRequestDto) {
        return Contract.builder()
                .conditions(contractRequestDto.getConditions())
                .reservation(Reservation.builder().id(contractRequestDto.getReservationId()).build())
                .build();
    }

    public static ContractResponseDto mapToContractResponseDto(Contract contract) {
        return ContractResponseDto.builder()
                .id(contract.getId())
                .conditions(contract.getConditions())
                .reservation(ReservationDtoMapper.toDto(contract.getReservation()))
                .build();
    }

}
