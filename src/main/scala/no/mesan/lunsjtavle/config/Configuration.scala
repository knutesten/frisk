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
    lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8080)

    /** User name used to access database. */
    lazy val dbUser = Try(config.getString("db.user")).toOption.orNull

    lazy val serviceHost = Try(config.getString("service.host")).getOrElse("localhost")
}
