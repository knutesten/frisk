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
            val url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%3D%22" + 
              ticker + "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback="
            val result = scala.io.Source.fromURL(url).mkString
            result
          }
        }
      }
    }
  }
  
}

