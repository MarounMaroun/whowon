import Parser._
import Utils.getWcData

case class Match(year: Int, winner: String, against: String, result: String, location: String)

object Worldcup {
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
      case None =>
        println(s"World cup was canceled on this year")
    }
  }

  def main(args: Array[String]): Unit = {
    parser.parse(args, Config()) match {
      case Some(config) =>
        config match {
          case c if c.year != -1 =>
            prettyPrint(getWcData.find(_.year == c.year))
          case c if c.cups != "" =>
            println(getWcData.count(_.winner.toLowerCase contains c.cups))
          case _ =>
        }
      case None =>
    }
  }
}
