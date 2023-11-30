package com.rentalhive.web.rest;

import com.rentalhive.domain.Equipment;
import com.rentalhive.dto.EquipmentDto;
import com.rentalhive.dto.response.EquipmentResponseDTO;
import com.rentalhive.exception.OrderDateException;
import com.rentalhive.mapper.EquipmentDtoMapper;
import com.rentalhive.service.EquipmentService;
import com.rentalhive.service.impl.EquipmentItemServiceImpl;
import com.rentalhive.utils.Response;
import com.rentalhive.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentRest {

    private final EquipmentService equipmentService;
    private final EquipmentItemServiceImpl equipmentItemService;

    @GetMapping
    public ResponseEntity<Response<List<EquipmentDto>>> getAllEquipments(){
        Response<List<EquipmentDto>> response = new Response<>();
        List<EquipmentDto> equipmentList = new ArrayList<>();
        equipmentService.findAll().stream().map(EquipmentDtoMapper::toDto).forEach(equipmentList::add);
        response.setResult(equipmentList);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<EquipmentDto>> addEquipment(@Valid @RequestBody EquipmentDto equipmentDto){
        Response<EquipmentDto> response = new Response<>();
        Equipment equipment = EquipmentDtoMapper.toEquipment(equipmentDto);
        try {
            response.setResult(EquipmentDtoMapper.toDto(equipmentService.save(equipment)));
            response.setMessage("Equipment has been added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ValidationException e) {
            response.setMessage("Equipment has not been added");
            response.setErrors(List.of(e.getCustomError()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<EquipmentDto>> updateEquipment(@Valid @RequestBody EquipmentDto equipmentDto, @PathVariable Long id){
        Response<EquipmentDto> response = new Response<>();
        Equipment equipment = EquipmentDtoMapper.toEquipment(equipmentDto);
        equipment.setId(id);
        try {
            response.setResult(EquipmentDtoMapper.toDto(equipmentService.update(equipment)));
            response.setMessage("Equipment has been updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ValidationException ex){
            response.setMessage("Equipment has not been updated");
            response.setErrors(List.of(ex.getCustomError()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.delete(id);
            String message = "Equipment a été supprimé avec success";
            return ResponseEntity.ok(message);
        }catch (Exception e) {
            String errorMessage = "Erreur lors de la suppression !";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAllEquipment() {
        try {
            equipmentService.deleteAll();
            String message = "Equipments sont supprimés avec success";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String errorMessage = "Erreur lors de la suppression !";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<Response<List<EquipmentResponseDTO>>> getEquipmentAvailable(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) throws OrderDateException {
        Response<List<EquipmentResponseDTO>> response = new Response<>();
        response.setMessage("Retrieve equipment is successful");
        response.setResult(equipmentItemService.findAvailableEquipments(start, end));
        return ResponseEntity.ok().body(response);
    }
}
