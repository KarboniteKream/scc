package scc.abstr.tree;

import scc.*;
import scc.abstr.*;

/**
 * Kratki pogojni stavek.
 *
 * @author sliva
 */
public class AbsIf extends AbsExpr {

	/** Pogoj. */
	public final AbsExpr cond;

	/** Pozitivna veja. */
	public final AbsExpr thenBody;

	/**
	 * Ustvari nov kratki pogojni stavek.
	 *
	 * @param pos
	 *            Polozaj stavcne oblike tega drevesa.
	 * @param cond
	 *            Pogoj.
	 * @param thenBody
	 *            Pozitivna veja.
	 */
	public AbsIf(Position pos, AbsExpr cond, AbsExpr thenBody) {
		super(pos);
		this.cond = cond;
		this.thenBody = thenBody;
	}

	@Override public void accept(Visitor visitor) { visitor.visit(this); }

}
