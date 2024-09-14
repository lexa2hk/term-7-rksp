package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileEntityRepository repository;
    private final FileServiceClient0 client0;
    private final FileServiceClient1 client1;
    private final FileServiceClient2 client2;

    @PostMapping("/upload")
    public Long uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        try {
//            client0.uploadFile(file);
//        } catch (Exception ignored){}
//        try {
//            client1.uploadFile(file);
//        } catch (Exception ignored){}
//        try {
//            client2.uploadFile(file);
//        } catch (Exception ignored){}
        return repository.save(FileEntity.builder().bytes(file.getBytes()).build()).getId();
    }

    @GetMapping("/{id}")
    public Resource downloadFile(@PathVariable("id") Long id) {
        return new ByteArrayResource(repository.findById(id).get().getBytes());
    }
}
