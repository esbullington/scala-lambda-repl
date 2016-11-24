package repl

import lambdaparser.LambdaParser

object LambdaREPL {

  val parser = new LambdaParser
  import parser.{Success, NoSuccess}

  def main(args: Array[String]) = loop()

  def loop() {
    while (true) {
      val exprSrc = scala.io.StdIn.readLine("λ> ")
      if (!(exprSrc startsWith ":")) {
				parser.parse(exprSrc) match {
					case Success(expr, _) => println("Parsed: " + expr)
					case err: NoSuccess   => println(err)
				}
      } else if (handleCommandOrQuit(exprSrc substring 1)) {
        // if handle command returns false
        // return and leave repl
        return ()
      }
    }
  }

  def handleCommandOrQuit(input: String): Boolean = input.split(" ", 2) match {
    case Array("quit" | "q") => true
    case cmd =>
      println("Unknown command: " + (cmd mkString " "))
      false
  }

}
