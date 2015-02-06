package no.mesan.lunsjtavle.model.flavour

import spray.json.DefaultJsonProtocol

import scala.slick.driver.H2Driver.simple._

/**
 * @author Simen Wold Anderson
 */

case class Flavour(flavour: String, description: String)

object FlavourJsonProtocol extends DefaultJsonProtocol {
  implicit val flavourFormat = jsonFormat2(Flavour)
}

class Flavours(tag: Tag) extends Table[Flavour](tag, "flavours") {

  val flavour = column[String]("flavour", O.NotNull)
  val description = column[String]("description", O.NotNull)

  def * = (flavour, description) <> (Flavour.tupled, Flavour.unapply)
}
