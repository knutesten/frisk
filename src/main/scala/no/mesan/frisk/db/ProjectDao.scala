package no.mesan.frisk.db

import no.mesan.frisk.model.frisk.project.{Project, Projects}

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
  
  def all: List[Project] = Db.database.withSession { implicit session =>
    projects.list
  }

  def insert(project: Project) = Db.database.withSession { implicit session =>
    projects += project
  }
}
