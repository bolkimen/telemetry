package com.blogspot.mvnblogbuild.telemetry.awstest;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AWSTestApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(AWSTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AWSTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String bucketName = "testsergey";
        String awsAccessKey = System.getenv("AWS_ACCESS_KEY");
        String awsSecretKey = System.getenv("AWS_SECRET_KEY");
        LOG.info("Login using AWS key {}...", awsAccessKey != null ? awsAccessKey.substring(0, 5): null);

        AWSCredentials credentials = new BasicAWSCredentials(
                awsAccessKey,
                awsSecretKey
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                //.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        List<Bucket> buckets = s3client.listBuckets();
        buckets.forEach(bucket -> {
            LOG.info("BUCKET: {}", bucket.getName());
        });

        createBucket(s3client, bucketName);

        ObjectListing objectListing = s3client.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            LOG.info(os.getKey());
        }

        createObject(s3client, bucketName);
    }

    private void createBucket(AmazonS3 s3client, String bucketName) {

        if(s3client.doesBucketExist(bucketName)) {
            LOG.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }

        s3client.createBucket(bucketName);
    }

    private void createObject(AmazonS3 s3client, String bucketName) {
        s3client.putObject(
                bucketName,
                "testfolder/test2.txt",
                new File("/home/sergeydreval/work/test2.txt")
        );
    }

    private void getObject(AmazonS3 s3client, String bucketName) throws IOException {
        S3Object s3object = s3client.getObject(bucketName, "picture/pic.png");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/user/Desktop/hello.txt"));
    }

}
