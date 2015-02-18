package no.mesan.frisk.db

import no.mesan.frisk.model.frisk.project.UserProjects

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable


/**
 * @author Simen Wold Anderson
 */
object UserProjectDao {

  val userProjects = TableQuery[UserProjects]

  def create() = Db.database.withSession { implicit session =>
    if(MTable.getTables("user_project").list.isEmpty) {
      userProjects.ddl.create
    }
  }
}
