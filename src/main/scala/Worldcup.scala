import Parser._
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.io.Source

case class Match(year: Int, winner: String, against: String, result: String, location: String)

object Worldcup {
  private type WorldCupData = Array[Match]

  private val wcFile = "wc.json"
  private val usage = """
    Usage: worldcup [--year|-y year]
  """

  /** Returns a list of worldcup matches.
    *
    * @return `List[Match]`.
    */
  private def getWcData: List[Match] = {
    val mapper = new ObjectMapper
    mapper.registerModule(DefaultScalaModule)

    val wcJsonContent = Source.fromResource(wcFile).getLines.mkString
    mapper.readValue(wcJsonContent, classOf[WorldCupData]).toList
  }

  /** Prints the winner, the runner up and the result of the given match.
    *
    * @param m The given match.
    */
  private def prettyPrint(m: Option[Match]): Unit = {
    m match {
      case Some(x: Match) =>
        println(
          s"Winner:    ${x.winner}\n" +
          s"Runner Up: ${x.against}\n" +
          s"Result:    ${x.result}"
        )
      case None => println(s"World cup was canceled on this year")
    }
  }

  def main(args: Array[String]): Unit = {
    parser.parse(args, Config()) match {
      case Some(config) =>
        prettyPrint(getWcData.find(_.year == config.year))
      case None =>
    }
  }
}
