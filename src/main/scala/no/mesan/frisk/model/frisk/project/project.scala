package no.mesan.frisk.model.frisk.project

import java.sql.Timestamp

import no.mesan.frisk.model.user.Users
import no.mesan.frisk.model.util.TimestampFormat
import spray.json.DefaultJsonProtocol

import scala.slick.driver.H2Driver.simple._
/**
 * @author Simen Wold Anderson
 */

case class Project(id: Option[Int] = None, 
                   name: String, 
                   userId: Int,
                   startTime: Timestamp,
                   endTime: Timestamp,
                   description: String)

object ProjectJsonProtocol extends DefaultJsonProtocol with TimestampFormat{
  implicit val projectFormat = jsonFormat6(Project)
}

class Projects(tag: Tag) extends Table[Project](tag, "project") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def userId = column[Int]("user_id", O.NotNull)
  def startTime = column[Timestamp]("start_time")
  def endTime = column[Timestamp]("end_time")
  def description = column[String]("description")
  
  def user = foreignKey("user_id_fk", userId, TableQuery[Users])(_.id)
  
  def * = (id.?, name, userId, startTime, endTime, description) <> (Project.tupled, Project.unapply)
}