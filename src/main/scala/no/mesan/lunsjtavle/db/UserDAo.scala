package no.mesan.lunsjtavle.db

import no.mesan.lunsjtavle.model.user.{User, Users}

import scala.slick.driver.H2Driver.simple._


/**
 * @author Knut Esten Melandsø Nekså
 */
object UserDao {
  val users = TableQuery[Users]

  def create() = H2.database.withSession { implicit session =>
    users.ddl.create
  }

  def insert(user: User) = H2.database.withSession { implicit session =>
    users += user
  }

  def findById(id: Int): Option[User] = H2.database.withSession { implicit session =>
    val byId = users.findBy(_.id)
    byId(id).list.headOption
  }

  def findByName(firstname: String): Option[User] = H2.database.withSession { implicit session =>
    val byName = users.filter(_.firstname === firstname)
    byName.list.headOption
  }

  def all : List[User] = H2.database.withSession { implicit session =>
    users.list
  }
}
