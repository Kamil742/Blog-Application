//package com.application.prodevblogs.controller;
//
//import com.application.prodevblogs.exceptions.BlogFilesNotFoundException;
//import com.application.prodevblogs.model.BlogFiles;
//import com.application.prodevblogs.service.BlogFilesService;
//import com.application.prodevblogs.service.serviceImpl.FtpServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/api/blogFiles")
//public class BlogFilesController {
//
//    private final BlogFilesService blogFilesService;
//    private FtpServiceImpl ftpService;
//    @Autowired
//    public BlogFilesController(BlogFilesService blogFilesService,FtpServiceImpl ftpService) {
//        this.ftpService=ftpService;
//        this.blogFilesService = blogFilesService;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<String> createBlogFiles(
//            @RequestBody BlogFiles blogFiles) throws RuntimeException {
//        try{
//            BlogFiles createdBlogFiles = blogFilesService.createBlogFiles(blogFiles);
//            return new ResponseEntity<>("BlogFiles Created Successfully", HttpStatus.CREATED);
//        }catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//
//
//    @GetMapping("/{BlogFileId}")
//    public ResponseEntity<BlogFiles> getBlogFiles(
//            @PathVariable Long BlogFileId) throws RuntimeException {
//        BlogFiles blogFiles = blogFilesService.getBlogFilesById(BlogFileId);
//        return new ResponseEntity<>(blogFiles, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/getBlogFiles")
//    public ResponseEntity<List<BlogFiles>> getAllBlogFiles() throws RuntimeException {
//        List<BlogFiles> blogFiles = blogFilesService.getAllBlogFiles();
//        return new ResponseEntity<>(blogFiles, HttpStatus.OK);
//    }
//
//
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            return new ResponseEntity<>(ftpService.saveFile(file),HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.FAILED_DEPENDENCY);
//        }
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<?> getList(){
//        try {
//            return new ResponseEntity<>(ftpService.getAllList(),HttpStatus.FOUND);
//        } catch (IOException e) {
//            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @GetMapping("/download")
//    public ResponseEntity<?> getDownload(@RequestParam String path){
//        try {
//            return ResponseEntity.ok(ftpService.getFile(path));
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//        }
//
//    }
//    @GetMapping("/download/{file}")
//    public ResponseEntity<?> getFile(@PathVariable String file){
//        try {
//            ftpService.getFile(file);
//            return ResponseEntity.ok("ok");
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//        }
//
//    }
//    @DeleteMapping("/delete/{BlogFileId}")
//    public ResponseEntity<Void> deleteBlogFiles(@PathVariable Long BlogFileId) throws RuntimeException, BlogFilesNotFoundException {
//        blogFilesService.deleteBlogFiles(BlogFileId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}