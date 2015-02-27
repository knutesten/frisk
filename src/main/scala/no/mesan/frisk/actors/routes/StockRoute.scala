package no.mesan.frisk.actors.routes

import akka.actor.{Actor, Props}
import spray.http.HttpData
import spray.httpx.SprayJsonSupport
import spray.httpx.marshalling.Marshaller
import spray.routing.HttpService

/**
 * @author Simen Wold Anderson
 */
object StockRoute {
  def props = Props(new StockRoute)
}

class StockRoute extends Actor with StockRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(stock)
}

trait StockRouteTrait extends HttpService with SprayJsonSupport{
  
  val stock = {
    get {
      pathEnd {
        parameter("ticker") { ticker =>
          complete {
            val url = "http://finance.yahoo.com/d/quotes.csv?s=" + ticker + "&f=sb1"
            val result = scala.io.Source.fromURL(url).mkString//.split("/(.*?)/")
            result
          }
        }
      }
    }
  }
  
}

