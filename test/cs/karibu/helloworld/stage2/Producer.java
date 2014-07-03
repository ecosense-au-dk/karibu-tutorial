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
 
import java.io.IOException; 

import dk.au.cs.karibu.serialization.*;
import dk.au.cs.karibu.producer.*;
import dk.au.cs.karibu.producer.rabbitmq.*;
 
/** Manual test case - produce Hello World data to 
 * be consumed by the 'Consumer' application. 
 *  
 * This illustrates 'Stage 2' of the Karibu testing 
 * environment. 
 *  
 * To experiment, try  
 *  
 * A) to run this while the 
 * consumer is NOT running (validate that 
 * messages are queue in the rabbit MQ) 
 *  
 * B) and of course, try running while 
 * the consumer is active. 
 *  
 * @author Henrik Baerbak Christensen, Aarhus University 
 * 
 */ 
public class Producer { 
 
 
  /** Send three chunks of data to the rabbit MQ */ 
  public static void main(String[] args) throws InterruptedException { 
    String rabbitMQServerName = args[0]; 
    System.out.println("Karibu - Hello World - Stage 2 - producer."); 
    System.out.println("  Assumes a running Rabbit MQ on port 5672 at "+ rabbitMQServerName+"."); 
     
    System.out.println( "Creating client request handler, using RabbitMessageProducer as connector." ); 
 
    ChannelConnector connector = null; 
    // Define the characteristics of the exchange on the Rabbit MQ. 
    RabbitExchangeConfiguration rec = 
        new ExampleExchangeConfiguration(rabbitMQServerName); 
    
    // And try to instantiate the connector 
    try { 
      connector = new RabbitChannelConnector( rec ); 
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
    // Next configure the client request handler 
    ClientRequestHandler<ExampleDomainClass> crh; 
    crh = new StandardClientRequestHandler<ExampleDomainClass>(ExampleCodes.EXAMPLE_PRODUCER_CODE,  
        connector, new ExampleSerializer()); 
     
    System.out.println("** Sending 3 payloads with 5 seconds delays ***"); 
     
    ExampleDomainClass edc; 
     
    // Finally, send the data... 
    try { 
      System.out.println(" Sending payload 1."); 
      edc = new ExampleDomainClass("Henrik", "StarCraft II"); 
      crh.send(edc, ExampleCodes.EXAMPLE_TOPIC); 
      Thread.sleep(5000); 
  
      System.out.println(" Sending payload 2."); 
      edc = new ExampleDomainClass("Mathilde", "MovieStar Planet"); 
      crh.send(edc, ExampleCodes.EXAMPLE_TOPIC); 
      Thread.sleep(5000); 
 
      System.out.println(" Sending payload 3."); 
      edc = new ExampleDomainClass("Magnus", "Diablo II (Diable III is bad)"); 
      crh.send(edc, ExampleCodes.EXAMPLE_TOPIC); 
       
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
     
    System.out.println("** Done ***"); 
  } 
} 
