package no.mesan.lunsjtavle

import akka.actor.ActorSystem
import akka.io.IO
import no.mesan.lunsjtavle.actors.routes.{ApiRoute, UserRoute}
import no.mesan.lunsjtavle.db.UserDao
import no.mesan.lunsjtavle.model.user.User
import spray.can.Http


/**
 * @author Knut Esten Melandsø Nekså
 */
object Boot extends App {
  UserDao.create()
  UserDao.insert(User("Knut", "Knuffern", "passord", "knuffern"))
  UserDao.insert(User("Anders", "Playboy", "passord", "playboy"))
  UserDao.insert(User("Simen", "Drusern", "passord", "drusern"))
  UserDao.insert(User("Mikkel", "Mikkelback", "passord", "mikkelback"))

  implicit val system = ActorSystem("lunsjtavle-actor-system")

  val userRoute = system.actorOf(UserRoute.props, "user-route")
  val apiRoute = system.actorOf(ApiRoute.props(userRoute), "api-route")

  IO(Http) ! Http.Bind(apiRoute, interface = "localhost", port = 8080)
}
