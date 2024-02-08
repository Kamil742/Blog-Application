//package com.application.prodevblogs.config;
//
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.Gateway;
//import org.springframework.integration.annotation.MessagingGateway;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.dsl.context.IntegrationFlowContext;
//import org.springframework.integration.ftp.dsl.Ftp;
//import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
//import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.ObjectInputFilter;
//
//
//
//@Configuration
//public class Configure {
//    @Value("${ftp.port:20}")
//    Integer port;
//    @Value("${ftp.host}")
//    String host;
//    @Value("${ftp.username}")
//    String username;
//    @Value("${ftp.password}")
//    String password;
//    private IntegrationFlowContext flowContext;
//    public Configure(IntegrationFlowContext flowContext){
//        this.flowContext=flowContext;
//
//    }
//
//    @Bean
//    public IntegrationFlow ftpUploadFlow() {
//        return IntegrationFlows.from("fileInputChannel")
//                .handle(Ftp.outboundAdapter(defaultFtpSessionFactory())
//                        .useTemporaryFileName(true)
//                        .remoteDirectoryExpression("headers['/home/admin/ftp/files']"))
//                .get();
//    }
//    @Bean
//    public DefaultFtpSessionFactory defaultFtpSessionFactory(){
//        DefaultFtpSessionFactory sessionFactory=new DefaultFtpSessionFactory();
//        sessionFactory.setUsername(username);
//        sessionFactory.setPassword(password);
//        sessionFactory.setHost(host);
//        sessionFactory.setPort(port);
//        return sessionFactory;
//    }
//
//    @Bean
//    public FtpRemoteFileTemplate ftpRemoteFileTemplate(DefaultFtpSessionFactory defaultFtpSessionFactory){
//        return new FtpRemoteFileTemplate(defaultFtpSessionFactory);
//    }
//}
