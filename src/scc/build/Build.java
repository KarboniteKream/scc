package scc.build;

import java.util.*;

import scc.*;
import scc.frames.*;
import scc.imcode.*;
import scc.asmcode.*;

public class Build
{
	private final boolean dump;

	public Build(boolean dump)
	{
		this.dump = dump;
	}

	public void build(LinkedList<ImcChunk> chunks)
	{
		for (ImcChunk chunk : chunks) {
			if (chunk instanceof ImcCodeChunk) {
				ImcCodeChunk codeChunk = (ImcCodeChunk) chunk;

				LinkedList<AsmInstr> prologue = new LinkedList<>();
				codeChunk.asmcode.addAll(1, prologue);

				LinkedList<AsmInstr> epilogue = new LinkedList<>();
				epilogue.add(new AsmLABEL("`l0", codeChunk.frame.endLabel));
				epilogue.add(new AsmOPER("RSUB", null));
				codeChunk.asmcode.addAll(epilogue);
			}
		}

		Set<String> functions = new HashSet<>();

		for (ImcChunk chunk : chunks) {
			if (chunk instanceof ImcCodeChunk) {
				for (AsmInstr instr : ((ImcCodeChunk)chunk).asmcode) {
					if (instr.mnemonic.equals("JSUB")) {
						functions.add(instr.labels.getFirst().name());
					}
				}
			}
		}

		if (functions.contains("_get_int")) {
			ImcCodeChunk get_int = new ImcCodeChunk(null, null);

			LinkedList<AsmInstr> asmcode = new LinkedList<>();
			asmcode.add(new AsmLABEL("`l0", new FrmLabel("_get_int")));
			// TODO
			asmcode.add(new AsmOPER("RSUB", null));
			get_int.asmcode = asmcode;

			chunks.add(get_int);
		}

		if (functions.contains("_get_char")) {
			ImcCodeChunk get_char = new ImcCodeChunk(null, null);

			LinkedList<AsmInstr> asmcode = new LinkedList<>();
			asmcode.add(new AsmLABEL("`l0", new FrmLabel("_get_char")));
			asmcode.add(new AsmOPER("RD", "#0"));
			asmcode.add(new AsmOPER("RSUB", null));
			get_char.asmcode = asmcode;

			chunks.add(get_char);
		}

		if (functions.contains("_put_int")) {
			ImcCodeChunk put_int = new ImcCodeChunk(null, null);

			LinkedList<AsmInstr> asmcode = new LinkedList<>();
			asmcode.add(new AsmLABEL("`l0", new FrmLabel("_put_int")));
			// TODO
			asmcode.add(new AsmOPER("RSUB", null));
			put_int.asmcode = asmcode;

			chunks.add(put_int);
		}

		if (functions.contains("_put_char")) {
			ImcCodeChunk put_char = new ImcCodeChunk(null, null);

			LinkedList<AsmInstr> asmcode = new LinkedList<>();
			asmcode.add(new AsmLABEL("`l0", new FrmLabel("_put_char")));
			asmcode.add(new AsmOPER("STA", "_wbuf"));
			asmcode.add(new AsmOPER("LDA", "@ADDR"));
			asmcode.add(new AsmOPER("WD", "#1"));
			asmcode.add(new AsmOPER("LDA", "_wbuf"));
			asmcode.add(new AsmOPER("RSUB", null));
			put_char.asmcode = asmcode;

			chunks.add(put_char);
		}

		if (functions.contains("_put_nl")) {
			ImcCodeChunk put_nl = new ImcCodeChunk(null, null);

			LinkedList<AsmInstr> asmcode = new LinkedList<>();
			asmcode.add(new AsmLABEL("`l0", new FrmLabel("_put_nl")));
			asmcode.add(new AsmOPER("STA", "_wbuf"));
			asmcode.add(new AsmOPER("LDA", "#10"));
			asmcode.add(new AsmOPER("WD", "#1"));
			asmcode.add(new AsmOPER("LDA", "_wbuf"));
			asmcode.add(new AsmOPER("RSUB", null));
			put_nl.asmcode = asmcode;

			chunks.add(put_nl);
		}

		if (functions.contains("_put_char") || functions.contains("_put_nl")) {
			chunks.add(new ImcDataChunk(new FrmLabel("_wbuf"), ImcDataChunk.WORD, 1));
		}

		chunks.add(new ImcDataChunk(new FrmLabel("FP"), ImcDataChunk.WORD, "X'001000'"));
		chunks.add(new ImcDataChunk(new FrmLabel("SP"), ImcDataChunk.WORD, "X'001000'"));
		chunks.add(new ImcDataChunk(new FrmLabel("ADDR"), ImcDataChunk.WORD, 1));
		chunks.add(new ImcDataChunk(new FrmLabel("LR"), ImcDataChunk.WORD, 1));

		ImcCodeChunk main = new ImcCodeChunk(null, null);
		main.asmcode = new LinkedList<>();

		main.asmcode.add(new AsmLABEL("`l0", new FrmLabel("MAIN")));
		main.asmcode.add(new AsmOPER("START", "100"));
		main.asmcode.add(new AsmOPER("LDX", "FP"));
		main.asmcode.add(new AsmOPER("JSUB", "_main"));
		main.asmcode.add(new AsmLABEL("`l0", new FrmLabel("_halt")));
		main.asmcode.add(new AsmOPER("J", "_halt"));

		chunks.addFirst(main);
	}

	public void dump(LinkedList<ImcChunk> chunks)
	{
		if (!dump) return;
		if (Report.dumpFile() == null) return;

		int labelLength = 4;
		boolean dataSegment = false;
		int i = 0;

		for (ImcChunk chunk : chunks) {
			if (chunk instanceof ImcDataChunk) {
				int length = ((ImcDataChunk)chunk).label.name().length();

				if (length > labelLength) {
					labelLength = length;
				}

				dataSegment = true;
			} else if (chunk instanceof ImcCodeChunk) {
				for (AsmInstr instr : ((ImcCodeChunk)chunk).asmcode) {
					if (instr.labels.size() > 0) {
						int length = instr.labels.getFirst().name().length();

						if (length > labelLength) {
							labelLength = length;
						}
					}
				}
			}
		}

		for (ImcChunk chunk : chunks) {
			if (chunk instanceof ImcCodeChunk) {
				if (i++ > 0) {
					Report.dump(0, "");
				}

				ImcCodeChunk codeChunk = (ImcCodeChunk)chunk;

				// zakaj ze potrebujem nop?
				for (int j = 0; j < codeChunk.asmcode.size(); j++) {
					AsmInstr instr = codeChunk.asmcode.get(j);

					if (instr instanceof AsmLABEL) {
						Report.dump(0, String.format("%-" + labelLength + "s %s", instr.labels.getFirst().name(), codeChunk.asmcode.get(++j).format(codeChunk.registers)));
					} else {
						Report.dump(labelLength + 1, instr.format(codeChunk.registers));
					}
				}
			}
		}

		if (dataSegment) {
			Report.dump(0, "");

			for (ImcChunk chunk : chunks) {
				if (chunk instanceof ImcDataChunk) {
					ImcDataChunk dataChunk = (ImcDataChunk) chunk;
					String label = String.format("%-" + labelLength + "s", dataChunk.label.name());

					if (dataChunk.data == null) {
						String opcode = null;

						switch(dataChunk.size) {
							case ImcDataChunk.BYTE: opcode = "RESB"; break;
							case ImcDataChunk.WORD: opcode = "RESW"; break;
						}

						Report.dump(0, String.format("%s %s %d", label, opcode, dataChunk.count));
					} else {
						String opcode = null;

						switch(dataChunk.size) {
							case ImcDataChunk.BYTE: opcode = "BYTE"; break;
							case ImcDataChunk.WORD: opcode = "WORD"; break;
						}

						Report.dump(0, String.format("%s %s %s", label, opcode, dataChunk.data));
					}
				}
			}
		}
	}
}
