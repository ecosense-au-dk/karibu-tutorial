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

package cs.karibu.exampledomain;

import java.io.IOException; 

import org.codehaus.jackson.JsonGenerationException; 
import org.codehaus.jackson.map.*; 

import dk.au.cs.karibu.serialization.Serializer;

/** A standard implementation of the serializer that 
* serializes into JSON strings, encoded as byte array. 
*  
* @author Henrik Baerbak Christensen, Aarhus University 
* 
* @param <T> 
*/ 
public class StandardJSONSerializer<T> implements Serializer<T> { 

  private ObjectMapper mapper; 

  public StandardJSONSerializer() { 
    mapper = new ObjectMapper(); 
  } 

  @Override 
  public byte[] serialize(T myData) { 
    String result = null; 
    try { 
      result = mapper.writeValueAsString(myData); 
    } catch (JsonGenerationException e) { 
      e.printStackTrace(); 
    } catch (JsonMappingException e) { 
      e.printStackTrace(); 
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
    return result.getBytes(); 
  } 
} 
