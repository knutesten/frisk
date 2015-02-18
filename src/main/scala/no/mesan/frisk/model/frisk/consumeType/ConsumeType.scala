package no.mesan.frisk.model.frisk.consumeType

import spray.json.DefaultJsonProtocol

import scala.slick.driver.PostgresDriver.simple._

/**
 * @author Simen Wold Anderson
 */
case class ConsumeType(id: Option[Int] = None, name: String, amount: Int)

object ConsumeTypeJsonProtocol extends DefaultJsonProtocol {
  implicit val friskConsumeTypeFormat = jsonFormat3(ConsumeType)
}

class ConsumeTypes(tag: Tag) extends Table[ConsumeType](tag, "frisk_consume_types") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def amount = column[Int]("amount")

  def * = (id.?, name, amount) <> (ConsumeType.tupled, ConsumeType.unapply)
}
