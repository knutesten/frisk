package no.mesan.frisk.actors.routes

import akka.actor.{Actor, ActorRef, Props}
import no.mesan.frisk.util.CORSSupport
import spray.http._
import spray.routing.HttpService

/**
 * @author Knut Esten Melandsø Nekså
 */

object ApiRoute {
  def props(userRoute: ActorRef, 
            flavourRoute: ActorRef, 
            consumeTypeRoute: ActorRef,
            logRoute: ActorRef,
            projectRoute: ActorRef) = Props(new ApiRoute(userRoute, flavourRoute, consumeTypeRoute, logRoute, projectRoute))
}

class ApiRoute(userRoute: ActorRef, 
               flavourRoute: ActorRef, 
               consumeTypeRoute: ActorRef, 
               logRoute: ActorRef, projectRoute: ActorRef) extends Actor with HttpService with CORSSupport{
  
  def actorRefFactory = context

  def receive = runRoute {
    cors {
      pathPrefix("api") {
        pathPrefix("user") { ctx => userRoute ! ctx} ~
          pathPrefix("flavour") { ctx => flavourRoute ! ctx} ~
          pathPrefix("consume-type") { ctx => consumeTypeRoute ! ctx} ~
          pathPrefix("log") { ctx => logRoute ! ctx} ~
          pathPrefix("project") { ctx => projectRoute ! ctx}
      } ~ 
      getFromResourceDirectory("app") ~
      pathSingleSlash {
        redirect("/index.html", StatusCodes.Found)
      }
    }
  }
}