package scc.imcode;

import java.util.*;

import scc.*;
import scc.frames.*;
import scc.asmcode.*;
import scc.tmpan.*;

public class ImcCodeChunk extends ImcChunk
{
	public FrmFrame frame;
	public ImcStmt imcode;
	public ImcStmt lincode;
	public LinkedList<AsmInstr> asmcode;

	public LinkedList<TmpNode> graph;
	public HashMap<FrmTemp, String> registers;

	public ImcCodeChunk(FrmFrame frame, ImcStmt imcode)
	{
		this.frame = frame;
		this.imcode = imcode;
		this.lincode = null;
		this.asmcode = null;

		this.graph = null;
		this.registers = null;
	}

	@Override
	public void dump()
	{
		Report.dump(0, "CODE CHUNK: label=" + frame.label.name());
		Report.dump(2, frame.toString());
		if (lincode == null) imcode.dump(2); else lincode.dump(2);
	}
}
