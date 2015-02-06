package no.mesan.lunsjtavle.actors.routes

import akka.actor.{Actor, Props, ActorRef}
import spray.routing.HttpService

/**
 * @author Knut Esten Melandsø Nekså
 */

object ApiRoute {
  def props(userRoute: ActorRef,
            flavourRoute: ActorRef) = Props(new ApiRoute(userRoute, flavourRoute))
}

class ApiRoute(userRoute: ActorRef, flavourRoute: ActorRef) extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute {
    pathPrefix("api") {
      pathPrefix("user") { cxt => userRoute ! cxt } ~
      pathPrefix("flavour") { cxt => flavourRoute ! cxt }
    }
  }
}