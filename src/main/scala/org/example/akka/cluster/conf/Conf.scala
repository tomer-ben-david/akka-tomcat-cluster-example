package org.example.akka.cluster.conf

/**
 * @author tomerb
 * Date: 11/16/13 Time: 9:23 PM
 */
object Conf {
  val nettyPort = System.getProperty("akka.remote.netty.tcp.port")
}
