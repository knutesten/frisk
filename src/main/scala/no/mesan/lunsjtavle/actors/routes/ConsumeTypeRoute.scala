package no.mesan.lunsjtavle.actors.routes

import akka.actor.{Actor, Props}
import no.mesan.lunsjtavle.db.ConsumeTypeDao
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

/**
 * @author Simen Wold Anderson
 */
object ConsumeTypeRoute {
  def props = Props(new ConsumeTypeRoute)
}

class ConsumeTypeRoute extends Actor with ConsumeTypeRouteTrait {
  def actorRefFactory = context
  
  def receive = runRoute(consumeType)
}

trait ConsumeTypeRouteTrait extends HttpService with SprayJsonSupport {
  import no.mesan.lunsjtavle.model.frisk.consumeType.ConsumeTypeJsonProtocol._

  val consumeType = {
    get {
      pathEnd {
        complete(ConsumeTypeDao.all)
      } ~ path(IntNumber) { id =>
        complete("by id")
      }
    } ~
    post {
      complete("post")
    }
  }
}
