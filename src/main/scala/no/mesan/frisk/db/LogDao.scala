package no.mesan.frisk.db

import java.sql.Timestamp

import no.mesan.frisk.model.frisk.log.{Log, Logs}

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable

/**
 * @author Simen Wold Anderson
 */
object LogDao {

  val logs = TableQuery[Logs]

  def create() = Db.database.withSession { implicit session =>
    if(MTable.getTables("consume_log").list.isEmpty) {
      logs.ddl.create
    }
  }

  def insert(log: Log) = Db.database.withSession { implicit session =>
    if (log.date == None) {
      val currentTime: Timestamp = new Timestamp(System.currentTimeMillis())
      val newLogEntry = log.copy(date = Option(currentTime))
      logs += newLogEntry
    } else {
      logs += log
    }
  }

  def all : List[Log] = Db.database.withSession { implicit session =>
//    val logOut = (l:Logs, u:Users) => l.userId === u.id
//    
////    val log = Query(Logs).filter(_.id === 1)
    
    logs.list
  }
}
