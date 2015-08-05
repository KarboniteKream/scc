package scc.abstr.tree;

import scc.*;
import scc.abstr.*;

/**
 * Zanka brez eksplicitnega stevca.
 *
 * @author sliva
 */
public class AbsWhile extends AbsExpr {

	/** Pogoj. */
	public final AbsExpr cond;

	/** Jedro zanke. */
	public final AbsExpr body;

	/**
	 * Ustvari novo zanko brez eksplicitnega stevca.
	 *
	 * @param pos
	 *            Polozaj stavcne oblike tega drevesa.
	 * @param cond
	 *            Pogoj.
	 * @param body
	 *            Jedro zanke.
	 */
	public AbsWhile(Position pos, AbsExpr cond, AbsExpr body) {
		super(pos);
		this.cond = cond;
		this.body = body;
	}

	@Override public void accept(Visitor visitor) { visitor.visit(this); }

}
