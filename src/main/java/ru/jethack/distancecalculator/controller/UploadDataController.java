package ru.jethack.distancecalculator.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.jethack.distancecalculator.service.UploadDataService;

@Controller
@RequestMapping("upload")
public class UploadDataController {

    final
    UploadDataService uploadDataService;

    public UploadDataController(UploadDataService uploadDataService) {
        this.uploadDataService = uploadDataService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

        Object result = uploadDataService.saveData(file);
        if (result == null) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.badRequest().body(((Exception) result).getMessage());
        }

    }
}
