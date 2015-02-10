package no.mesan.lunsjtavle.db

import no.mesan.lunsjtavle.model.frisk.log.{Log, Logs}

import scala.slick.driver.H2Driver.simple._

/**
 * @author Simen Wold Anderson
 */
object LogDao {

  val logs = TableQuery[Logs]

  def create() = Db.database.withSession { implicit session =>
    logs.ddl.create
  }

  def insert(log: Log) = Db.database.withSession { implicit session =>
    logs += log
  }

  def all : List[Log] = Db.database.withSession { implicit session =>
//    val logOut = (l:Logs, u:Users) => l.userId === u.id
//    
////    val log = Query(Logs).filter(_.id === 1)
    
    logs.list
  }
}
