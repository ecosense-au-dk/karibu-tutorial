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
 
package cs.karibu.quickstart;

import java.io.IOException;
import java.util.*;

import cs.karibu.exampledomain.*;
import dk.au.cs.karibu.producer.*;
import dk.au.cs.karibu.producer.rabbitmq.*;
import dk.au.cs.karibu.utilities.PropertyReader;

/** A very simple load generator which also serves as tutorial
 * 
 * @author Henrik Baerbak Christensen, Aarhus University
 *
 */
 
public class GenerateLoad {
 
  public static void main(String[] args) throws IOException, InterruptedException { 
    if ( args.length != 2 ) { 
      System.out.println( "usage: GenerateLoad <resource-root-folder> <maxprsec>" ); 
      System.out.println( "     resource-root-folder = root folder which contains the exchange.properties file"); 
      System.out.println( "     maxprsec = maximum msgs to be sent every second."); 
      
      System.exit(-1); 
    } 
 
    System.out.println("*** Karibu Load Generator ***");    
    
    String resourceFolderRoot = args[0];
    int maxPrSec = Integer.parseInt(args[1]);
    
    // Read in the property files
    PropertyReader rr = new PropertyReader(resourceFolderRoot);
    Properties exchangeProperties = rr.readPropertiesFailFast("exchange");

    // Configure the connector to the MQ
    ChannelConnector connector = null; 
    RabbitExchangeConfiguration rabbitExchangeConfig =
        new StandardRabbitExchangeConfiguration(exchangeProperties);
      
    connector = new RabbitChannelConnector( rabbitExchangeConfig ); 
    
    // Configure the client request handler
    ClientRequestHandler<ExampleMeasurement> readingHandler;
    StandardJSONSerializer<ExampleMeasurement> serializer;
    serializer = new StandardJSONSerializer<ExampleMeasurement>();

    readingHandler = new StandardClientRequestHandler<ExampleMeasurement>(DomainConstants.PRODUCER_CODE_EXAMPLE_MEASUREMENT,  
        connector, serializer ); 


    // Finally - generate load by repeatedly sending data...
    System.out.println("Hit CTRL-C to stop producing data - Max throughput pr sec is: "+ maxPrSec); 
    long count = 0L; int countThisSecond = 0;
    long time = System.currentTimeMillis();
    Calendar now; 
    ExampleMeasurement data;

    while ( true ) {
      // verify that we have not exceeded our max send limit pr sec
      if ( countThisSecond < maxPrSec ) {
        now = Calendar.getInstance(); 
        data = new ExampleMeasurement(now, count); 

        readingHandler.send(data, DomainConstants.STORE_TOPIC_EXAMPLE_MEASUREMENT); 
        count++; 
        countThisSecond++;
        if ( count % 500 == 0) { 
          System.out.println("  Send Count = "+count + " at "+ new Date() ); 
        } 
      }
      
      // and reset the counters if 1 sec has gone
      long t = System.currentTimeMillis();
      if ( t >= time+1000 ) {
        time = t;
        countThisSecond = 0;
      }
    } 
  }
}
