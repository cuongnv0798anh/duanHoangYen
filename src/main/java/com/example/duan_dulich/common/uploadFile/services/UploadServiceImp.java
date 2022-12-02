package com.example.duan_dulich.common.uploadFile.services;

import com.example.duan_dulich.common.uploadFile.model.UploadDto;
import com.example.duan_dulich.common.uploadFile.model.UploadMapper;
import com.example.duan_dulich.common.uploadFile.property.FileStorageProperties;
import com.example.duan_dulich.common.uploadFile.utils.exception.FileStorageException;
import com.example.duan_dulich.common.uploadFile.utils.exception.MyFileNotFoundException;
import com.example.duan_dulich.common.uploadFile.utils.respon.DataRespon;
import com.example.duan_dulich.model.entity.ImageTour;
import com.example.duan_dulich.repository.ImageReponsitory;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UploadServiceImp implements UploadService {
    private final Path fileStorage;
    @Autowired
    ImageReponsitory uploadReponsitory;

    @Autowired
    public UploadServiceImp(FileStorageProperties fileStorageProperties) {
        this.fileStorage = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorage);
        } catch (IOException e) {
            throw new FileStorageException("Khong the tao folder de upload file");
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public Collection<ImageTour> saveFile(MultipartFile[] file) {
        Collection<ImageTour> imageTours = new ArrayList<>();

        for (int i = 0; i < file.length; i++) {


            String fileName = StringUtils.cleanPath(file[i].getOriginalFilename());
            try {
                if (fileName.contains("..")) {
                    throw new FileStorageException("File chua ky tu la" + fileName);
                }
                if (!isImageFile(file[i])) {
                    throw new FileStorageException("File khong hop le");
                }
                String generatedFileName = UUID.randomUUID().toString().replace("-", "");
                String fileExtension = FilenameUtils.getExtension(file[i].getOriginalFilename());
                generatedFileName = generatedFileName + "." + fileExtension;
                Path targetLocation = this.fileStorage.resolve(generatedFileName);
                Files.copy(file[i].getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/view/")
                        .path(generatedFileName)
                        .toUriString();
                ImageTour imageTour = new ImageTour(generatedFileName, fileUri, file[i].getContentType());
                imageTours.add(imageTour);
            } catch(IOException ex){
                throw new FileStorageException("K the luu tru file " + fileName + ". Vui long thu lai", ex);
            }
            }

        return imageTours;
    }





    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorage.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    @Override
    public DataRespon getAll() {
        List<UploadDto> uploadDtoList = uploadReponsitory.findAll()
                .stream().map(UploadMapper::map).collect(Collectors.toList());
        return new DataRespon(uploadDtoList);
    }

    @Override
    public ResponseEntity delete(Integer id) {
        Optional<ImageTour> uploadEntity = uploadReponsitory.findById(id);
        if (!uploadEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID nay khong ton tai");
        }
        try {
            uploadReponsitory.delete(uploadEntity.get());
            File file = new File(fileStorage + "/" + uploadEntity.get().getFilename());
            if (file.delete()) {
                System.out.println("Xoa thanh cong trong local");
            } else {
                System.out.println("Xoa khong thanh cong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Xoa thanh cong");

    }

    @Override
    public ResponseEntity update(Integer id, MultipartFile file) {
        ImageTour uploadEntity = uploadReponsitory.getById(id);
        Optional<ImageTour> optionalUploadEntity = uploadReponsitory.findById(id);
        try {
            if (!optionalUploadEntity.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID nay khong ton tai");
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                uploadEntity.setId(id);
                if (fileName.contains("..")) {
                    throw new FileStorageException("File chua ky tu la" + fileName);
                }
                if (!isImageFile(file)) {
                    throw new FileStorageException("File khong hop le");
                }
                String generatedFileName = UUID.randomUUID().toString().replace("-", "");
                String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                generatedFileName = generatedFileName + "." + fileExtension;
                Path targetLocation = this.fileStorage.resolve(generatedFileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/view/")
                        .path(generatedFileName)
                        .toUriString();
                File file1 = new File(fileStorage + "/" + optionalUploadEntity.get().getFilename());
                System.out.printf("ssss" + uploadEntity.getFilename());
                if (file1.delete()) {
                    System.out.println("Xoa thanh cong trong local");
                } else {
                    System.out.println("Xoa khong thanh cong");
                }
                uploadEntity.setFilename(generatedFileName);
                uploadEntity.setFileType(file.getContentType());
                uploadEntity.setFileUrl(fileUri);

                uploadReponsitory.save(uploadEntity);
            } catch (IOException ex) {
                throw new FileStorageException("K the luu tru file " + fileName + ". Vui long thu lai", ex);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Update thanh cong");
    }
}
