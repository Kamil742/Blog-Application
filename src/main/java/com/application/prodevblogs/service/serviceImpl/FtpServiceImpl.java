//package com.application.prodevblogs.service.serviceImpl;
//
//import com.application.prodevblogs.model.BlogFiles;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.net.ftp.FTPFile;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
//import org.springframework.integration.file.remote.InputStreamCallback;
//import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import java.io.*;
//import java.util.Arrays;
//import java.util.List;
//import java.util.StringTokenizer;
//
//@Service
//public class FtpServiceImpl {
//
//    private FtpRemoteFileTemplate ftpRemoteFileTemplate;
//
//    @Autowired
//    public FtpServiceImpl(FtpRemoteFileTemplate ftpRemoteFileTemplate) {
//        this.ftpRemoteFileTemplate = ftpRemoteFileTemplate;
//        System.out.println(ftpRemoteFileTemplate.getSession().isOpen());
//        System.out.println(ftpRemoteFileTemplate.getSession().test());
//    }
//
//    public BlogFiles saveFile(MultipartFile file) throws Exception {
//        try {
//            boolean success = false;
//            FTPFile[] ftp = ftpRemoteFileTemplate.list("Desktop/");
//            Arrays.asList(ftp).stream().forEach(ftpFile -> System.out.println(ftpFile.getName()));
//            ftpRemoteFileTemplate.execute(e->{e.write(file.getInputStream(),"data/"+file.getOriginalFilename()); return null;});
//            BlogFiles blogFiles=new BlogFiles();
//            blogFiles.setPath(file.getOriginalFilename());
//            blogFiles.setSize(file.getSize());
//            System.out.println(file.getSize());
//            return blogFiles;
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public List<String> getAllList() throws IOException {
//        return  List.of(ftpRemoteFileTemplate.getSession().listNames(""));
//    }
//
//    public File getFile(String file) throws IOException {
//        StringTokenizer stringTokenizer=new StringTokenizer(file,".");
//        File temp=File.createTempFile(stringTokenizer.nextToken(),stringTokenizer.nextToken());
//        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//        ftpRemoteFileTemplate.get(file, new InputStreamCallback() {
//            @Override
//            public void doWithInputStream(InputStream stream) throws IOException {
//                FileCopyUtils.copy(stream,byteArrayOutputStream);
//                FileUtils.copyFile(temp,byteArrayOutputStream);
//            }
//        });
//        System.out.println(temp.isFile());
//        System.out.println(temp.toString());
//        Resource resource = new FileSystemResource(temp);
//
////        MultipartFile multipartFile=new CommonsMultipartFile()
//        return temp;
//    }
//}