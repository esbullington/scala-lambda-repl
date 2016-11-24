package prettyprinter

import expr._

class PrettyPrinter {
  def apply(expr: Expr): String = expr match {
    case Lambda(arg, body) => p"λ$arg.$body"
    case Apply(fun, arg)   => p"$fun $arg"
    case Var(name)         => s"$name"
  }

  implicit class PrettyPrinting(val sc: StringContext) {
    def p(args: Expr*) = sc.s((args map parensIfNeeded):_*)
  }

  def parensIfNeeded(expr: Expr) = expr match {
    case Var(name) => name
    case _         => "(" + apply(expr) + ")"
  }

}
