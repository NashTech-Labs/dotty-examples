/*
* Union Types: https://dotty.epfl.ch/docs/reference/new-types/union-types.html
 */
@main def UnionTypes = {

  case class UserName(name: String) {
    def lookup = println(s"User name is :: ${name}")
  }
  case class Password(hash: String) {
    def lookup = println(s"Password is :: ${hash}")
  }

  def getLoginInfo(loginInfo: UserName | Password): Password | UserName = {
    loginInfo match {
      case u@UserName(_) =>
        u.lookup
        u
      case p@Password(_) =>
        p.lookup
        p
    }
  }

  val loginInfo1: UserName | Password = getLoginInfo(UserName("knoldus"))
  val loginInfo2: Password | UserName = getLoginInfo(Password("knolder"))
  println(s"login1 info is :: ${loginInfo1}")
  println(s"login2 info is :: ${loginInfo2}")

  // Union supports commutative algebric properties

  val value1: UserName | Password = getLoginInfo(UserName("Narayan"))
  val value2: Password | UserName = getLoginInfo(UserName("Narayan"))
  if(value1 == value2) println("Commutative properties satisfy") else println("Commutative properties does not satisfy")

  // It also supports Pattern matching syntax

  val pattern1 = value1 match {
    case _: UserName | Password => println("Pattern Matched")
    case _: Password | UserName => println("Pattern Matched")
  }

  val pattern2 = value2 match {
    case a:(Password | UserName) => println(s"Pattern Matched ${a}")
  }
  //Both pattern1 and pattern2 are equivalent
}
