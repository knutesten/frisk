package no.mesan.frisk.db

import java.sql.Timestamp

import no.mesan.frisk.model.frisk.consumeType.ConsumeTypes
import no.mesan.frisk.model.frisk.flavour.Flavours
import no.mesan.frisk.model.frisk.log.{Log, Logs}
import no.mesan.frisk.model.frisk.project.{UserProjects, Projects}
import no.mesan.frisk.model.user.{User, Users}
import spray.httpx.marshalling.ToResponseMarshallable

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable

/**
 * @author Simen Wold Anderson
 */
object LogDao {
  implicit def timestampOrdering: Ordering[Timestamp] = Ordering.fromLessThan(_.getTime < _.getTime)

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

  def deleteById(logId: Int) = Db.database.withSession { implicit session =>
    logs.filter(_.id === logId).delete
  }

  def all: List[Log] = Db.database.withSession { implicit session =>
    logs.sortBy(_.date.desc).list
  }

  def getFormatedList: List[(Int, String, String, String, String, Timestamp)] = Db.database.withSession { implicit session =>
    val users = TableQuery[Users]
    val projects = TableQuery[Projects]
    val flavour = TableQuery[Flavours]
    val consumeType = TableQuery[ConsumeTypes]
    
    val q = for {
      l <- logs
      u <- users if l.userId === u.id
      p <- projects if l.projectId === p.id
      f <- flavour if l.flavourId === f.id
      c <- consumeType if l.consumeTypeId === c.id
    } yield (l.id, u.username, f.flavour, c.name, p.name, l.date)

    q.sortBy(_._6.desc).list.take(5)
  }
  
  def getFriskCountForProject(projectId: Int): Int = Db.database.withSession { implicit session =>
    val consumeTypes = TableQuery[ConsumeTypes]

    val q = for {
      l <- logs if l.projectId === projectId
      c <- consumeTypes if l.consumeTypeId === c.id
    } yield (c.amount)

        q.list.sum//.fold(0) { (sum, i) => sum + i}
  }

  def getFriskForUserInProject(project: Int): List[(String,Option[Int])] = Db.database.withSession { implicit session =>
    val consumeTypes = TableQuery[ConsumeTypes]
    val users = TableQuery[Users]

    val q = (for {
      l <- logs
      c <- consumeTypes if l.consumeTypeId === c.id
      u <- users if l.userId === u.id
    } yield (u, c)).groupBy(_._1.username)

    val g2 = q.map { case (username, c) =>
      (username, c.map(_._2.amount).sum)
    }

    g2.list
  }
}
