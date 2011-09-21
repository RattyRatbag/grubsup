package duxitservices.grubsup.entities

import collection.mutable.ArrayBuffer

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

case class Recipe (title: String, ingredients: String, method: String, pictures: ArrayBuffer[Picture]) {
  override def toString() = title
}