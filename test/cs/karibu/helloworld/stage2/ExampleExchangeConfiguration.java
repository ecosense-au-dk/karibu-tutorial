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
 
import com.rabbitmq.client.Address; 

import dk.au.cs.karibu.producer.rabbitmq.*;
 
/** Example configuration object for RabbitMQ exchange. 
 * Actually, it is a pretty "they way it should be" configuration 
 * for a single machine with standard credentials in a test setup. 
 *  
 * @author Henrik Baerbak Christensen, Aarhus University 
 * 
 */ 
public class ExampleExchangeConfiguration implements 
    RabbitExchangeConfiguration { 
 
  private String mqServerName; 
 
  public ExampleExchangeConfiguration(String rabbitMQServerName) { 
    mqServerName = rabbitMQServerName; 
  } 
 
  @Override 
  public String getUsername() { 
    // default RabbitMQ 
    return "guest"; 
  } 
 
  @Override 
  public String getPassword() { 
    // default RabbitMQ 
    return "guest"; 
  } 
 
  @Override 
  public Address[] getServerAddressList() { 
    // the default port is 5672, and the 'cluster' is a single machine 
    Address[] clusterAddr = new Address[] { new Address(mqServerName, 5672) }; 
    return clusterAddr; 
  } 
 
  @Override 
  public String getExchangeName() { 
    return "ecosense-exchange-test"; 
  } 
 
  @Override 
  public boolean isExchangeDurable() { 
    return true; 
  } 
 
  @Override 
  public String getExchangeType() { 
    return RabbitConstants.TOPIC; 
  } 
 
  public String toString() { 
    return "Guest Exchange on "+mqServerName+" port 5672."; 
  } 
 
  @Override 
  public boolean isSSLConnection() { 
    return false; 
  } 
} 
