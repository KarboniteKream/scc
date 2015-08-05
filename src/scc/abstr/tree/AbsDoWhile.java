package scc.abstr.tree;

import scc.*;
import scc.abstr.*;

public class AbsDoWhile extends AbsExpr
{
	public final AbsExpr body;
	public final AbsExpr cond;

	public AbsDoWhile(Position pos, AbsExpr body, AbsExpr cond)
	{
		super(pos);
		this.body = body;
		this.cond = cond;
	}

	@Override public void accept(Visitor visitor) { visitor.visit(this); }
}
