import scala.util.Try

object Program {

  def main(args: Array[String]): Unit = {
    val input = getInput(args)
    val answer = input match {
      case None => None
      case Some(value) => calculate(value)
    }
    println(s"$answer")
  }

  def getInput(args: Array[String]): Option[Input] = {
    args.length match {
      case 2 =>
        val n = Try(args(0).toInt).toOption
          .filter(n => {
            3 <= n && n <= 100
          })

        val a = args(1)
          .split(',')
          .flatMap(x => Try(x.toInt).toOption)
          .filter(ai => {
            1 <= ai && ai <= Math.pow(10, 6)
          })

        (n, a) match {
          case (None, _) => None
          case _ if n.getOrElse(0) != a.length => None
          case _ => Some(Input(n.getOrElse(0), a))
        }
      case _ => None
    }
  }

  def calculate(input: Input): Int = {
    val lines = input.a.sorted

    val triangles = lines.indices.flatMap(i => {
      (i + 1 until lines.length).flatMap(j => {
        (j + 1 until lines.length).filter(k => {
          isTriangle(lines(i), lines(j), lines(k))
        }).map(k => {
          Triangle(lines(i), lines(j), lines(k))
        })
      })
    })

    // 別解
    // 可読性は高いけど、三角形でない辺の組み合わせでも new してしまう
    //    val triangles = {
    //      for {
    //        i <- lines.indices
    //        j <- i + 1 until lines.length
    //        k <- j + 1 until lines.length
    //      } yield Triangle(lines(i), lines(j), lines(k))
    //    }.filter(isTriangle)

    triangles.map(_.totalLength).max
  }

  case class Input(n: Int, a: Array[Int])

  case class Triangle(a: Int, b: Int, c: Int) {
    def totalLength: Int = a + b + c
  }

  def isTriangle(t: Triangle): Boolean = isTriangle(t.a, t.b, t.c)

  def isTriangle(a: Int, b: Int, c: Int): Boolean = (a + b > c) && (b + c > a) && (c + a > b)
}
