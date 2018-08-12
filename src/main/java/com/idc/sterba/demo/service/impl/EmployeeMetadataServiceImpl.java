package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.secure.EmployeeMetadata;
import com.idc.sterba.demo.repository.EmployeeMetadataRepository;
import com.idc.sterba.demo.service.EmployeeMetadataService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmployeeMetadataServiceImpl implements EmployeeMetadataService {
    private EmployeeMetadataRepository employeeMetadataRepository;

    public EmployeeMetadataServiceImpl(EmployeeMetadataRepository employeeMetadataRepository) {
        this.employeeMetadataRepository = employeeMetadataRepository;
    }


    @Override
    public void savePicture(MultipartFile file, Long usedId) {
        EmployeeMetadata employeeMetadata = employeeMetadataRepository.findByEmployee_Id(usedId);
        try {
            employeeMetadata.setProfilePicture(IOUtils.toByteArray(file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        employeeMetadataRepository.save(employeeMetadata);
    }

    @Override
    public byte[] getPicture(Long userId) {
        EmployeeMetadata employeeMetadata = employeeMetadataRepository.findByEmployee_Id(userId);
        if (employeeMetadata != null) {
            return employeeMetadata.getProfilePicture();
        } else {
            return null;
        }
    }
}
