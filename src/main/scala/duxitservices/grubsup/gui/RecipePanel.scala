package duxitservices.grubsup.gui

import swing.GridBagPanel.Fill
import swing._
import duxitservices.grubsup.entities.Recipe
import java.io.File
import duxitservices.grubsup.GrubsUp
import xml.{NodeBuffer, XML, Node}
import javax.swing.ImageIcon

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

class RecipePanel extends GridBagPanel {
  val c = new Constraints {
    weightx = 0
    weighty = 0
    ipadx = 10
    ipady = 10
    fill = Fill.Both
  }
  val title = new TextField
  val ingredients = new TextArea {
    columns = 40
    rows = 10
  }
  val method = new TextArea {
    columns = 40
    rows = 10
  }

  c.gridx = 0
  c.gridy = 0
  c.gridwidth = 2
  layout(new FlowPanel {
    contents += new Button {
      action = Action.apply("New Recipe") {
        clear()
      }
      icon = new ImageIcon("images/New.png")
    }
    contents += new Button{
      action = Action.apply("Save Recipe") {
        val file = new File("recipes/" + title.text + ".xml")
        if (file.exists()) {
          if (Dialog.showConfirmation(this, "Are you sure you wish to overwrite " + file.getName, "Overwrite file?") == Dialog.Result.Yes) {
            save(file)
          }
        } else {
          save(file)
        }
        GrubsUp.ui.recipeList.rebuildList()
      }
      icon = new ImageIcon("images/Save.png")
    }
  }) = c

  c.gridx = 0
  c.gridy += 1
  c.gridwidth = 1
  layout(new Label("Recipe Title")) = c
  c.gridx += 1
  c.weightx = 1
  layout(title) = c

  c.gridy += 1
  c.gridx = 0
  c.weightx = 0
  c.weighty = 1
  layout(new Label("Ingredients")) = c
  c.gridx += 1
  c.weightx = 1
  layout(new ScrollPane(ingredients) {
    horizontalScrollBarPolicy = ScrollPane.BarPolicy.Never
  }) = c

  c.gridy += 1
  c.gridx = 0
  c.weightx = 0
  layout(new Label("Method")) = c
  c.gridx += 1
  c.weightx = 1
  layout(new ScrollPane(method) {
    horizontalScrollBarPolicy = ScrollPane.BarPolicy.Never
  }) = c

  def clear() {
    title.text = ""
    ingredients.text = ""
    method.text = ""
  }

  def loadRecipe(recipe: Recipe) {
    title.text = recipe.title
    ingredients.text = recipe.ingredients
    method.text = recipe.method
    GrubsUp.ui.picturePanel.loadPictures(recipe.pictures)
  }

  def generateXml(): Node =
    <recipe>
      <title>
        {title.text}
      </title>
      <ingredients>
        {ingredients.text}
      </ingredients>
      <method>
        {method.text}
      </method>
      <pictures>
        {buildPictures}
      </pictures>
    </recipe>

  def buildPictures = {
    val result = new NodeBuffer
    GrubsUp.ui.picturePanel.pictureList.foreach {
      case pic => result += <picture><fileName>{pic.fileName}</fileName><order>{pic.order}</order></picture>
    }
    result
  }

  def save(file: File) {
    try {
      XML.save(file.getAbsolutePath, generateXml(), "UTF-8")
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}