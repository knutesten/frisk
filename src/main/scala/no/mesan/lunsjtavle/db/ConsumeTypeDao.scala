package no.mesan.lunsjtavle.db

import no.mesan.lunsjtavle.model.frisk.consumeType.{ConsumeType, ConsumeTypes}

import scala.slick.driver.H2Driver.simple._

/**
 * @author Simen Wold Anderson
 */
object ConsumeTypeDao {
  val consumeTypes = TableQuery[ConsumeTypes]
  
//  val db = Db.database.withSession(implicitly)
  
  def create() = Db.database.withSession { implicit session =>
    consumeTypes.ddl.create
  }
  
  def insert(consumeType: ConsumeType) = Db.database.withSession { implicit session =>
    consumeTypes += consumeType
  }
  
//  def findById(id: Int)(implicit session: Session) = (id = Some(id))

  def all : List[ConsumeType] = Db.database.withSession { implicit session => 
    consumeTypes.list
  }
}