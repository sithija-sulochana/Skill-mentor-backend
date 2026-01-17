//package com.example.springpractice.utils;
//
//
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.pipeline.Annotation;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
//import edu.stanford.nlp.util.CoreMap;
//
//import java.util.List;
//import java.util.Properties;
//
//public final class SentimentUtils {
//
//    // Singleton pipeline (heavy object, should not be recreated)
//    private static final StanfordCoreNLP PIPELINE;
//
//    static {
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,parse,sentiment");
//        PIPELINE = new StanfordCoreNLP(props);
//    }
//
//    // Prevent instantiation
//    private SentimentUtils() {}
//
//    /**
//     * Analyze sentiment of given text.
//     *
//     * @param text input text
//     * @return POSITIVE, NEGATIVE, NEUTRAL, VERY_POSITIVE, VERY_NEGATIVE
//     */
//    public static String analyzeSentiment(String text) {
//        if (text == null || text.isBlank()) {
//            return "NEUTRAL";
//        }
//
//        Annotation annotation = new Annotation(text);
//        PIPELINE.annotate(annotation);
//
//        List<CoreMap> sentences =
//                annotation.get(CoreAnnotations.SentencesAnnotation.class);
//
//        if (sentences == null || sentences.isEmpty()) {
//            return "NEUTRAL";
//        }
//
//        // Use the first sentence sentiment
//        return sentences.get(0)
//                .get(SentimentCoreAnnotations.SentimentClass.class);
//    }
//}
