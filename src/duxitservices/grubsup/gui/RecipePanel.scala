package duxitservices.grubsup.gui

import swing.GridBagPanel.Fill
import swing._

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

class RecipePanel extends GridBagPanel {
  val c = new Constraints {
    weightx = 0
    weighty = 0
    ipadx = 5
    ipady = 5
    fill = Fill.Both
  }
  val title = new TextField
  val ingredients = new TextArea
  val method = new TextArea

  c.gridx = 0
  c.gridy = 0
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
  layout(new ScrollPane(ingredients)) = c

  c.gridy += 1
  c.gridx = 0
  c.weightx = 0
  layout(new Label("Method")) = c
  c.gridx += 1
  c.weightx = 1
  layout(new ScrollPane(method)) = c

  def clear() {
    title.text = ""
    ingredients.text = ""
    method.text = ""
  }
}