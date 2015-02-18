package no.mesan.frisk.model.frisk.project

import no.mesan.frisk.model.user.Users
import no.mesan.frisk.model.util.TimestampFormat
import spray.json.DefaultJsonProtocol

import scala.slick.driver.PostgresDriver.simple._

/**
 * @author Simen Wold Anderson
 */

case class UserProject(userId: Int, projectId: Int)

object UserProjectJsonProtocol extends DefaultJsonProtocol with TimestampFormat {
  implicit val userProjectFormat = jsonFormat2(UserProject)
}

class UserProjects(tag: Tag) extends Table[UserProject](tag, "user_project") {

  def userId = column[Int]("user_id")
  def projectId = column[Int]("project_id")

  def userFk = foreignKey("user_id_fk", userId, TableQuery[Users])(_.id)

  def projectFk = foreignKey("project_id_fk", projectId, TableQuery[Projects])(_.id)
  def * = (userId, projectId) <> (UserProject.tupled, UserProject.unapply)

  def userProjectPk = primaryKey("user_project_pk", (userId, projectId))

}
