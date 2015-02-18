package no.mesan.frisk.db

/**
 * @author Knut Esten Melandsø Nekså
 */


import no.mesan.frisk.config.Configuration
import org.apache.commons.dbcp2.BasicDataSource
import org.h2.tools.Server

import scala.slick.driver.H2Driver.simple._


object Db extends Configuration{

  val database: Database = {
    val dataSource = new BasicDataSource
//    dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
//    dataSource.setDriverClassName("org.h2.Driver")
//    dataSource.setUrl("jdbc:mysql://localhost:3306/frisk")
//    dataSource.setDriverClassName("com.mysql.jdbc.Driver")
//    dataSource.setUsername("root")
//    dataSource.setPassword("root")
    dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUsername(dbUser);
    dataSource.setPassword(dbPassword);
    Database.forDataSource(dataSource)
  }

  Server.createTcpServer().start()
  Server.createWebServer().start()
}


object Datasource {

}