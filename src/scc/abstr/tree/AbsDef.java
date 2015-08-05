package scc.abstr.tree;

import scc.*;

public abstract class AbsDef extends AbsTree
{
	public final String name;

	public AbsDef(Position pos, String name)
	{
		super(pos);
		this.name = name;
	}
}
