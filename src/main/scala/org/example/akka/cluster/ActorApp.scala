package org.example.akka.cluster

import akka.actor.{ActorRef, ActorSystem}
import org.scalatra.{AsyncResult, FutureSupport, ScalatraServlet}
import scala.concurrent.ExecutionContext
import akka.util.Timeout
import scala.concurrent.duration._
import java.util.Date

/**
 * @author tomerb
 * Date: 11/6/13 Time: 12:57 PM
 */
class ActorApp(system:ActorSystem, publisher:ActorRef) extends ScalatraServlet with FutureSupport {

  import _root_.akka.pattern.ask
  implicit val timeout = Timeout(10 seconds)
  protected implicit def executor: ExecutionContext = system.dispatcher

  get("/publish") {
    publisher ! PublishMessage
  }
}
