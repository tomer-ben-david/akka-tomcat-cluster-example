import akka.actor._
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.actor.{Props, ActorSystem}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.ClusterDomainEvent
import javax.servlet.ServletContext
import org.example.akka.cluster._
import org.example.akka.cluster.conf.Conf
import org.scalatra.LifeCycle

/**
 * @author tomerb
 * Date: 11/6/13 Time: 12:39 PM
 */
class ScalatraBootstrap extends LifeCycle {

  // Create an Akka system
  val system = ActorSystem("ClusterSystem")
  val subscriber = system.actorOf(Props[Subscriber], "subscriber-%s".format(Conf.nettyPort))
  val publisher = system.actorOf(Props[Publisher], "publisher-%s".format(Conf.nettyPort))
  val clusterListener = system.actorOf(Props[SimpleClusterListener], name = "clusterListener")

  Cluster(system).subscribe(clusterListener, classOf[ClusterDomainEvent])

  override def init(context: ServletContext) {
    println("starting")
    context.mount(new ActorApp(system, publisher), "/*")
  }

  override def destroy(context:ServletContext) {
    system.shutdown()
  }

}
