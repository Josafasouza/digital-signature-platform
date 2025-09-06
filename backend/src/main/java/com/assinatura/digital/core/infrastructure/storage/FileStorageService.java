package com.assinatura.digital.core.infrastructure.storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class FileStorageService {
      private final Path fileStorageLocation;

      public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
           this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
           try {
                Files.createDirectories(this.fileStorageLocation);
           } catch (IOException ex) {
                throw new RuntimeException("Não foi possível criar o diretório para armazenamento dos arquivos.");
           }
      }

      public String storeFile(MultipartFile file) {
           String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
           try {
                if (fileName.contains("..")) {
                     throw new RuntimeException("Caminho de arquivo inválido.");
                }
                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return targetLocation.toString();
           } catch (IOException ex) {
                throw new RuntimeException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
           }
      }

      public String getFileHash(MultipartFile file) {
           try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = digest.digest(file.getBytes());
                return Base64.getEncoder().encodeToString(hashBytes);
           } catch (NoSuchAlgorithmException | IOException e) {
                throw new RuntimeException("Não foi possível gerar o hash do arquivo.", e);
           }
      }
}
