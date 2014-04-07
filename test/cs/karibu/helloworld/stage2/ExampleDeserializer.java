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
 
import java.util.StringTokenizer; 
 

import com.mongodb.BasicDBObject; 

import cs.karibu.backend.Deserializer;
 
 
/** Example of a deserializer. 
 *  
 * @author Henrik Baerbak Christensen, Aarhus University 
 * 
 */ 
public class ExampleDeserializer implements Deserializer { 
 
  @Override 
  public BasicDBObject buildDocumentFromByteArray(byte[] payload) { 
     
    String name, game; 
    String payloadAsString = new String(payload); // convert to String 
    StringTokenizer st = new StringTokenizer(payloadAsString, "|"); 
    name = st.nextToken(); 
    game = st.nextToken(); 
     
    BasicDBObject root = new BasicDBObject(); 
    root.put("name", name); 
    root.put("game", game); 
    return root; 
  } 
   
} 
 
