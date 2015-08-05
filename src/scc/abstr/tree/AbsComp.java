package scc.abstr.tree;

import scc.*;
import scc.abstr.*;

public class AbsComp extends AbsDef
{
	public final AbsType type;

	public AbsComp(Position pos, String name, AbsType type)
	{
		super(pos, name);
		this.type = type;
	}

	@Override public void accept(Visitor visitor) { visitor.visit(this); }

}
