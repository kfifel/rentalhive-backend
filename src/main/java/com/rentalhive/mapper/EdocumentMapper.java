package com.rentalhive.mapper;

import com.rentalhive.domain.Edocument;
import com.rentalhive.dto.EdocumentDto;

public class EdocumentMapper {
    private EdocumentMapper() {
    }
    public static EdocumentDto toDto(Edocument edocument)
    {
        return EdocumentDto.builder().modelId(edocument.getModelId()).modelName(edocument.getModelName()).classpath(edocument.getClasspath()).build();
    }

    public static Edocument toEdocument(EdocumentDto edocumentDto)
    {
        return Edocument.builder().modelName(edocumentDto.getModelName()).modelId(edocumentDto.getModelId()).classpath(edocumentDto.getClasspath()).build();
    }
}
