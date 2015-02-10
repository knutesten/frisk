package no.mesan.lunsjtavle.actors.routes

import akka.actor.{Actor, Props}
import no.mesan.lunsjtavle.db.UserDao
import no.mesan.lunsjtavle.model.user.User
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

/**
 * @author Knut Esten Melandsø Nekså
 */

object UserRoute {
  def props = Props(new UserRoute)
}

class UserRoute extends Actor with UserRouteTrait {
  def actorRefFactory = context

  def receive = runRoute(user)
}

trait UserRouteTrait extends HttpService with SprayJsonSupport {
  import no.mesan.lunsjtavle.model.user.UserJsonProtocol._

  val user = {
    get {
       pathEnd {
        complete(UserDao.all)
      } ~ path(IntNumber) { id =>
        complete(UserDao.findById(id))
      } ~ path(Segment) { firstName =>
        complete(UserDao.findByName(firstName))
      }
    } ~
    post {
      entity(as[User]) { user =>
        UserDao.insert(user)
        complete("REG")
      }
    }
  }
}
