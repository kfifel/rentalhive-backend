package com.rentalhive.service.impl;

import com.rentalhive.domain.Contract;
import com.rentalhive.domain.Reservation;
import com.rentalhive.repository.ContractRepository;
import com.rentalhive.service.ContractService;
import com.rentalhive.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl  implements ContractService {

    private final ContractRepository contractRepository;
    private final ReservationService reservationService;

    @Override
    public Contract save(Contract contract) {
        Optional<Reservation> reservation = reservationService.findById(contract.getReservation().getId());
        if (reservation.isPresent()) {
            contract.setReservation(reservation.get());
            return contractRepository.save(contract);
        }
        throw new ValidationException("Reservation not found");
    }
}
