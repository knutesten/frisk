package no.mesan.lunsjtavle.actors.routes

import akka.actor.{Actor, Props}
import no.mesan.lunsjtavle.db.LogDao
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

/**
 * @author Simen Wold Anderson
 */
object LogRoute {
  def props = Props(new LogRoute)
}

class LogRoute extends Actor with LogRouteTrait {
  def actorRefFactory = context

  def receive = runRoute(log)
}

trait LogRouteTrait extends HttpService with SprayJsonSupport {
  import no.mesan.lunsjtavle.model.frisk.log.LogJsonProtocol._
  
  val log = {
    get {
      pathEnd {
        complete(LogDao.all)
      }
    } ~
      post {
        complete("post")
      }
  }
}

