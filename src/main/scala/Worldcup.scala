import Parser._
import Utils.getWcData

case class Match(year: Int, winner: String, against: String, result: String, location: String)

object Worldcup {
  /** Prints the winner, the runner up and the result of the given match.
    *
    * @param m The given match.
    */
  private def prettyPrintWinner(m: Option[Match]): Unit = {
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

  /** Prints the winner, the runner up and the result of the given match.
    *
    * @param m The given match.
    */
  private def prettyPrintTable(m: Option[Match]): Unit = {
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

  /** Group the given country by its name.
    * "West Germany" and "Germany" are considered the same.
    */
  private def groupTeams(m: Match) = {
    if (m.winner.toLowerCase.contains("germany")) "Germany" else m.winner
  }

  /** Prints total winning years for each team. */
  private def prettyPrintTable(table: Map[String, List[Int]]): Unit = {
    table.toSeq.sortBy(-_._2.length) foreach {
      case (team, years) =>
        println(f"$team%-10s 🏆 ${years.length} times (${years.mkString(", ")})")
    }
  }

  def main(args: Array[String]): Unit = {
    parser.parse(args, Config()) match {
      case Some(config) =>
        config match {
          case c if c.year != -1 =>
            prettyPrintWinner(getWcData.find(_.year == c.year))
          case c if c.cups != "" =>
            println(getWcData.count(_.winner.toLowerCase contains c.cups))
          case _ =>
            prettyPrintTable(getWcData.groupBy(groupTeams).mapValues(_.map(_.year)))
        }
      case None =>
    }
  }
}
