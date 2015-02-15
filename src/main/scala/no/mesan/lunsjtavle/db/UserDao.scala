package no.mesan.lunsjtavle.db

import no.mesan.lunsjtavle.model.user.{User, Users}

import scala.slick.driver.H2Driver.simple._


/**
 * @author Knut Esten Melandsø Nekså
 */
object UserDao {
  val users = TableQuery[Users]

  def create() = Db.database.withSession { implicit session =>
//    if(MTable.getTables("users").list.isEmpty) {
      users.ddl.create
//    }
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
