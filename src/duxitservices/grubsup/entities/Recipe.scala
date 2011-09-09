package duxitservices.grubsup.entities

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

case class Recipe (title: String, ingredients: String, method: String) {
  override def toString() = title
}