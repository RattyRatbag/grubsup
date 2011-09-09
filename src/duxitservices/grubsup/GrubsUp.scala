package duxitservices.grubsup

import gui.RecipePanel
import swing.GridBagPanel.Fill
import swing._
import xml.{Node, XML}

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

object GrubsUp extends SimpleSwingApplication {
  lazy val ui = new GridBagPanel {
    val c = new Constraints {
      weightx = 0.5
      weighty = 0
      ipadx = 5
      ipady = 5
      fill = Fill.Both
    }

    c.gridy = 0
    c.gridx = 0
    layout(new FlowPanel {
      contents += new Button(Action.apply("New Recipe") {
        recipePanel.clear()
      })
      contents += new Button(Action.apply("Save Recipe") {
        try{
          XML.save("recipes/" + recipePanel.title.text + ".xml", generateXml())
        } catch {
          case e: Exception => e.printStackTrace()
        }
      })
    }) = c

    c.gridy += 1
    c.weighty = 1
    val recipePanel = new RecipePanel
    layout(recipePanel) = c

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
  }

  def top = new MainFrame {
    contents = ui
    title = "Grubs Up! v0.1"
  }
}