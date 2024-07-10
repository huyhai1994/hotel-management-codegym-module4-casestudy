package com.codegym.hotelmanagementsystemcodegymmodule4.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IAwsS3Service {

    String saveImageToS3(MultipartFile photo);
}