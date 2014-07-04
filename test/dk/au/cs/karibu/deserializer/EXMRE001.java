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

package dk.au.cs.karibu.deserializer;

import java.util.Date;

import com.mongodb.BasicDBObject;

import org.codehaus.jackson.map.util.ISO8601Utils;

import dk.au.cs.karibu.serialization.Deserializer;

/** An example deserializer for JSON payloads. 
 * You can write these rather generic using methods
 * from the mongo and jackson libraries.
 * 
 * To allow Mongo to index timestamps properly,
 * convert it from a string to a real Mongo Data object.
 * 
 * @author Henrik Baerbak Christensen, Aarhus University
 *
 */

public class EXMRE001 implements Deserializer {

  @Override
  public BasicDBObject buildDocumentFromByteArray(byte[] payload) {
    Date theTimestamp;

    // The binary payload is actually a string in JSON format 
    String asJSON = new String(payload); 
     
    // and Mongo has utils to convert that :) 
    BasicDBObject dbo = (BasicDBObject) com.mongodb.util.JSON.parse(asJSON); 
    
    // Read out the timestamp as string and write it back as Date object 
    String asISODate = dbo.getString("timestamp"); 
    theTimestamp = ISO8601Utils.parse(asISODate); 
    dbo.put("timestamp", theTimestamp); 

    return dbo;
  }

}
