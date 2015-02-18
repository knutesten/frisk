package no.mesan.frisk.db

import no.mesan.frisk.model.frisk.project.Projects

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable


/**
 * @author Simen Wold Anderson
 */
object ProjectDao {

  val projects = TableQuery[Projects]

  def create() = Db.database.withSession { implicit session =>
    if(MTable.getTables("project").list.isEmpty) {
      projects.ddl.create
    }
  }
  
}
