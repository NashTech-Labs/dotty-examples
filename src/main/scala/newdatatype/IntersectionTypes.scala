
/**
 * Intersection Types: https://dotty.epfl.ch/docs/reference/new-types/intersection-types.html
 */

@main def IntersectionTypes: Unit = {

  def handleResponse(r: ResponseData & ErrorHandling): Option[String] = {
    val err: ErrorHandling = r
    val response: ResponseData = r

    if (!err.success) {
      err.errorCode
    } else {
      response.data
    }
  }

  class SuccessResponse extends ResponseData with ErrorHandling {
    override def success: Boolean = true

    override def errorCode: Option[String] = None

    override def data: Option[String] = Some("I am an example of Intersection type in Dotty")

    override def whoAmI: ResponseData & ErrorHandling = this
  }

  class FailureResponse extends ErrorHandling with ResponseData {
    override def success: Boolean = false

    override def errorCode: Option[String] = Some("404!!! Not Found")

    override def data: Option[String] = None

    override def whoAmI: ResponseData & ErrorHandling = this
  }

  val successResponse = new SuccessResponse
  val failureResponse = new FailureResponse


  println(handleResponse(successResponse))

  println(handleResponse(failureResponse))

}

sealed trait ErrorHandling {
  def success: Boolean

  def errorCode: Option[String]

  def whoAmI: ErrorHandling
}

sealed trait ResponseData {
  def data: Option[String]

  def whoAmI: ResponseData
}