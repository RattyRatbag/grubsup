package duxitservices.grubsup.gui

import swing._
import swing.BorderPanel.Position
import duxitservices.grubsup.entities.Picture
import javax.swing.ImageIcon

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

class PicturePanel extends BorderPanel {
  val pictureList = new ListView[Picture]

  add(new FlowPanel {
    contents += new Button {
      action = Action.apply("") {
        // Add picture
      }
      icon = new ImageIcon("images/Add.png")
    }
    contents += new Button {
      action = Action.apply("") {
        // Remove selected picture
      }
      icon = new ImageIcon("images/Delete.png")
    }
  }, Position.North)

  add(new ScrollPane(pictureList) {
    horizontalScrollBarPolicy = ScrollPane.BarPolicy.Never
  }, Position.Center)
}