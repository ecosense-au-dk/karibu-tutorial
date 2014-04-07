/*
 * Copyright 2013 Henrik Baerbak Christensen, Aarhus University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
 
package cs.karibu.helloworld.stage2; 
 
import org.slf4j.*; 

import cs.karibu.backend.*;
import cs.karibu.backend.rabbitmq.*;
import cs.karibu.producer.rabbitmq.RabbitExchangeConfiguration;
 
/** Manual test case - Consume Hello World data being 
 * produced by the 'Producer' application. 
 *  
 * This illustrates 'Stage 2' of the Karibu testing 
 * environment with a binding to the RabbbitMQ system. 
 *  
 * @author Henrik Baerbak Christensen, Aarhus University 
 * 
 */ 
public class Consumer { 
 
  public static void main(String[] args) throws InterruptedException { 
    String rabbitMQServerName = args[0]; 
    System.out.println("Karibu - Hello World - Stage 2 - consumer."); 
    System.out.println("  Assumes a running Rabbit MQ on port 5672 at "+ rabbitMQServerName+"."); 
     
    final Logger log = LoggerFactory.getLogger(Consumer.class); 
     
    // As storage, we use a null storage that simply outputs to the shell 
    ProcessingStrategy database; 
    database = new ExampleShellOutputNullStorage(); 
       
    // No fancy factory for deserializers, we know the single one 
    // that must be used. 
    DeserializerFactory factory = new DeserializerFactory() { 
      @Override 
      public Deserializer createDeserializer(String producerCode) { 
        Deserializer returnvalue = null; 
        if ( producerCode.equals(ExampleCodes.EXAMPLE_PRODUCER_CODE)) { 
          returnvalue = new ExampleDeserializer(); 
        } else { 
          log.error("Requested deserializer for unknown producer code: "+producerCode); 
        } 
        return returnvalue; 
      } 
    }; 
      
    // Configure the rabbit exchange and queue 
    RabbitExchangeConfiguration rec =  
        new ExampleExchangeConfiguration(rabbitMQServerName); 
    RabbitQueueConfiguration rqc =  
        new ExampleStorageQueueConfiguration(); 
 
    // Configure and create the MessageReceiverEndpoint, 
    // using a RabbitMQ as polling consumer 
    PollingConsumer pollingConsumer = 
        new RabbitMQPollingConsumer( rec, rqc ); 
    // .... and configure it and spawn the thread 
    MessageReceiverEndpoint receiverEndpoint;
    receiverEndpoint = new MessageReceiverEndpointFactory.Builder().
        pollingConsumer(pollingConsumer).
        processingStrategy(database).
        deserializerFactory(factory).
        logger(log).
        build();
        
    // Finally, spawn the thread ... 
    Thread receiverThread = new Thread( receiverEndpoint, "ReceiverThread" ); 
    System.out.println("Start receiving payloads. Hit Ctrl-C to terminate..."); 
     
    receiverThread.start(); 
    receiverThread.join(); // delay main thread until receiver endpoint terminates. 
  } 
} 
