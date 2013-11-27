package org.example.akka.cluster

import akka.contrib.pattern.{DistributedPubSubExtension, DistributedPubSubMediator}
import akka.actor.{ActorRef, Actor}
import java.util.Date
import org.example.akka.cluster.conf.Conf

/**
 * @author tomerb
 * Date: 11/7/13 Time: 3:54 PM
 */
class Publisher extends Actor {
  import DistributedPubSubMediator.Publish
  val mediator = actiateExtension

  def actiateExtension: ActorRef = DistributedPubSubExtension(context.system).mediator

  def receive = {
    case PublishMessage ⇒
      mediator ! Publish("topicA", "ActorMesage [" + Conf.nettyPort + "] -->")
  }
}
