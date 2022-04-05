//package com.olim.cvhelper.data.generator;
//
//import com.olim.cvhelper.data.Role;
//import com.olim.cvhelper.data.entity.CvApplication;
//import com.olim.cvhelper.data.entity.User;
//import com.olim.cvhelper.data.service.CvApplicationRepository;
//import com.olim.cvhelper.data.service.UserRepository;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import org.jeasy.random.EasyRandom;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.List;
//
//@SpringComponent
//public class DataGenerator {
//
//    @Bean
//    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, CvApplicationRepository cvApplicationRepository, UserRepository userRepository) {
//        return args -> {
//            Logger logger = LoggerFactory.getLogger(getClass());
////            int seed = 123;
////
////            logger.info("Generating demo data");
////
////            logger.info("... generating 2 User entities...");
////            User user = new User();
////            user.setName("John Normal");
////            user.setUsername("user");
////            user.setPassword(passwordEncoder.encode("user"));
//////            user.setProfilePictureUrl(
//////                    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
////            user.setRole(Role.USER);
////            userRepository.save(user);
////            User admin = new User();
////            admin.setName("Emma Powerful");
////            admin.setUsername("admin");
////            admin.setPassword(passwordEncoder.encode("admin"));
//////            admin.setProfilePictureUrl(
//////                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
////            admin.setRole(Role.ADMIN);
////            userRepository.save(admin);
//
//            logger.info("Generated demo data");
//            List<User> all = userRepository.findAll();
//            EasyRandom generator = new EasyRandom();
//            CvApplication cvApplication = generator.nextObject(CvApplication.class);
//            cvApplication.setAssignee(all.get(0));
//            cvApplication.setCvLink("https://google.disk.com/1234124124");
//            cvApplication.setLinkedInLink("https://www.linkedin.com/in/boriskezikov/");
//
//            CvApplication cvApplication1 = generator.nextObject(CvApplication.class);
//            cvApplication.setAssignee(all.get(0));
//            cvApplication.setCvLink("https://google.disk.com/1234124124");
//            cvApplication.setLinkedInLink("https://www.linkedin.com/in/boriskezikov/");
//
//            CvApplication cvApplication2 = generator.nextObject(CvApplication.class);
//            cvApplication.setAssignee(all.get(0));
//            cvApplication.setCvLink("https://google.disk.com/1234124124");
//            cvApplication.setLinkedInLink("https://www.linkedin.com/in/boriskezikov/");
//
//            CvApplication cvApplication3 = generator.nextObject(CvApplication.class);
//            cvApplication.setAssignee(all.get(0));
//            cvApplication.setCvLink("https://google.disk.com/1234124124");
//            cvApplication.setLinkedInLink("https://www.linkedin.com/in/boriskezikov/");
//
//            CvApplication cvApplication4 = generator.nextObject(CvApplication.class);
//            cvApplication.setAssignee(all.get(0));
//            cvApplication.setCvLink("https://google.disk.com/1234124124");
//            cvApplication.setLinkedInLink("https://www.linkedin.com/in/boriskezikov/");
//
//            cvApplicationRepository.saveAll(List.of(cvApplication, cvApplication1, cvApplication2, cvApplication3, cvApplication4));
//
//        };
//    }
//
//}