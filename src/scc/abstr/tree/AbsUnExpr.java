package scc.abstr.tree;

import scc.*;
import scc.abstr.*;

/**
 * Unarni izraz.
 *
 * @author sliva
 */
public class AbsUnExpr extends AbsExpr {

	public static final int ADD = 0;
	public static final int SUB = 1;
	public static final int MEM = 2; // (prefix  ^)
	public static final int VAL = 3; // (postfix ^)
	public static final int NOT = 4;
	public static final int BIT_NOT = 5;
	public static final int INC = 6;
	public static final int DEC = 7;

	/** Operator. */
	public final int oper;

	/** Podizraz. */
	public final AbsExpr expr;

	/**
	 * Ustvari nov binarni izraz.
	 *
	 * @param pos
	 *            Polozaj stavcne oblike tega drevesa.
	 * @param oper
	 *            Operator.
	 * @param expr
	 *            Podizraz.
	 */
	public AbsUnExpr(Position pos, int oper, AbsExpr expr) {
		super(pos);
		this.oper = oper;
		this.expr = expr;
	}

	@Override public void accept(Visitor visitor) { visitor.visit(this); }

}
