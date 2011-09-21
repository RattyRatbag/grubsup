package duxitservices.grubsup.gui

import swing._
import swing.BorderPanel.Position
import duxitservices.grubsup.entities.Picture
import javax.swing.ImageIcon
import collection.mutable.ArrayBuffer

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

class PicturePanel extends BorderPanel {
  val pictureListView = new ListView[Picture]
  var pictureList = new ArrayBuffer[Picture]
  val chooser = new FileChooser

  add(new FlowPanel {
    contents += new Button {
      action = Action.apply("") {
        val result = chooser.showOpenDialog(this)
        if (result == FileChooser.Result.Approve) {
          pictureList += new Picture(chooser.selectedFile.getAbsolutePath, pictureListView.listData.length)
          pictureListView.listData = pictureList
          pictureListView.repaint()
        }
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

  add(new ScrollPane(pictureListView) {
    horizontalScrollBarPolicy = ScrollPane.BarPolicy.Never
  }, Position.Center)

  def loadPictures(buffer: ArrayBuffer[Picture]) {
    pictureList = buffer
    pictureListView.listData = pictureList
    pictureListView.repaint()
  }
}