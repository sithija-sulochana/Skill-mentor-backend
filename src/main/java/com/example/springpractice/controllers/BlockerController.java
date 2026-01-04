//package com.example.springpractice.controllers;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@RestController
//public class BlockerController {
//    @GetMapping("/check")
//    public String checkSite(@RequestParam String url) throws Exception {
//
//         List<String> blockSites = List.of(
//                "youtube.com",
//                 "facebook.com",
//                 "pornhub.com",
//                 "x-hamster.com"
//
//        );
//
//         for(String site : blockSites){
//             if(url.contains(site)){
////                 throw new Exception("You can't access to the site");
//                 return "❌ BLOCKED: " + site;
//             }
//         }
//         return "Allwoed ✅";
//    }
//
//    @GetMapping("/proxy")
//    public ResponseEntity<?> proxy(@RequestParam String url) {
//
//        if (isBlocked(url)) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body("🚫 Website Blocked");
//        }
//
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);
//
//        return ResponseEntity.ok(response);
//    }
//
//}
