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
 
import java.io.IOException; 
import java.util.Stack; 

import dk.au.cs.karibu.backend.ServerRequestHandler;
import dk.au.cs.karibu.producer.ChannelConnector;
 
 
/** A IPC implementation used for single-VM testing.  
 * Connects a client request handler with a server 
 * request handler using simple in-process method call. 
 *  
 * Augmented with code to simulate connection failures. 
 * You can push exceptions to simulate onto a stack 
 * using the 'pushException...' method. Then 
 * the next calls to send will fail with the 
 * next exception on the stack until it is 
 * empty, after which normal send is resumed. 
 *  
 * @author Henrik Baerbak Christensen, Aarhus University 
 * 
 */ 
public class InVMInterProcessConnector implements ChannelConnector { 
 
  private ServerRequestHandler serverRequestHandler; 
  private boolean connectionIsOpen; 
  private int countOfCallsToOpen; 
 
  // The stack of exceptions to throw to simulate 
  // send failures 
  private Stack<Exception> exceptionToThrowStack;  
 
  /** Create a connector that makes direct method calls to 
   * the server request handler. 
   * @param srh 
   */ 
  public InVMInterProcessConnector(ServerRequestHandler srh) { 
    this.serverRequestHandler = srh; 
    connectionIsOpen = false; 
    exceptionToThrowStack = new Stack<Exception>(); 
    countOfCallsToOpen = 0; 
  } 
 
  @Override 
  public void openConnection() throws IOException { 
    connectionIsOpen = true; countOfCallsToOpen++; 
  } 
 
  @Override 
  public void closeConnection() throws IOException { 
    connectionIsOpen = false; 
  } 
   
   
 
  public void pushExceptionToBeThrownAtNextSend( 
      Exception theExceptionToThrow) { 
    exceptionToThrowStack.push(theExceptionToThrow);   
  } 
 
  @Override 
  public void send(byte[] bytes, String topic) throws IOException { 
    if ( ! connectionIsOpen ) {  
      throw new RuntimeException("Connection has not been opened / in VM connector"); 
    } 
 
    if ( ! exceptionToThrowStack.isEmpty() ) { 
      Exception nextToThrow = exceptionToThrowStack.pop(); 
      if ( nextToThrow instanceof RuntimeException ) { 
        RuntimeException cache1 = (RuntimeException) nextToThrow; 
        throw cache1; 
      } else if ( nextToThrow instanceof IOException ) { 
        IOException cache2 = (IOException)nextToThrow; 
        throw cache2; 
      } 
    } 
    serverRequestHandler.receive(bytes); 
  } 
 
  public int getCountOfCallsToOpen() { 
    return countOfCallsToOpen; 
  } 
 
  @Override 
  public boolean isOpen() { 
    return connectionIsOpen; 
  } 
} 
