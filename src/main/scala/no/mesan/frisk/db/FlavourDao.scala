package no.mesan.frisk.db

import no.mesan.frisk.model.frisk.flavour.{Flavour, Flavours}

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable

/**
 * @author Simen Wold Anderson
 */
object FlavourDao {
  val flavours = TableQuery[Flavours]

  def create() = Db.database.withSession { implicit session =>
    if(MTable.getTables("flavour").list.isEmpty) {
      flavours.ddl.create
    }
  }

  def insert(flavour: Flavour) = Db.database.withSession { implicit session =>
    flavours += flavour
  }

  def all : List[Flavour] = Db.database.withSession { implicit session =>
//    if(!MTable.getTables("flavours").list.isEmpty) {
      flavours.list
//    }

//    Nil
  }
}
