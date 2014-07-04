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

import java.util.Calendar;

import org.codehaus.jackson.map.util.ISO8601DateFormat;

/** This is an example of a measurement class
 * that contains a collection of data that is
 * treated by Karibu as a "whole" to be
 * produced in a client and sent to the database
 * using Karibu client library and daemons.
 * 
 * @author Henrik Baerbak Christensen, Aarhus University
 *
 */

public class ExampleMeasurement {

  private String timestamp;
  private long count;
  private String theData;
  
  final static ISO8601DateFormat isoFormatter = new ISO8601DateFormat(); 

  public ExampleMeasurement(Calendar timestamp, long count) {
    this.timestamp = isoFormatter.format(timestamp.getTimeInMillis());
    this.count = count;
    generateDummyForTheData(count);
  }

  // 90 bytes
  final static String dummy = 
      "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
  
  /** Just generate data of some size for generating
   * traffic on the network and a bit of load on the
   * database.
   * @param count 
   */
  private void generateDummyForTheData(long count) {
    theData = new String();
    for ( int i = 0; i < 200; i++ ) {
      theData += "("+count+") " + dummy; // terrible performance, yes
    }
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String now) {
    this.timestamp = now;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public String getTheData() {
    return theData;
  }

  public void setTheData(String theData) {
    this.theData = theData;
  }


}
