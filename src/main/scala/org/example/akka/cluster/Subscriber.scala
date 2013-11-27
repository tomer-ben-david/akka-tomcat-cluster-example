package org.example.akka.cluster

import akka.actor.{Actor, ActorLogging}
import akka.contrib.pattern.{DistributedPubSubExtension, DistributedPubSubMediator}
import org.example.akka.cluster.conf.Conf

/**
 * @author tomerb
 * Date: 11/6/13 Time: 12:04 PM
 */
class Subscriber extends Actor with ActorLogging {
  import DistributedPubSubMediator.{ Subscribe, SubscribeAck }
  val mediator = DistributedPubSubExtension(context.system).mediator
  // subscribe to the topic named "topicA"
  mediator ! Subscribe("topicA", self)

  def receive = {
    case SubscribeAck(Subscribe("topicA", `self`)) ⇒
      context become ready
  }

  def ready: Actor.Receive = {
    case s: String ⇒
      val msg = s + " [Received at] %s".format(Conf.nettyPort)
      log.info(msg)
  }
}
