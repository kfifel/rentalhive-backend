package com.rentalhive.service.impl;

import com.rentalhive.domain.Edocument;
import com.rentalhive.fileutil.Impl.Base64ToFileImpl;
import com.rentalhive.repository.ContractRepository;
import com.rentalhive.repository.EdocumentRepository;
import com.rentalhive.repository.OrganizationRepository;
import com.rentalhive.repository.UserRepository;
import com.rentalhive.service.EdocumentService;
import com.rentalhive.utils.CustomError;
import com.rentalhive.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;


@Service
public class EdocumentServiceImp implements EdocumentService {
    private EdocumentRepository edocumentRepository;
    private Base64ToFileImpl base64ToFile = new Base64ToFileImpl();
    private UserRepository userRepository;
    private OrganizationRepository organizationRepository;
    private ContractRepository contractRepository;


    @Autowired
    public EdocumentServiceImp(EdocumentRepository edocumentRepository, UserRepository userRepository, OrganizationRepository organizationRepository,ContractRepository contractRepository) {
        this.edocumentRepository = edocumentRepository;
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public Edocument save(Edocument edocument) throws ValidationException {
        if (edocument.getModelId() == null) {
            throw new ValidationException(new CustomError("name", "Invalid id or path name"));
        }

        if ("USER".equals(edocument.getModelName())) {
            return validateAndSave("USER",edocument, userRepository);
        } else if ("CONTRACT".equals(edocument.getModelName())) {
            return validateAndSave("CONTRACT", edocument, contractRepository);
        } else if ("ORGANIZATION".equals(edocument.getModelName())) {
            return validateAndSave("ORGANIZATION", edocument, organizationRepository);
        } else {
            throw new ValidationException(new CustomError("name", "Invalid model name"));
        }
    }

    @Override
    public List<Edocument> getAllEdocument() {
        return edocumentRepository.findAll();
    }

    @Override
    public Edocument update(Edocument edocument, Long id) throws ValidationException {
        Optional<Edocument> existingEdocument = edocumentRepository.findById(id);
        if (existingEdocument.isPresent()) {
            edocument.setId(id);
            return save(edocument);
        }
        throw new ValidationException(new CustomError("name", "Entity not found"));
    }

    @Override
    public void deleteDocument(long id) {
        Optional<Edocument> edocument = edocumentRepository.findById(id);
        if (edocument.isPresent())
            edocumentRepository.delete(edocument.get());
        else
            throw new NoSuchElementException("Role not found with id: " + id);
    }

    private Edocument validateAndSave(String folder,Edocument edocument, JpaRepository<?, Long> repository) throws ValidationException {
        if (repository.findById(edocument.getModelId()).isPresent()) {
            String Random = generateRandomNumber();
            if(base64ToFile.saveFile(edocument.getClasspath(),"/Desktop/rental-Hive/src/main/java/com/rentalhive/assets/image/"+folder, Random)) {
                edocument.setClasspath("assets/image/"+folder+"/"+Random);
                return edocumentRepository.save(edocument);
            }
            else
                throw new ValidationException(new CustomError("name", "Class Path not found"));
        } else
            throw new ValidationException(new CustomError("name", "Entity not found"));
    }

    private static String generateRandomNumber() {
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            randomNumber.append(random.nextInt(10));
        }

        return randomNumber.toString();
    }

}
