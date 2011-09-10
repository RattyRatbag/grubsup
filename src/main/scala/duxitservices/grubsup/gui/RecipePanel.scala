package duxitservices.grubsup.gui

import swing.GridBagPanel.Fill
import swing._
import duxitservices.grubsup.entities.Recipe

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
    columns = 80
    rows = 10
  }
  val method = new TextArea {
    columns = 80
    rows = 10
  }

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
  }
}