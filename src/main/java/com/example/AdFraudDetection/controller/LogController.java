package com.example.AdFraudDetection.controller;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class LogController {

    private final BlobContainerClient blobContainerClient;

    @Autowired
    LogController(@Value("${azure.storage.blob.endpoint}") String endpoint,
                  @Value("${azure.storage.blob.sasToken}") String sasToken,
                  @Value("${azure.storage.blob.containerName}") String containerName) {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().endpoint(endpoint).sasToken(sasToken).buildClient();

        this.blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);

        System.out.println(endpoint);
        System.out.println(containerName);
    }

    @Scheduled(initialDelay = 30000, fixedRate = 30000)
    public void uploadlog()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        String blobName = "requests-"+formattedDateTime;

        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        String filePath = "./logs/requests.log";
        String content = "Hello from docker";
        InputStream contentStream = new ByteArrayInputStream(content.getBytes());
        blobClient.upload(contentStream, content.length());

        //Upload the log file
        File file = new File(filePath);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            blobClient.upload(inputStream, file.length(),true);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }


        //Empty the log file
        try{
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}





//    // Create a local file in the ./data/ directory for uploading and downloading
//    String localPath = "./data/";
//    String fileName = "quickstart" + java.util.UUID.randomUUID() + ".txt";
//
//    // Get a reference to a blob
//    BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
//
//    // Write text to the file
//    FileWriter writer = null;
//try
//        {
//        writer = new FileWriter(localPath + fileName, true);
//        writer.write("Hello, World!");
//        writer.close();
//        }
//        catch (IOException ex)
//        {
//        System.out.println(ex.getMessage());
//        }
//
//        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());
//
//// Upload the blob
//        blobClient.uploadFromFile(localPath + fileName);



//import org.springframework.stereotype.Service;
//        import com.azure.storage.blob.BlobServiceClient;
//        import com.azure.storage.blob.BlobServiceClientBuilder;
//        import com.azure.storage.blob.specialized.BlobClient;
//        import java.io.InputStream;
//
//@Service
//public class BlobStorageService {
//
//    private final BlobServiceClient blobServiceClient;
//
//    public BlobStorageService(BlobServiceClient blobServiceClient) {
//        this.blobServiceClient = blobServiceClient;
//    }
//
//    public void uploadBlob(InputStream data, long dataSize, String blobName) {
//        BlobClient blobClient = blobServiceClient.getBlobContainerClient("your-container-name")
//                .getBlobClient(blobName);
//
//        blobClient.upload(data, dataSize, true);
//    }
//}
