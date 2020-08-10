/*
* Match Types: https://dotty.epfl.ch/docs/reference/new-types/match-types.html
*/

@main def MatchTyes = {

  import scala.compiletime.ops.int.%

  type HCF[A <: Int, B <: Int] <: Int = B match {
    case 0 => A
    case _ => HCF[B, A % B]
  }

  val hcf: HCF[10, 20] = 10

  println("HCF of 10 & 20 is: " + hcf)

//   val hcf: HCF[10, 20] = 11 --- this statement will not compiles


}