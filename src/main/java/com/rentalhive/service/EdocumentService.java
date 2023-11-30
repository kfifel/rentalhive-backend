package com.rentalhive.service;

import com.rentalhive.domain.Edocument;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EdocumentService {
    public Edocument save(Edocument edocument) throws ValidationException;
    public List<Edocument> getAllEdocument();
    public Edocument update(Edocument edocument, Long id) throws ValidationException;
    public void deleteDocument(long id);
}
