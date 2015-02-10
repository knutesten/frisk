package no.mesan.lunsjtavle.db

import java.sql.Timestamp

import org.joda.time.DateTime

import scala.slick.driver.H2Driver.simple._

/**
* @author Simen Wold Anderson
*/
trait DateUtils {

  implicit def dateColumnType  =
    MappedColumnType.base[DateTime, Timestamp](
      dt => new Timestamp(dt.getMillis),
      ts => new DateTime(ts.getTime)
    )
}
