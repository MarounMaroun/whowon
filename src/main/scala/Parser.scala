import Utils.getWcData
import scopt.OptionParser

case class Config(year: Int = -1, cups: String = "")

object Parser {
  /** Checks if a world cup occurred in the given year.
    *
    * @param year The given year.
    * @return true if WC occurred, false otherwise.
    */
  private def isWorldCupYear(year: Int): Boolean = {
    year % 4 == 2 && year >= 1930 && year <= 2018
  }

  val parser: OptionParser[Config] = new scopt.OptionParser[Config]("whowon") {
    opt[Int]('y', "year").action {
      (x, c) => c.copy(year = x)
    }.validate { year =>
      if (isWorldCupYear(year)) success else failure(s"No world cup occurred on $year")
    }.text("year of the world cup")

    opt[String]('c', "cups").action {
      (x, c) => c.copy(cups = x)
    }.validate { team =>
      getWcData.find(t => t.winner.equalsIgnoreCase(team)|| t.against.equalsIgnoreCase(team)) match {
        case Some(_) => success
        case _ => failure(s"$team didn't participate at any known world cup")
      }
    }.text("team you want to know how many times won the world cup")
  }
}
