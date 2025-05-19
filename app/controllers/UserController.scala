package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def getUsers: Action[AnyContent] = Action {
    val users = Json.arr(
      Json.obj("id" -> 1, "name" -> "Anjali"),
      Json.obj("id" -> 2, "name" -> "Chay")
    )
    Ok(users)
  }

  def createUser: Action[JsValue] = Action(parse.json) { request =>
    (request.body \ "name").asOpt[String] match {
      case Some(name) =>
        val user = Json.obj("id" -> 3, "name" -> name)
        Created(user)
      case None =>
        BadRequest(Json.obj("error" -> "'name' field is required"))
    }
  }

}
