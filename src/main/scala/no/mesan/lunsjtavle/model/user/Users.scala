package no.mesan.lunsjtavle.model.user

import spray.json.DefaultJsonProtocol

import scala.slick.driver.H2Driver.simple._

/**
 * @author Knut Esten Melandsø Nekså
 */

case class User(id: Option[Int] = None, firstname: String, lastname: String, password: String, username: String)

object UserJsonProtocol extends DefaultJsonProtocol {
  implicit val userFormat = jsonFormat5(User)
}

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def firstName = column[String]("first_name", O.NotNull)
  def lastName = column[String]("last_name", O.NotNull)
  def password = column[String]("password", O.NotNull)
  def username = column[String]("username", O.NotNull)

  def * = (id.?, firstName, lastName, password, username) <> (User.tupled, User.unapply)
}