package no.mesan.lunsjtavle

import akka.actor.ActorSystem
import akka.io.IO
import no.mesan.lunsjtavle.actors.routes.{ApiRoute, FlavourRoute, UserRoute}
import no.mesan.lunsjtavle.config.Configuration
import no.mesan.lunsjtavle.db.{ConsumeTypeDao, FlavourDao, UserDao}
import no.mesan.lunsjtavle.model.frisk.consumeType.ConsumeType
import no.mesan.lunsjtavle.model.frisk.flavour.Flavour
import no.mesan.lunsjtavle.model.user.User
import spray.can.Http

/**
 * @author Knut Esten Melandsø Nekså
 */
object Boot extends App with Configuration{
  UserDao.create()
  UserDao.insert(User(null, "Knut", "Knuffern", "passord", "knuffern"))
  UserDao.insert(User(null, "Anders", "Playboy", "passord", "playboy"))
  UserDao.insert(User(null, "Simen", "Drusern", "passord", "drusern"))
  UserDao.insert(User(null, "Mikkel", "Mikkelback", "passord", "mikkelback"))

  FlavourDao.create();
  FlavourDao.insert(Flavour(null, "EXTRA STRONG", "Insanely strong frisk"))
  
  ConsumeTypeDao.create();
  ConsumeTypeDao.insert(ConsumeType(null, "Bonusfrisk", 1))

  implicit val system = ActorSystem("lunsjtavle-actor-system")

  val flavourRoute = system.actorOf(FlavourRoute.props, "flavour-route")
  val userRoute = system.actorOf(UserRoute.props, "user-route")

  val apiRoute = system.actorOf(ApiRoute.props(userRoute, flavourRoute), "api-route")

//  val restService = system.actorOf(Props[ApiRoute])

  IO(Http) ! Http.Bind(apiRoute, serviceHost, servicePort)

}