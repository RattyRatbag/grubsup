package duxitservices.grubsup.gui

import swing._
import swing.BorderPanel.Position
import duxitservices.grubsup.entities.Picture
import collection.mutable.ArrayBuffer
import javax.swing.{JLabel, ImageIcon}
import javax.imageio.ImageIO
import java.io.File
import java.awt.{Image, Color}

/**
 * @author David Edmonds <edmonds.d.r@gmail.com>
 */

class PicturePanel extends BorderPanel {
  val pictureListView = new ListView[Picture] {
    renderer = new ListView.Renderer[Picture] {
      def componentFor(list: ListView[_], isSelected: Boolean, focused: Boolean, a: Picture, index: Int) = {
        val label = ListView.GenericRenderer.componentFor(list, isSelected, focused, a, index)
        val jLabel = label.peer.asInstanceOf[JLabel]
        jLabel.setText(index.toString)
        val image = ImageIO.read(new File(a.fileName))
        jLabel.setIcon(new ImageIcon(image.getScaledInstance(200, 150, Image.SCALE_SMOOTH)))
        label
      }
    }
  }
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