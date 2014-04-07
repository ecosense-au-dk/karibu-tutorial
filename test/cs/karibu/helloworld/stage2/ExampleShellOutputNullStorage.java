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
 
import com.mongodb.BasicDBObject; 

import cs.karibu.backend.ProcessingStrategy;
 
 
/** Final processing that outputs BSON object on the console 
 * 
 * @author Henrik Baerbak Christensen, Aarhus University
 *
 */
public class ExampleShellOutputNullStorage implements ProcessingStrategy { 
 
  @Override 
  public void process(String producerCode, BasicDBObject dbo) { 
    System.out.println("Received payload with producer code:" + producerCode); 
    if ( producerCode.equals(ExampleCodes.EXAMPLE_PRODUCER_CODE)) { 
      System.out.println(" *** The favorite game of "+dbo.getString("name")+ " is "+dbo.getString("game")+ " ***"); 
    } else { 
      System.out.println("Unknown producer code - ignoring..."); 
    } 
 
  } 
 
} 
