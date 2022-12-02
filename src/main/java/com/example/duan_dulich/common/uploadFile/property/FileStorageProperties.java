package com.example.duan_dulich.common.uploadFile.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
        private String uploadDir;
}
