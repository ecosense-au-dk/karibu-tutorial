package cs.karibu.helloworld.stage2;

import cs.karibu.backend.rabbitmq.RabbitQueueConfiguration;

/** For the sake of the tutorial the storage queue
 * configuration is programmatically coded. For normal
 * usage, please use the StandardStorageQueueConfiguration
 * which reads a property file instead.
 * 
 * @author Henrik Baerbak Christensen, Aarhus University
 *
 */
public class ExampleStorageQueueConfiguration implements
    RabbitQueueConfiguration {

  @Override
  public String getQueueName() {
    return "storage-queue";
  }

  @Override
  public boolean isQueueDurable() {
    return true;
  }

  @Override
  public boolean isQueueExclusive() {
    return false;
  }

  @Override
  public boolean isQueueAutoDelete() {
    return false;
  }

  @Override
  public String getRoutingKey() {
    return "*.*.store";
  }

}
