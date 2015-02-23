package no.mesan.frisk.db

/**
 * @author Knut Esten Melandsø Nekså
 */


import java.net.URI

import no.mesan.frisk.config.Configuration
import org.apache.commons.dbcp2.BasicDataSource
import org.h2.tools.Server

import scala.slick.driver.PostgresDriver.simple._


object Db extends Configuration{

  val database: Database = {
    val dataSource = new BasicDataSource()

    try {
      val dbUri = new URI(System.getenv("DATABASE_URL"))
      val dbUrl = "jdbc:postgresql://" + dbUri.getHost + dbUri.getPath
      dataSource.setDriverClassName("org.postgresql.Driver")
      
      dataSource.setUrl(dbUrl)
      dataSource.setUsername(dbUri.getUserInfo().split(":")(0))
      dataSource.setPassword(dbUri.getUserInfo().split(":")(1))
    }
    catch {
      case e: NullPointerException =>
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");

    }
    Database.forDataSource(dataSource)
  }

  Server.createTcpServer().start()
  Server.createWebServer().start()
}
