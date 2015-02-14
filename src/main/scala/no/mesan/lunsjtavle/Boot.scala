package no.mesan.lunsjtavle

import java.sql.Timestamp

import akka.actor.ActorSystem
import akka.io.IO
import no.mesan.lunsjtavle.actors.routes._
import no.mesan.lunsjtavle.config.Configuration
import no.mesan.lunsjtavle.db.{ConsumeTypeDao, FlavourDao, LogDao, UserDao}
import no.mesan.lunsjtavle.model.frisk.consumeType.ConsumeType
import no.mesan.lunsjtavle.model.frisk.flavour.Flavour
import no.mesan.lunsjtavle.model.frisk.log.Log
import no.mesan.lunsjtavle.model.user.User
import spray.can.Http

/**
 * @author Knut Esten Melandsø Nekså
 * @author Simen Wold Anderson
 */
object Boot extends App with Configuration {
  UserDao.create()
  UserDao.insert(User(None, "Knut", "Knuffern", "passord", "knuffern"))
  UserDao.insert(User(None, "Anders", "Playboy", "passord", "playboy"))
  UserDao.insert(User(None, "Simen", "Drusern", "passord", "drusern"))
  UserDao.insert(User(None, "Mikkel", "Mikkelback", "passord", "mikkelback"))

  FlavourDao.create()
  FlavourDao.insert(Flavour(None, "EXTRA STRONG", "Insanely strong frisk"))
  
  ConsumeTypeDao.create()
  ConsumeTypeDao.insert(ConsumeType(None, "Bonusfrisk", 1))
  
  LogDao.create()
  LogDao.insert(Log(None, None, 1, 1, 1, 1))

  implicit val system = ActorSystem("frisk-actor-system")

  val flavourRoute = system.actorOf(FlavourRoute.props, "flavour-route")
  val userRoute = system.actorOf(UserRoute.props, "user-route")
  val consumeTypeRoute = system.actorOf(ConsumeTypeRoute.props, "consume-type-route")
  val logRoute = system.actorOf(LogRoute.props, "log-route")

  val apiRoute = system.actorOf(ApiRoute.props(userRoute, flavourRoute, consumeTypeRoute, logRoute), "api-route")

//  val restService = system.actorOf(Props[ApiRoute])

  IO(Http) ! Http.Bind(apiRoute, serviceHost, servicePort)

}