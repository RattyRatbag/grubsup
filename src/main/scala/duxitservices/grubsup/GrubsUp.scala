package duxitservices.grubsup

import gui.{RecipeList, RecipePanel}
import swing._
import xml.{Node, XML}
import java.io.File

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

object GrubsUp extends SimpleSwingApplication {
  lazy val ui = new BorderPanel {
    add(new FlowPanel {
      contents += new Button(Action.apply("New Recipe") {
        recipePanel.clear()
      })
      contents += new Button(Action.apply("Save Recipe") {
        val file = new File("recipes/" + recipePanel.title.text + ".xml")
        if (file.exists()) {
          if (Dialog.showConfirmation(this, "Are you sure you wish to overwrite " + file.getName, "Overwrite file?") == Dialog.Result.Yes) {
            save(file)
          }
        } else {
          save(file)
        }
        recipeList.rebuildList()
      })
    }, BorderPanel.Position.North)

    val recipeList = new RecipeList
    add(new ScrollPane(recipeList) {
      horizontalScrollBarPolicy = ScrollPane.BarPolicy.Never
    }, BorderPanel.Position.West)

    val recipePanel = new RecipePanel
    add(recipePanel, BorderPanel.Position.Center)

    def generateXml(): Node =
      <recipe>
        <title>
          {recipePanel.title.text}
        </title>
        <ingredients>
          {recipePanel.ingredients.text}
        </ingredients>
        <method>
          {recipePanel.method.text}
        </method>
      </recipe>

    def save(file: File) {
      try {
        XML.save(file.getAbsolutePath, generateXml(), "UTF-8")
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
  }

  def top = new MainFrame {
    contents = ui
    title = "Grubs Up! v0.2"
  }
}