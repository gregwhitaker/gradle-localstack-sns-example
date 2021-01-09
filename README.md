# gradle-localstack-sns-example
![Build](https://github.com/gregwhitaker/gradle-localstack-sns-example/workflows/Build/badge.svg)

An example of working with mock AWS SNS endpoints using [LocalStack](https://github.com/localstack/localstack) and the [Gradle LocalStack Plugin](https://github.com/Nike-Inc/gradle-localstack).

In this example you will see how to configure a local SNS topic with an attached SQS queue using LocalStack and the Gradle LocalStack Plugin.

## Building the Example
Run the following command to build the example:

    ./gradlew clean build

## Running the Example
Follow the steps below to run the example application:

1. Run the following command to setup the LocalStack environment:

        ./gradlew startLocalStack

    If successful, you will see that the topic, queue, and subscription was created:

        > Task :setupSnsTopicWithSqsListener
        Creating SQS endpoint queue: example-topic-queue
        Created SQS endpoint queue: example-topic-queue
        Creating SNS topic: example-topic
        Created SNS topic: example-topic
        Created SNS subscription: arn:aws:sns:us-east-1:000000000000:example-topic:580f19f7-80d2-477c-85aa-0036e7af94ba
        
        > Task :startLocalStack
        LocalStack Started
        
2. Run the following command to list the SNS topics in LocalStack:
   
         ./gradlew listSnsTopics

   If successful, you will see something similar to the following in the console:

        > Task :listSnsTopics
        ┌─────────────────────────────────────────────────┬─────────────────────────────────────────────────┬────────────────────────────────────────────────┐
        │TopicName                                        │TopicArn                                         │Subscriptions                                   │
        ├─────────────────────────────────────────────────┼─────────────────────────────────────────────────┼────────────────────────────────────────────────┤
        │example-topic                                    │arn:aws:sns:us-east-1:000000000000:example-topic │arn:aws:sns:us-east-1:000000000000:example-topic│
        │                                                 │                                                 │:580f19f7-80d2-477c-85aa-0036e7af94ba           │
        └─────────────────────────────────────────────────┴─────────────────────────────────────────────────┴────────────────────────────────────────────────┘
        
3. Run the following command to execute the application:

         ./gradlew run

   If successful, you will see the following in the console:

        > Task :run
        [main] INFO example.SnsExampleApplication - Publishing messages to topic...
        [main] INFO example.SnsExampleApplication - Publishing message to topic: test-message-1
        [main] INFO example.SnsExampleApplication - Publishing message to topic: test-message-2
        [main] INFO example.SnsExampleApplication - Publishing message to topic: test-message-3
        [main] INFO example.SnsExampleApplication - Publishing message to topic: test-message-4
        [main] INFO example.SnsExampleApplication - Publishing message to topic: test-message-5
        [main] INFO example.SnsExampleApplication - Receiving messages from queue...
        [main] INFO example.SnsExampleApplication - Received message: {"Type": "Notification", "MessageId": "53bbd718-32a6-40cc-95dc-67acce361f80", "Token": null, "TopicArn": "arn:aws:sns:us-east-1:000000000000:example-topic", "Message": "test-message-1", "SubscribeURL": null, "Timestamp": "2021-01-09T15:39:10.556Z", "SignatureVersion": "1", "Signature": "EXAMPLEpH+..", "SigningCertURL": "https://sns.us-east-1.amazonaws.com/SimpleNotificationService-0000000000000000000000.pem", "Subject": "test-subject"}
        [main] INFO example.SnsExampleApplication - Receiving messages from queue...
        [main] INFO example.SnsExampleApplication - Received message: {"Type": "Notification", "MessageId": "02fbacb1-dfa0-404b-9a0f-7cec7272a9c7", "Token": null, "TopicArn": "arn:aws:sns:us-east-1:000000000000:example-topic", "Message": "test-message-2", "SubscribeURL": null, "Timestamp": "2021-01-09T15:39:10.607Z", "SignatureVersion": "1", "Signature": "EXAMPLEpH+..", "SigningCertURL": "https://sns.us-east-1.amazonaws.com/SimpleNotificationService-0000000000000000000000.pem", "Subject": "test-subject"}
        [main] INFO example.SnsExampleApplication - Receiving messages from queue...
        [main] INFO example.SnsExampleApplication - Received message: {"Type": "Notification", "MessageId": "9f4f2d4b-8b05-4664-a639-82c909a07589", "Token": null, "TopicArn": "arn:aws:sns:us-east-1:000000000000:example-topic", "Message": "test-message-3", "SubscribeURL": null, "Timestamp": "2021-01-09T15:39:10.710Z", "SignatureVersion": "1", "Signature": "EXAMPLEpH+..", "SigningCertURL": "https://sns.us-east-1.amazonaws.com/SimpleNotificationService-0000000000000000000000.pem", "Subject": "test-subject"}
        [main] INFO example.SnsExampleApplication - Receiving messages from queue...
        [main] INFO example.SnsExampleApplication - Received message: {"Type": "Notification", "MessageId": "9a4f856c-33a7-46a3-9992-c338c2983cf0", "Token": null, "TopicArn": "arn:aws:sns:us-east-1:000000000000:example-topic", "Message": "test-message-4", "SubscribeURL": null, "Timestamp": "2021-01-09T15:39:10.747Z", "SignatureVersion": "1", "Signature": "EXAMPLEpH+..", "SigningCertURL": "https://sns.us-east-1.amazonaws.com/SimpleNotificationService-0000000000000000000000.pem", "Subject": "test-subject"}
        [main] INFO example.SnsExampleApplication - Receiving messages from queue...
        [main] INFO example.SnsExampleApplication - Received message: {"Type": "Notification", "MessageId": "a87a7cdc-a65a-4b50-8638-e6ffc12edcd4", "Token": null, "TopicArn": "arn:aws:sns:us-east-1:000000000000:example-topic", "Message": "test-message-5", "SubscribeURL": null, "Timestamp": "2021-01-09T15:39:10.786Z", "SignatureVersion": "1", "Signature": "EXAMPLEpH+..", "SigningCertURL": "https://sns.us-east-1.amazonaws.com/SimpleNotificationService-0000000000000000000000.pem", "Subject": "test-subject"}

4. Run the following command to teardown the LocalStack environment:

        ./gradlew killLocalStack
    
## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/gradle-localstack-sns-example/issues).
         
## License
MIT License

Copyright (c) 2021 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.