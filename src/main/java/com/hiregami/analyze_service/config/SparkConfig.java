package com.hiregami.analyze_service.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

  @Bean
  public SparkConf sparkConf() {
    return new SparkConf().setAppName("AnalyzeService").setMaster("local[*]");
  }

  @Bean
  public JavaSparkContext javaSparkContext() {
    return new JavaSparkContext(sparkConf());
  }
}
