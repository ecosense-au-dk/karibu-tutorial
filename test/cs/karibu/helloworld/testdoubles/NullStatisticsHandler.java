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

package cs.karibu.helloworld.testdoubles;

import java.util.Date;

import cs.karibu.backend.StatisticHandler;

/** Null object statistics handler - doing nothing.
 * 
 * @author Henrik Baerbak Christensen, Aarhus University
 *
 */
public class NullStatisticsHandler implements StatisticHandler {

  @Override
  public void flushToStorage() {
  }

  @Override
  public String getDaemonIP() {
    return "none";
  }

  @Override
  public Date getEndTimestamp() {
    return null;
  }

  @Override
  public String getMaxChunkProducerCode() {
    return null;
  }

  @Override
  public long getMaxChunkSize() {
    return 0;
  }

  @Override
  public Date getStartTimestamp() {
    return null;
  }

  @Override
  public String getStatusAsString() {
    return null;
  }

  @Override
  public long getTotalBytesSent() {
    return 0;
  }

  @Override
  public long getTotalCountMsg() {
    return 0;
  }

  @Override
  public void notifyReceive(String arg0, long arg1) {
  }

}
