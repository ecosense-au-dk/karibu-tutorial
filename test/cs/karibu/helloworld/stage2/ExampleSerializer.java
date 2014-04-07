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

import cs.karibu.producer.Serializer;
 
/** Example of a serializer that converts the domain class to the 
 * on-the-wire format 
 * 
 * @author Henrik Baerbak Christensen, Aarhus University 
 *  
 */ 
public class ExampleSerializer implements Serializer<ExampleDomainClass> { 
 
  @Override 
  public byte[] serialize(ExampleDomainClass myData) { 
    // just make something for the sake of the example 
    String onTheWireString = myData.getName() + "|" + myData.getGame(); 
    byte[] payload = onTheWireString.getBytes(); 
    return payload; 
  } 
} 
