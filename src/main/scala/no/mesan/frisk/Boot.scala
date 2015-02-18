package no.mesan.frisk

import akka.actor.ActorSystem
import akka.io.IO
import no.mesan.frisk.actors.routes._
import no.mesan.frisk.config.Configuration
import no.mesan.frisk.db._
import spray.can.Http

/**
 * @author Knut Esten Melandsø Nekså
 * @author Simen Wold Anderson
 */
object Boot extends App with Configuration {
  UserDao.create()
  ProjectDao.create()
  FlavourDao.create()
  ConsumeTypeDao.create()
  LogDao.create()
  UserProjectDao.create()

  
  implicit val system = ActorSystem("frisk-actor-system")

  val flavourRoute = system.actorOf(FlavourRoute.props, "flavour-route")
  val userRoute = system.actorOf(UserRoute.props, "user-route")
  val consumeTypeRoute = system.actorOf(ConsumeTypeRoute.props, "consume-type-route")
  val logRoute = system.actorOf(LogRoute.props, "log-route")

  val apiRoute = system.actorOf(ApiRoute.props(userRoute, flavourRoute, consumeTypeRoute, logRoute), "api-route")

  IO(Http) ! Http.Bind(apiRoute, serviceHost, servicePort)
}