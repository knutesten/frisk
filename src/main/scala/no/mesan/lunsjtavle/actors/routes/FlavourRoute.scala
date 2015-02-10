package no.mesan.lunsjtavle.actors.routes

import akka.actor.{Actor, Props}
import no.mesan.lunsjtavle.db.FlavourDao
import no.mesan.lunsjtavle.model.frisk.flavour.{Flavour, FlavourJsonProtocol}
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

/**
 * @author Simen Wold Anderson
 */

object FlavourRoute {
  def props = Props(new FlavourRoute)
}

class FlavourRoute extends Actor with FlavourRouteTrait {
  def actorRefFactory = context

  def receive = runRoute(flavour)
}

trait FlavourRouteTrait extends HttpService with SprayJsonSupport {
  import no.mesan.lunsjtavle.model.frisk.flavour.FlavourJsonProtocol._

  val flavour = {
    get {
      pathEnd {
        complete(FlavourDao.all)
      }
    } ~
    post {
      entity(as[Flavour]) { flavour =>
        FlavourDao.insert(flavour)
        complete("Flavour was created")
      }
    }
  }

}