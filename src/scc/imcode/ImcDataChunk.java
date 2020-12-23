package scc.imcode;

import scc.*;
import scc.frames.*;

public class ImcDataChunk extends ImcChunk
{
	public FrmLabel label;
	public int size;

	public static final int BYTE = 1;
	public static final int WORD = 3;

	public int count;
	public String data;

	public ImcDataChunk(FrmLabel label, int size, int count)
	{
		this.label = label;
		this.size = size;
		this.count = count;
		this.data = null;
	}

	public ImcDataChunk(FrmLabel label, int size, String data)
	{
		this.label = label;
		this.size = size;
		this.count = 1;
		this.data = data;
	}

	@Override
	public void dump()
	{
	    String extra = data != null ? (" data=" + data) : (" count=" + count);
		Report.dump(0, "DATA CHUNK: label=" + label.name() + " size=" + size + extra);
	}
}
