import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.io.Source

object Utils {
  type WorldCupData = Array[Match]
  private val wcFile = "wc.json"

  /** Returns a list of worldcup matches.
    *
    * @return `List[Match]`.
    */
  def getWcData: List[Match] = {
    val mapper = new ObjectMapper
    mapper.registerModule(DefaultScalaModule)

    val wcJsonContent = Source.fromResource(wcFile).getLines.mkString
    mapper.readValue(wcJsonContent, classOf[WorldCupData]).toList
  }
}
