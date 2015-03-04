package no.mesan.frisk.actors.routes

import akka.actor.{Actor, Props}
import no.mesan.frisk.db.LogDao
import no.mesan.frisk.model.frisk.log.Log
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
  import no.mesan.frisk.model.frisk.log.LogJsonProtocol._
  
  val log = {
    get {
      path("count" / IntNumber) { id =>
        val c = LogDao.getFriskCountForProject(id)
        complete("Count", c)
      } ~
      path("formatted") {
        complete(LogDao.getFormatedList)
      } ~
      path("user-frisk-count" / IntNumber) { id =>
        complete(LogDao.getFriskForUserInProject(id))
      } ~
      pathEnd {
        complete(LogDao.all)
      }
    } ~
    post {
      pathEnd {
        entity(as[Log]) { log =>
          LogDao.insert(log)
          complete("INSERTED: ", log)
        }
      }
    } ~
    delete {
      path(IntNumber) { id =>
        LogDao.deleteById(id)
        complete("DELETED: ", id)
      }
    }
  }
}

