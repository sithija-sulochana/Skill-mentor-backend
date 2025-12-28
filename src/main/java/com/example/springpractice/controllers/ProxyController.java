//package com.example.springpractice.controllers;
//
//
//
//import com.example.springpractice.services.BlockService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//public class ProxyController {
//
//    private final BlockService blockService;
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public ProxyController(BlockService blockService) {
//        this.blockService = blockService;
//    }
//
//    @GetMapping("/proxy")
//    public ResponseEntity<?> proxy(@RequestParam String url) {
//
//        if (blockService.isBlocked(url)) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body("🚫 Website Blocked by Policy");
//        }
//
//        try {
//            String response = restTemplate.getForObject(url, String.class);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_GATEWAY)
//                    .body("⚠️ Unable to reach target website");
//        }
//    }
//}
