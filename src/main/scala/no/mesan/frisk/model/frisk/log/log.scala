package no.mesan.frisk.model.frisk.log

import java.sql.Timestamp

import no.mesan.frisk.model.frisk.consumeType.ConsumeTypes
import no.mesan.frisk.model.frisk.flavour.Flavours
import no.mesan.frisk.model.frisk.project.Projects
import no.mesan.frisk.model.user.Users
import no.mesan.frisk.model.util.TimestampFormat
import spray.json.DefaultJsonProtocol

import scala.slick.driver.PostgresDriver.simple._

/**
 * @author Simen Wold Anderson
 */
case class Log(id: Option[Int],
               var date: Option[Timestamp],
               userId: Int, 
               flavourId: Int, 
               consumeTypeId: Int,
               projectId: Int)

object LogJsonProtocol extends DefaultJsonProtocol with TimestampFormat{
  implicit val friskLogFormat = jsonFormat6(Log)
}

class Logs(tag: Tag) extends Table[Log](tag, "consume_log") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def date = column[Timestamp]("date")
  def userId = column[Int]("user_id", O.NotNull)
  def flavourId = column[Int]("flavour_id")
  def consumeTypeId = column[Int]("consume_type_id", O.NotNull)
  def projectId = column[Int]("project_id")

  def userFk = foreignKey("user_id_fk", userId, TableQuery[Users])(_.id)
  def flavourFk = foreignKey("flavour_id_fk", flavourId, TableQuery[Flavours])(_.id)
  def consumeTypeFk = foreignKey("consume_type_id_fk", consumeTypeId, TableQuery[ConsumeTypes])(_.id)
  def projectFk = foreignKey("project_id_fk", projectId, TableQuery[Projects])(_.id)

  def * = (id.?, date.?, userId, flavourId, consumeTypeId, projectId) <> (Log.tupled, Log.unapply)
}

