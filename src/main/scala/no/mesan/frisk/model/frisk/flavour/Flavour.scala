package no.mesan.frisk.model.frisk.flavour

import spray.json.DefaultJsonProtocol

import scala.slick.driver.PostgresDriver.simple._

/**
 * @author Simen Wold Anderson
 */

case class Flavour(id: Option[Int] = None, flavour: String, description: String)

object FlavourJsonProtocol extends DefaultJsonProtocol {
  implicit val flavourFormat = jsonFormat3(Flavour)
}

class Flavours(tag: Tag) extends Table[Flavour](tag, "flavour") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def flavour = column[String]("flavour", O.NotNull)
  def description = column[String]("description", O.NotNull)

  def * = (id.?, flavour, description) <> (Flavour.tupled, Flavour.unapply)
}
