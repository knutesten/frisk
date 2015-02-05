package no.mesan.lunsjtavle.model.user

import spray.json.DefaultJsonProtocol

import scala.slick.driver.H2Driver.simple._


/**
 * @author Knut Esten Melandsø Nekså
 */

case class User(firstname: String, lastname: String, password: String, username: String, id: Option[Int] = None)

object UserJsonProtocol extends DefaultJsonProtocol {
  implicit val userFormat = jsonFormat5(User)
}

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def firstname = column[String]("firstname", O.NotNull)
  def lastname = column[String]("lastname", O.NotNull)
  def password = column[String]("passord", O.NotNull)
  def username = column[String]("username", O.NotNull)


  def * = (firstname, lastname, password, username, id.?) <> (User.tupled, User.unapply)
}