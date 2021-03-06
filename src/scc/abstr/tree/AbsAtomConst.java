package scc.abstr.tree;

import scc.*;
import scc.abstr.*;

/**
 * Opis konstante atomarnega tipa.
 *
 * @author sliva
 */
public class AbsAtomConst extends AbsExpr {

	public static final int BOOL = 0;
	public static final int INT = 1;
	public static final int STR = 2;
	public static final int FLOAT = 3;
	public static final int CHAR = 4;
	public static final int VOID = 5;

	/** Tip. */
	public final int type;

	/** Vrednost. */
	public final String value;

	/**
	 * Ustvari konstanto.
	 *
	 * @param pos
	 *            Polozaj stavcne oblike tega drevesa.
	 * @param type
	 *            Konkretni tip.
	 * @param value
	 *            Vrednost.
	 */
	public AbsAtomConst(Position pos, int type, String value) {
		super(pos);
		this.type = type;
		this.value = value;
	}

	@Override public void accept(Visitor visitor) { visitor.visit(this); }

}
