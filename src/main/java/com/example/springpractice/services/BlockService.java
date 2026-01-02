//package com.example.springpractice.services;
//
//
//
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.util.List;
//
//@Service
//public class BlockService {
//
//    // Hardcoded for now (later move to DB or config)
//    private static final List<String> BLOCKED_DOMAINS = List.of(
//            "facebook.com",
//            "youtube.com",
//            "instagram.com"
//    );
//
//    public boolean isBlocked(String url) {
//        try {
//            URI uri = new URI(url);
//            String host = uri.getHost(); // ex: www.youtube.com
//
//            if (host == null) return false;
//
//            // Remove "www."
//            host = host.startsWith("www.") ? host.substring(4) : host;
//
//            for (String blocked : BLOCKED_DOMAINS) {
//                if (host.equals(blocked) || host.endsWith("." + blocked)) {
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//            // Invalid URL → block for safety
//            return true;
//        }
//        return false;
//    }
//}
//
