package no.mesan.lunsjtavle.actors.routes

import akka.actor.{Actor, ActorRef, Props}
import spray.routing.HttpService

/**
 * @author Knut Esten Melandsø Nekså
 */

object ApiRoute {
  def props(userRoute: ActorRef) = Props(new ApiRoute(userRoute))
}

class ApiRoute(userRoute: ActorRef) extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute {
    pathPrefix("api") {
      pathPrefix("user") { cxt => userRoute ! cxt }
    }
  }
}
