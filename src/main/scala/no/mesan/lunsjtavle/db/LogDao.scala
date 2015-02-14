package no.mesan.lunsjtavle.db

import java.sql.Timestamp

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
    println("\n\nyooooooooooooo\n\n")
    println("\n\n" + log.date + "\n\n")

    if (log.date == None) {
      println("\n\nhellooooooooooooo\n\n")
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
