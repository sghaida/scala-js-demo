package com.sghaida.demo
import org.scalajs.dom
import org.scalajs.dom.ext.SessionStorage
import org.scalajs.dom.raw.Event
import org.scalajs.dom.{document, html}
import dom.html

import java.lang.Math._

object intro {

  /**
   * create HTML elements
   * @param node html node
   * @param pText paragraph text
   * @param hText heading text
   */
  def appendElm(node: dom.Node, pText: String, hText: String): Unit = {

   /* H1 */
    val h1Node = document.createElement("h1")
    h1Node.textContent = hText
    h1Node.setAttribute("style", "color:red;")
    node.appendChild(h1Node)

    /* H2 */
    val h2Node = document.createElement("h2")
    h2Node.textContent = s"yet an other $hText and Ugly!"
    h2Node.setAttribute("style", "text-align:center;")
    node.appendChild(h2Node)

    /* P */
    val parNode = document.createElement("p")
    parNode.textContent = pText
    parNode.setAttribute("class","p-hello")
    parNode.setAttribute("name", "p1")
    node.appendChild(parNode)

    /* Input */
    val input = document.createElement("input")
    input.setAttribute("id", "input-text")
    input.setAttribute("type", "text")
    input.setAttribute("name", "input-text")
    input.setAttribute("style",
      s"""
         |-webkit-box-shadow: inset 2px 2px 2px 0px rgba(0,0,0,0.75);
         |-moz-box-shadow: inset 1px 2px 1px 0px rgba(0,0,0,0.75);
         |box-shadow: inset 1px 1px 2px 0px rgba(0,0,0,0.75);
         |margin: 0 0 0 0;
         |padding: 5px;
         |width: 200px;
         |height: 20px;
         |font-size:18px;
         |overflow:visible;
         |white-space: nowrap;
         |text-overflow: ellipsis;
         |""".stripMargin)

    node.appendChild(input)

    val inputDiv = document.createElement("div")
    inputDiv.setAttribute("id","out-div")
    node.appendChild(inputDiv)

    val canvas: html.Canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    canvas.id = "canvas"
    node.appendChild(canvas)
  }

  /**
   * event handling for mouse enter and mouse over
   * @param node html node
   */
  def eventHandler(node: html.Document): Unit = {
    val elements =  node.getElementsByClassName("p-hello")
    val elm = elements.namedItem("p1")

    val f = document.createElement("font")
    f.setAttribute("size", "34")

    elm.addEventListener("mouseenter", (_: Event) => {
      f.textContent = elm.textContent
      elm.textContent = ""
      elm.setAttribute("style", "color:blue;")
      elm.appendChild(f)
    })

    elm.addEventListener("mouseleave", (_: Event) => {
      elm.textContent = f.textContent
    })
  }

  /**
   * base64 encoder
   * @param node html node
   */
  def responsiveEncoder(node: html.Document): Unit = {

    val in =  node.getElementById("input-text").asInstanceOf[html.Input]
    val out = node.getElementById("out-div")

    in.onkeyup = (_: dom.Event) => {
      out.textContent = dom.window.btoa(in.value)
      out.setAttribute("style",
        s"""
          |-webkit-box-shadow: inset 2px 2px 2px 0px rgba(0,0,0,0.75);
          |-moz-box-shadow: inset 2px 3px 2px 0px rgba(0,0,0,0.75);
          |box-shadow: inset 2px 1px 3px 0px rgba(0,0,0,0.75);
          |margin: 0 0 0 0;
          |padding: 5px;
          |width: ${out.textContent.length*10}px;
          |overflow:visible;
          |white-space: nowrap;
          |text-overflow: ellipsis;
          |""".stripMargin)

    }
  }

  def draw(canvas: html.Canvas): Int = {

    def clear(): Unit = {
      canvas.width = 200
      canvas.height = 200
    }
    clear()

    val brush =
      canvas.getContext("2d")
        .asInstanceOf[dom.CanvasRenderingContext2D]

    def h = canvas.height
    def w = canvas.width

    var x = 0.0
    type Graph = (String, Double => Double)
    val graphs = Seq[Graph](
      ("red", sin),
      ("blue", x => sin(x/16) * sin(x))
    ).zipWithIndex
    dom.window.setInterval(() => {
      x = (x + 1) % w; if (x == 0) SessionStorage.clear()
      for (((color, f), i) <- graphs) {
        val offset = h / 3 * (i + 0.5)
        val y = f(x / w * 10) * h / 15
        brush.fillStyle = color
        brush.fillRect(x, y + offset, 3, 3)
      }
    }, 30)

  }

  def main(args: Array[String]): Unit = {
    println("hello world!")
    // Compare to ["1", "10", "11", "16"].map(parseInt) WTF
    println(List("1", "10", "11", "16").map(_.toInt))

    appendElm(document.body, "Hello World!", "very important message")
    eventHandler(document)
    responsiveEncoder(document)

    val canvas = document.getElementById("canvas").asInstanceOf[html.Canvas]

    draw(canvas)
    canvas
        .asInstanceOf[html.Canvas].getContext("2d")
        .asInstanceOf[dom.CanvasRenderingContext2D]
  }

}

