package ru.jethack.distancecalculator.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadDataService {
    Object saveData(MultipartFile file);
}
