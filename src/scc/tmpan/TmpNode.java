package scc.tmpan;

import java.util.*;

import scc.frames.*;

public class TmpNode
{
	public static final int POTENTIAL_SPILL = 1;
	public static final int ACTUAL_SPILL = 2;

	public FrmTemp temp;
	public LinkedList<TmpNode> edges;

	public int register;
	public int spill;

	public TmpNode(FrmTemp temp)
	{
		this.temp = temp;
		edges = new LinkedList<TmpNode>();

		register = 0;
		spill = 0;
	}
}
