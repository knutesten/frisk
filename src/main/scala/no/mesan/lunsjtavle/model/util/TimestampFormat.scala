package no.mesan.lunsjtavle.model.util

import java.sql.Timestamp

import spray.json._

/**
 * @author Knut Esten Melandsø Nekså
 */
trait TimestampFormat {
  implicit object TimestampFormat extends JsonFormat[Timestamp] {
    def write(obj: Timestamp) = JsNumber(obj.getTime)

    def read(json: JsValue) = json match {
      case JsNumber(time) => new Timestamp(time.toLong)
      case _ => throw new DeserializationException("Date expected")
    }
  }
}
