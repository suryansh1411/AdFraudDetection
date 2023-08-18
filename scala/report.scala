import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import java.sql.Timestamp
import com.fasterxml.jackson.databind.ObjectMapper
// import spark.implicits._
import org.json.JSONObject


val spark = SparkSession.builder()
  .appName("LogProcessing")
  .getOrCreate()


// spark.sql("""CREATE DATABASE AdFraudDetection;""")
 spark.sql("""USE DATABASE AdFraudDetection;""")

// spark.sql("""CREATE TABLE log_table (
//     ip_Address STRING,
//     fraud_status BOOLEAN,
//     time_stamp TIMESTAMP,
//     country STRING
// ) USING delta;""")

val schema = spark.table("log_table").schema

val storage_account_name = "sspreportingtest"
val container_name = "adfrauddetection"
val mount_point = "/mnt/"+storage_account_name+"/"+container_name
val folder_path = mount_point+"/logs/17-08-2023"

for (logFile <- dbutils.fs.ls(folder_path)) {
 println(logFile.path)
 val logDF = spark.read.text(logFile.path)
 // Array newLogs = [];

 logDF.collect().foreach { row =>
  val value = row.getString(0)
  // println(value)
  val log: Array[String] = value.split(" ")
  val ipData = new JSONObject(log(7)+log(8)+log(9))

  val ipAddress = ipData.getJSONObject("ipData").getString("ipAddress")
  val fraud = ipData.getJSONObject("ipData").getBoolean("fraud")
  val timestamp = Timestamp.valueOf(log(0)+ " " +log(1))
  val country = ipData.getJSONObject("ipData").getString("country")

  // println((ipAddress, fraud, timestamp, country).getClass.getName)
  val newRow = Row(ipAddress, fraud, timestamp, country)
  val rowRDD = spark.sparkContext.parallelize(Seq(newRow))
  val newLogDF = spark.createDataFrame(rowRDD, schema)

  newLogDF.write
    .format("delta")
    .mode("append")
    .saveAsTable("log_table")
 }

}

///////////////////////////////////////////////////////////////////////
val df=spark.sql("""SELECT * FROM log_table;""")
df.show()

spark.sql(
 """
       CREATE TABLE fraud_report
       USING delta
       -- OPTIONS ('path' '/Data/AdfraudDetection')
       AS
       SELECT ip_Address,
       Date(time_stamp) AS date,
       country,
       COUNT(*) AS total_count,
       SUM(CASE WHEN fraud_status = 'true' THEN 1 ELSE 0 END) AS total_fraud,
       SUM(CASE WHEN fraud_status = 'false' THEN 1 ELSE 0 END) AS total_non_fraud
FROM log_table
GROUP BY ip_Address, Date(time_stamp), country;
""")