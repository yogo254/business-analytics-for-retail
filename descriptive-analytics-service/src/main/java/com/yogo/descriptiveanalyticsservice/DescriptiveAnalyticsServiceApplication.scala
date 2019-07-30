package com.yogo.descriptiveanalyticsservice;


import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.boot.{ApplicationArguments, ApplicationRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
class DescriptiveAnalyticsServiceApplication extends ApplicationRunner {
	override def run(args: ApplicationArguments): Unit = {
		val conf=new SparkConf().setMaster("local[2]")
  		.setAppName("analytics")

				val spark=SparkSession.builder()
  		.config(conf).getOrCreate()

		val d=spark.read.option("header",true)
			.option("inferSchema",true).csv("olist_sellers_dataset.csv")
		d.printSchema()

	}
}
object DescriptiveAnalyticsServiceApplication {

	def main(args:Array[String]):Unit ={
		SpringApplication.run(classOf[DescriptiveAnalyticsServiceApplication])


	}

}
