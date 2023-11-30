package com.rentalhive.web.rest;


import com.rentalhive.domain.Contract;
import com.rentalhive.dto.request.ContractRequestDto;
import com.rentalhive.dto.response.ContractResponseDto;
import com.rentalhive.mapper.ContractDtoMapper;
import com.rentalhive.service.ContractService;
import com.rentalhive.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractRest {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<Response<ContractResponseDto>> save(@Valid @RequestBody ContractRequestDto contractDto) {
        Contract contract = ContractDtoMapper.mapToContract(contractDto);
        Contract saved = contractService.save(contract);
        ContractDtoMapper.mapToContractResponseDto(saved);
        return ResponseEntity.ok(Response.<ContractResponseDto>builder()
                    .message("Contract saved")
                    .result(ContractDtoMapper.mapToContractResponseDto(saved))
                .build());
    }
}
