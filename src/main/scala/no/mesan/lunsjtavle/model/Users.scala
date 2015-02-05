package no.mesan.lunsjtavle.model

import spray.json.DefaultJsonProtocol

import scala.slick.driver.H2Driver.simple._


/**
 * @author Knut Esten Melandsø Nekså
 */

case class User(name: String, id: Option[Int] = None)

object UserJsonProtocol extends DefaultJsonProtocol {
  implicit val userFormat = jsonFormat2(User)
}

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name", O.NotNull)

  def * = (name, id.?) <>(User.tupled, User.unapply)
}