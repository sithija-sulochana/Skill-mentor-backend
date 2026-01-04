//package com.example.springpractice.services;
//
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.pipeline.*;
//import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
//import edu.stanford.nlp.util.CoreMap;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//
//@Service
//public class SentimentService {
//
//    private final StanfordCoreNLP pipeline;
//
//    public SentimentService() {
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,parse,sentiment");
//        this.pipeline = new StanfordCoreNLP(props);
//    }
//
//    public String analyzeSentiment(String text) {
//        Annotation annotation = new Annotation(text);
//        pipeline.annotate(annotation);
//
//        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
//            return sentence.get(SentimentCoreAnnotations.SentimentClass.class);
//        }
//
//        return "NEUTRAL";
//    }
//}
