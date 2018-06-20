import scopt.OptionParser

case class Config(year: Int = -1)

object Parser {
  private def isWorldCupYear(year: Int): Boolean = {
    for (y <- Range(1934, 2018, 4)) {
      if (year == y) return true
    }
    false
  }

  val parser: OptionParser[Config] = new scopt.OptionParser[Config]("worldcup") {
    opt[Int]('y', "year").required.action {
      (x, c) => c.copy(year = x)
    }.validate { year =>
      if (isWorldCupYear(year)) success else failure(s"No world cup occurred on $year")
    }.text("year of the world cup")
  }
}
