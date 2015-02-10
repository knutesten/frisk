package no.mesan.lunsjtavle.model.frisk.log

import java.sql.Timestamp

import no.mesan.lunsjtavle.model.frisk.consumeType.ConsumeTypes
import no.mesan.lunsjtavle.model.frisk.flavour.Flavours
import no.mesan.lunsjtavle.model.frisk.project.Projects
import no.mesan.lunsjtavle.model.user.Users
import no.mesan.lunsjtavle.model.util.TimestampFormat
import spray.json.DefaultJsonProtocol

import scala.slick.driver.H2Driver.simple._

/**
 * @author Simen Wold Anderson
 */
case class Log(id: Option[Int],
               date: Timestamp, 
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
  def userId = column[Int]("user_id_pk", O.NotNull)
  def flavourId = column[Int]("flavour_id_pk")
  def consumeTypeId = column[Int]("consume_type_id_pk", O.NotNull)
  def projectId = column[Int]("project_id_pk")

  def user = foreignKey("user_id_fk", userId, TableQuery[Users])(_.id)
  def flavour = foreignKey("flavour_id_fk", flavourId, TableQuery[Flavours])(_.id)
  def consumeType = foreignKey("consume_type_id_fk", consumeTypeId, TableQuery[ConsumeTypes])(_.id)
//  def project = foreignKey("project_id_fk", projectId, TableQuery[Projects])(_.id)

  def * = (id.?, date, userId, flavourId, consumeTypeId, projectId) <> (Log.tupled, Log.unapply)
}

