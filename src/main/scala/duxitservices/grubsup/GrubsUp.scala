package duxitservices.grubsup

import gui.{PicturePanel, RecipeList, RecipePanel}
import swing._
import javax.swing.UIManager

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

object GrubsUp extends SimpleSwingApplication {
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)

  lazy val ui = new BorderPanel {
    val recipeList = new RecipeList
    add(new ScrollPane(recipeList) {
      horizontalScrollBarPolicy = ScrollPane.BarPolicy.Never
    }, BorderPanel.Position.West)

    val recipePanel = new RecipePanel
    add(recipePanel, BorderPanel.Position.Center)

    val picturePanel = new PicturePanel
    add(picturePanel, BorderPanel.Position.East)
  }

  def top = new MainFrame {
    contents = ui
    title = "Grubs Up! v0.3"
  }
}