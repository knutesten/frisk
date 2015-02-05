package no.mesan.lunsjtavle

import akka.actor.ActorSystem
import akka.io.IO
import no.mesan.lunsjtavle.actors.routes.{ApiRoute, UserRoute}
import no.mesan.lunsjtavle.db.{UserDao, H2}
import no.mesan.lunsjtavle.model.{User, Users}
import spray.can.Http

import scala.slick.driver.H2Driver.simple._


/**
 * @author Knut Esten Melandsø Nekså
 */
object Boot extends App {
  UserDao.create()
  UserDao.insert(User("Knut"))
  UserDao.insert(User("Anders"))
  UserDao.insert(User("Simen"))
  UserDao.insert(User("Mikkel"))

  implicit val system = ActorSystem("lunsjtavle-actor-system")

  val userRoute = system.actorOf(UserRoute.props, "user-route")
  val apiRoute = system.actorOf(ApiRoute.props(userRoute), "api-route")

  IO(Http) ! Http.Bind(apiRoute, interface = "localhost", port = 8080)
}
