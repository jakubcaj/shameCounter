package com.idc.sterba.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeMetadataService {
    void savePicture(MultipartFile file, Long userId);

    byte[] getPicture(Long userId);

}
