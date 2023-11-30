package com.rentalhive.web.rest;

import com.rentalhive.domain.EquipmentFamily;
import com.rentalhive.dto.FamilyDto;
import com.rentalhive.mapper.FamilyDtoMapper;
import com.rentalhive.service.FamilyService;
import com.rentalhive.utils.Response;
import com.rentalhive.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/equipment-family")
public class FamilyRest {
    private final FamilyService familyService;

    @Autowired
    public FamilyRest(FamilyService familyService) {
        this.familyService = familyService;
    }

    @PostMapping
    public ResponseEntity<Response<FamilyDto>> addFamily(@Valid @RequestBody FamilyDto familyDto){
        Response<FamilyDto> response = new Response<>();
        EquipmentFamily equipmentFamily = FamilyDtoMapper.toFamily(familyDto);
        try {
            response.setResult(FamilyDtoMapper.toDto(familyService.save(equipmentFamily)));
            response.setMessage("Equipment family has been added successfully");
        }catch (ValidationException e) {
            response.setMessage("Equipment family has not been added");
            response.setErrors(List.of(e.getCustomError()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<FamilyDto>> updateFamily(@Valid @RequestBody FamilyDto familyDto, @PathVariable Long id) {
        Response<FamilyDto> response = new Response<>();
        EquipmentFamily equipmentFamily = FamilyDtoMapper.toFamily(familyDto);
        equipmentFamily.setId(id);
        try {
            response.setResult(FamilyDtoMapper.toDto(familyService.update(equipmentFamily)));
            response.setMessage("Equipment family has been updated successfully");
        }catch (ValidationException e) {
            response.setMessage("Equipment family has not been updated");
            response.setErrors(List.of(e.getCustomError()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public List<EquipmentFamily> findAll(){
        return familyService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            familyService.delete(id);
            return ResponseEntity.ok("EquipmentFamily a été supprimé avec success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression !");        }
    }

}
