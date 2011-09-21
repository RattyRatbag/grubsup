package duxitservices.grubsup.gui

import swing.BorderPanel.Position
import java.io.{FilenameFilter, File}
import collection.mutable.ArrayBuffer
import duxitservices.grubsup.GrubsUp
import swing._
import xml.{Elem, XML}
import duxitservices.grubsup.entities.{Picture, Recipe}

/**
 * Panel containing a Recipe List, and also a toolbar for loading/deleting recipes
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

class RecipeList extends BorderPanel {
  val recipeList = new ListView[Recipe](getAllRecipes)

  add(new ScrollPane(recipeList) {
    horizontalScrollBarPolicy = ScrollPane.BarPolicy.Never
  }, Position.Center)

  add(new FlowPanel {
    contents += new Button(Action.apply("Load Recipe") {
      GrubsUp.ui.recipePanel.loadRecipe(recipeList.selection.items.head)
    })
    contents += new Button(Action.apply("Delete Recipe") {
      val file = new File("recipes/" + recipeList.selection.items.head.title + ".xml")
      if (file.exists() && Dialog.showConfirmation(this, "Are you sure you wish to delete " + file.getName, "Delete file?") == Dialog.Result.Yes) {
        file.delete()
      }
      rebuildList()
    })
  }, Position.North)

  def getAllRecipes: Seq[Recipe] = {
    val recipes = new ArrayBuffer[Recipe]
    val recipeFolder = new File("recipes/")
    val files = recipeFolder.listFiles(new FilenameFilter {
      def accept(file: File, fileName: String) = fileName.endsWith(".xml")
    })
    if (files != null) {
      files.foreach {
        case f: File =>
          val xml = XML.loadFile(f)
          recipes += Recipe((xml \ "title").text.trim, (xml \ "ingredients").text.trim, (xml \ "method").text.trim, buildPictureList(xml))
      }
    }
    recipes.sortWith(_.title < _.title)
  }

  def buildPictureList(xml: Elem): ArrayBuffer[Picture] = {
    val pictures = new ArrayBuffer[Picture]
    (xml \\ "picture").foreach {
      case p => pictures += Picture((p \ "fileName").text.trim, (p \ "order").text.trim.toInt)
    }
    pictures
  }

  def rebuildList() {
    recipeList.listData = getAllRecipes
    recipeList.repaint()
  }
}