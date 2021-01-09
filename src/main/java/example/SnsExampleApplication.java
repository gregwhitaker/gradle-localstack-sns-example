package example;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple example that publishes messages to a mock SNS topic created by the Gradle LocalStack Plugin and then listens to
 * the published messages, via a mock SQS queue configured as a topic listener, and prints them to the console.
 */
public class SnsExampleApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SnsExampleApplication.class);

    public static void main(String... args) {
        // Setup clients
        final AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
                .build();

        final AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
                .build();

        // Get arns and urls
        final String topicArn = "arn:aws:sns:us-east-1:000000000000:example-topic";     // Arns in localstack are hardcoded to all zeros for account number
        final String queueUrl = sqsClient.getQueueUrl("example-topic-queue").getQueueUrl();

        LOG.info("Publishing messages to topic...");

        // Publish messages on topic
        for (int i = 1; i <= 5; i++) {
            final String message = "test-message-" + i;

            LOG.info("Publishing message to topic: {}", message);
            snsClient.publish(topicArn, message, "test-subject");
        }

        // Receive messages from queue
        int receiveCnt = 0;
        while (receiveCnt < 5) {
            LOG.info("Receiving messages from queue...");
            final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
            receiveMessageRequest.setWaitTimeSeconds(20);

            final ReceiveMessageResult receiveMessageResult = sqsClient.receiveMessage(receiveMessageRequest);
            for (Message message : receiveMessageResult.getMessages()) {
                LOG.info("Received message: {}", message.getBody());
                sqsClient.deleteMessage(queueUrl, message.getReceiptHandle());
                receiveCnt++;
            }
        }
    }
}
