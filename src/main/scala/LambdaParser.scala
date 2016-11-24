package lambdaparser

import scala.util.parsing.combinator.syntactical.StdTokenParsers
import scala.util.parsing.combinator.lexical.StdLexical
import scala.util.parsing.combinator.PackratParsers
import prettyprinter.PrettyPrinter
import expr._

class LambdaLexer extends StdLexical {
  override def letter = elem("letter", c => c.isLetter && c != 'λ')
}

class LambdaParser extends StdTokenParsers with PackratParsers {
  type Tokens = StdLexical
  val lexical = new LambdaLexer
  lexical.delimiters ++= Seq("λ", ".", "(", ")")

  lazy val expr: PackratParser[Expr]         = lambda | application | variable | parens
  lazy val lambda: PackratParser[Lambda]     = "λ" ~> variable ~ "." ~ expr ^^
                                   { case v ~ "." ~ e  => Lambda(v, e) }
  lazy val application: PackratParser[Apply] = expr ~ expr ^^
                                   { case left ~ right => Apply(left, right) }
  lazy val variable: PackratParser[Var]      = ident ^^ Var
  lazy val parens: PackratParser[Expr]       = "(" ~> expr <~ ")"

	def parse(source: String): ParseResult[Expr] = {
		val tokens = new lexical.Scanner(source)
		phrase(expr)(tokens)
	}

}

