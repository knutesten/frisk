package no.mesan.lunsjtavle.db

/**
 * @author Knut Esten Melandsø Nekså
 */


import org.apache.commons.dbcp2.BasicDataSource

import scala.slick.driver.H2Driver.simple._


object H2 {
  val database: Database = {
    val dataSource = new BasicDataSource
    dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
    dataSource.setDriverClassName("org.h2.Driver")
    Database.forDataSource(dataSource)
  }
}
