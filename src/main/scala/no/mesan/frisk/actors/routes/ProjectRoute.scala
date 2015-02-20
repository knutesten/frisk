package no.mesan.frisk.actors.routes

import akka.actor.{Actor, Props}
import no.mesan.frisk.db.ProjectDao
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

/**
 * @author Simen Wold Anderson
 */
object ProjectRoute {
  def props = Props(new ProjectRoute)
}

class ProjectRoute extends Actor with ProjectRouteTrait {
  def actorRefFactory = context

  def receive = runRoute(project)
}

trait ProjectRouteTrait extends HttpService with SprayJsonSupport {
  import no.mesan.frisk.model.frisk.project.ProjectJsonProtocol._

  val project = {
    get {
      pathEnd {
        complete(ProjectDao.all)
      }
    } ~
    post {
      complete("Post to project")
    }
  }
}