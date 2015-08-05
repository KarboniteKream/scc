package scc.abstr.tree;

import scc.*;
import scc.abstr.*;

/**
 * Asbtraktno sintaksno drevo.
 *
 * @author sliva
 */
public abstract class AbsTree {

	public final Position position;

	/**
	 * Ustvari novo abstraktno sintaksno drevo.
	 *
	 * @param pos
	 *            Polozaj stavcne oblike tega drevesa.
	 */
	public AbsTree(Position pos) {
		this.position = pos;
	}

	public abstract void accept(Visitor visitor);

}
