package no.mesan.frisk.actors.routes

import akka.actor.{Actor, ActorRef, Props}
import spray.routing.HttpService

/**
 * @author Knut Esten Melandsø Nekså
 */

object ApiRoute {
  def props(userRoute: ActorRef,
            flavourRoute: ActorRef, 
            consumeTypeRoute: ActorRef,
            logRoute: ActorRef) = Props(new ApiRoute(userRoute, flavourRoute, consumeTypeRoute, logRoute))
}

class ApiRoute(userRoute: ActorRef, flavourRoute: ActorRef, consumeTypeRoute: ActorRef, logRoute: ActorRef) extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute {
    pathPrefix("api") {
      pathPrefix("user") { ctx => userRoute ! ctx } ~
      pathPrefix("flavour") { ctx => flavourRoute ! ctx } ~
      pathPrefix("consume-type") { ctx => consumeTypeRoute ! ctx } ~
      pathPrefix("log") { ctx => logRoute ! ctx }
    }
  }
}