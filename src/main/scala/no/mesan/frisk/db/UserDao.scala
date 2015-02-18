package no.mesan.frisk.db

import no.mesan.frisk.model.user.{User, Users}

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable


/**
 * @author Knut Esten Melandsø Nekså
 */
object UserDao {
  val users = TableQuery[Users]

  def create() = Db.database.withSession { implicit session =>
    if(MTable.getTables("user").list.isEmpty) {
      users.ddl.create
    }
  }

  def insert(user: User) = Db.database.withSession { implicit session =>
    users += user
  }

  def findById(id: Int): Option[User] = Db.database.withSession { implicit session =>
    val byId = users.findBy(_.id)
    byId(id).list.headOption
  }

  def findByName(firstName: String): Option[User] = Db.database.withSession { implicit session =>
    val byName = users.filter(_.firstName === firstName)
    byName.list.headOption
  }

  def all : List[User] = Db.database.withSession { implicit session =>
    users.list
  }
}
