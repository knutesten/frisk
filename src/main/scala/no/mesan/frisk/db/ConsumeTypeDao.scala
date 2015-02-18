package no.mesan.frisk.db

import no.mesan.frisk.model.frisk.consumeType.{ConsumeType, ConsumeTypes}

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable

/**
 * @author Simen Wold Anderson
 */
object ConsumeTypeDao {
  val consumeTypes = TableQuery[ConsumeTypes]
  
  def create() = Db.database.withSession { implicit session =>
    if(MTable.getTables("frisk_consume_type").list.isEmpty) {
      consumeTypes.ddl.create
    }
  }
  
  def insert(consumeType: ConsumeType) = Db.database.withSession { implicit session =>
    consumeTypes += consumeType
  }
  
//  def findById(id: Int)(implicit session: Session) = (id = Some(id))

  def all : List[ConsumeType] = Db.database.withSession { implicit session => 
    consumeTypes.list
  }
}
