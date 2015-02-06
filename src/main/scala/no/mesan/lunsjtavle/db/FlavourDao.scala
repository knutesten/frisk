package no.mesan.lunsjtavle.db

import no.mesan.lunsjtavle.model.flavour.{Flavour, Flavours}

import scala.slick.driver.H2Driver.simple._

/**
 * @author Simen Wold Anderson
 */
object FlavourDao {
  val flavours = TableQuery[Flavours]

  def create() = H2.database.withSession { implicit session =>
//    if(MTable.getTables("flavours").list.isEmpty) {
      flavours.ddl.create
//    }
  }

  def insert(flavour: Flavour) = H2.database.withSession { implicit session =>
    flavours += flavour
  }

  def all : List[Flavour] = H2.database.withSession { implicit session =>
//    if(!MTable.getTables("flavours").list.isEmpty) {
      flavours.list
//    }

//    Nil
  }
}
