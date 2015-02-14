package no.mesan.lunsjtavle.config

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
 * @author Simen Wold Anderson
 */
trait Configuration {
  /** Application config object. */
  val config = ConfigFactory.load()

  /** Port to start service on. */
  lazy val servicePort = Try(config.getInt("$PORT")).getOrElse(8080)
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse("localhost")

  /** User name used to access database. */
  lazy val dbUser = Try(config.getString("db.user")).toOption.orNull
  lazy val dbPassword = Try(config.getString("db.password")).toOption.orNull
  lazy val dbPort = Try(config.getString("db.port"))
  lazy val dbHost = Try(config.getString("db.host"))
  lazy val dbName = Try(config.getString("db.name"))
}
