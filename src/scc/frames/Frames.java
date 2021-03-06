package scc.frames;

import scc.*;
import scc.abstr.*;
import scc.abstr.tree.*;
import scc.seman.*;
import scc.seman.type.*;

public class Frames implements Visitor
{
	private boolean dump;

	public Frames(boolean dump)
	{
		this.dump = dump;

		AbsFunDef get_int = (AbsFunDef) SymbTable.fnd("get_int");
		FrmDesc.setFrame(get_int, new FrmFrame(get_int));

		AbsFunDef get_char = (AbsFunDef) SymbTable.fnd("get_char");
		FrmDesc.setFrame(get_char, new FrmFrame(get_char));

		AbsFunDef put_int = (AbsFunDef) SymbTable.fnd("put_int");
		FrmDesc.setFrame(put_int, new FrmFrame(put_int));

		AbsFunDef put_char = (AbsFunDef) SymbTable.fnd("put_char");
		FrmDesc.setFrame(put_char, new FrmFrame(put_char));

		AbsFunDef put_nl = (AbsFunDef) SymbTable.fnd("put_nl");
		FrmDesc.setFrame(put_nl, new FrmFrame(put_nl));
	}

	public void dump(AbsTree tree)
	{
		if (! dump) return;
		if (Report.dumpFile() == null) return;
		indent = 0;
		tree.accept(this);
	}

	private int indent;

	public void visit(AbsArrType arrType) {
		Report.dump(indent, "AbsArrType " + arrType.position.toString() + ": " + "[" + arrType.length + "]");
		{
			SemType typ = SymbDesc.getType(arrType);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; arrType.type.accept(this); indent -= 2;
	}

	public void visit(AbsAtomConst atomConst) {
		switch (atomConst.type) {
		case AbsAtomConst.BOOL:
			Report.dump(indent, "AbsAtomConst " + atomConst.position.toString() + ": BOOLEAN(" + atomConst.value + ")");
			break;
		case AbsAtomConst.INT:
			Report.dump(indent, "AbsAtomConst " + atomConst.position.toString() + ": INTEGER(" + atomConst.value + ")");
			break;
		case AbsAtomConst.STR:
			Report.dump(indent, "AbsAtomConst " + atomConst.position.toString() + ": STRING(" + atomConst.value + ")");
			break;
		default:
			// TODO: Change to FRAMES.
			Report.error("Internal error :: scc.frames.Frames.visit(AbsAtomConst)");
		}
		{
			SemType typ = SymbDesc.getType(atomConst);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
	}

	public void visit(AbsAtomType atomType) {
		switch (atomType.type) {
		// TODO: ostali tipi
		case AbsAtomType.BOOL:
			Report.dump(indent, "AbsAtomType " + atomType.position.toString() + ": BOOLEAN");
			break;
		case AbsAtomType.INT:
			Report.dump(indent, "AbsAtomType " + atomType.position.toString() + ": INTEGER");
			break;
		// case AbsAtomType.STR:
		// 	Report.dump(indent, "AbsAtomType " + atomType.position.toString() + ": STRING");
		// 	break;
		case AbsAtomType.FLOAT:
			Report.dump(indent, "AbsAtomType " + atomType.position.toString() + ": FLOAT");
			break;
		case AbsAtomType.CHAR:
			Report.dump(indent, "AbsAtomType " + atomType.position.toString() + ": CHAR");
			break;
		case AbsAtomType.VOID:
			Report.dump(indent, "AbsAtomType " + atomType.position.toString() + ": VOID");
			break;
		default:
			Report.error("Internal error :: scc.frames.Frames.visit(AbsAtomType)");
		}
		{
			SemType typ = SymbDesc.getType(atomType);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
	}

	public void visit(AbsBinExpr binExpr) {
		switch (binExpr.oper) {
		case AbsBinExpr.OR:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": OR");
			break;
		case AbsBinExpr.AND:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": AND");
			break;
		case AbsBinExpr.EQU:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": EQU");
			break;
		case AbsBinExpr.NEQ:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": NEQ");
			break;
		case AbsBinExpr.LEQ:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": LEQ");
			break;
		case AbsBinExpr.GEQ:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": GEQ");
			break;
		case AbsBinExpr.LTH:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": LTH");
			break;
		case AbsBinExpr.GTH:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": GTH");
			break;
		case AbsBinExpr.ADD:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": ADD");
			break;
		case AbsBinExpr.SUB:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": SUB");
			break;
		case AbsBinExpr.MUL:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": MUL");
			break;
		case AbsBinExpr.DIV:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": DIV");
			break;
		case AbsBinExpr.MOD:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": MOD");
			break;
		case AbsBinExpr.DOT:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": DOT");
			break;
		case AbsBinExpr.ARR:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": ARR");
			break;
		case AbsBinExpr.ASSIGN:
			Report.dump(indent, "AbsBinExpr " + binExpr.position.toString() + ": ASSIGN");
			break;
		default:
			Report.error("Internal error :: scc.frames.Frames.visit(AbsBinExpr)");
		}
		{
			SemType typ = SymbDesc.getType(binExpr);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; binExpr.expr1.accept(this); indent -= 2;
		indent += 2; binExpr.expr2.accept(this); indent -= 2;
	}

	public void visit(AbsComp comp) {
		Report.dump(indent, "AbsComp " + comp.position.toString() + ": " + comp.name);
		{
			SemType typ = SymbDesc.getType(comp);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		{
			FrmAccess access = FrmDesc.getAccess(comp);
			if (access != null)
				Report.dump(indent + 2, "#accesed as " + access.toString());
		}
		indent += 2; comp.type.accept(this); indent -= 2;
	}

	public void visit(AbsCompName compName) {
		Report.dump(indent, "AbsCompName " + compName.position.toString() + ": " + compName.name);
		{
			SemType typ = SymbDesc.getType(compName);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
	}

	public void visit(AbsDefs defs) {
		Report.dump(indent, "AbsDefs " + defs.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(defs);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		for (int def = 0; def < defs.numDefs(); def++) {
			indent += 2; defs.def(def).accept(this); indent -= 2;
		}
	}

	public void visit(AbsDoWhile doWhileStmt) {
		Report.dump(indent, "AbsDoWhile " + doWhileStmt.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(doWhileStmt);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; doWhileStmt.body.accept(this); indent -= 2;
		indent += 2; doWhileStmt.cond.accept(this); indent -= 2;
	}

	public void visit(AbsExprs exprs) {
		Report.dump(indent, "AbsExprs " + exprs.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(exprs);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		for (int expr = 0; expr < exprs.numExprs(); expr++) {
			indent += 2; exprs.expr(expr).accept(this); indent -= 2;
		}
	}

	public void visit(AbsFor forStmt) {
		Report.dump(indent, "AbsFor " + forStmt.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(forStmt);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; forStmt.init.accept(this); indent -= 2;
		indent += 2; forStmt.cond.accept(this); indent -= 2;
		indent += 2; forStmt.step.accept(this); indent -= 2;
		indent += 2; forStmt.body.accept(this); indent -= 2;
	}

	public void visit(AbsFunCall funCall) {
		Report.dump(indent, "AbsFunCall " + funCall.position.toString() + ": " + funCall.name);
		{
			AbsDef def = SymbDesc.getNameDef(funCall);
			if (def != null)
				Report.dump(indent + 2, "#defined at " + def.position.toString());
		}
		{
			SemType typ = SymbDesc.getType(funCall);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		for (int arg = 0; arg < funCall.numArgs(); arg++) {
			indent += 2; funCall.arg(arg).accept(this); indent -= 2;
		}
	}

	public void visit(AbsFunDef funDef) {
		Report.dump(indent, "AbsFunDef " + funDef.position.toString() + ": " + funDef.name);
		{
			SemType typ = SymbDesc.getType(funDef);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		{
			FrmFrame frame = FrmDesc.getFrame(funDef);
			if (frame != null)
				Report.dump(indent + 2, "#framed as " + frame.toString());
		}
		for (int par = 0; par < funDef.numPars(); par++) {
			indent += 2; funDef.par(par).accept(this); indent -= 2;
		}
		indent += 2; funDef.type.accept(this); indent -= 2;
		indent += 2; funDef.expr.accept(this); indent -= 2;
	}

	public void visit(AbsIf ifStmt) {
		Report.dump(indent, "AbsIf " + ifStmt.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(ifStmt);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; ifStmt.cond.accept(this); indent -= 2;
		indent += 2; ifStmt.thenBody.accept(this); indent -= 2;
	}

	public void visit(AbsIfElse ifElse) {
		Report.dump(indent, "AbsIfElse " + ifElse.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(ifElse);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; ifElse.cond.accept(this); indent -= 2;
		indent += 2; ifElse.thenBody.accept(this); indent -= 2;
		indent += 2; ifElse.elseBody.accept(this); indent -= 2;
	}

	public void visit(AbsNop nop)
	{
		Report.dump(indent, "AbsNop " + nop.position.toString());
		{
			SemType typ = SymbDesc.getType(nop);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
	}

	public void visit(AbsPar par) {
		Report.dump(indent, "AbsPar " + par.position.toString() + ": " + par.name);
		{
			SemType typ = SymbDesc.getType(par);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		{
			FrmAccess access = FrmDesc.getAccess(par);
			if (access != null)
				Report.dump(indent + 2, "#accesed as " + access.toString());
		}
		indent += 2; par.type.accept(this); indent -= 2;
	}

	public void visit(AbsPtrType ptrType) {
		Report.dump(indent, "AbsPtrType " + ptrType.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(ptrType);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; ptrType.type.accept(this); indent -= 2;
	}

	public void visit(AbsRecType recType) {
		Report.dump(indent, "AbsRecType " + recType.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(recType);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		for (int comp = 0; comp < recType.numComps(); comp++) {
			indent += 2; recType.comp(comp).accept(this); indent -= 2;
		}
	}

	public void visit(AbsReturn returnStmt) {
		Report.dump(indent, "AbsReturn " + returnStmt.position.toString());
		{
			SemType typ = SymbDesc.getType(returnStmt);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; returnStmt.expr.accept(this); indent -= 2;
	}

	public void visit(AbsTypeDef typeDef) {
		Report.dump(indent, "AbsTypeDef " + typeDef.position.toString() + ": " + typeDef.name);
		{
			SemType typ = SymbDesc.getType(typeDef);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; typeDef.type.accept(this); indent -= 2;
	}

	public void visit(AbsTypeName typeName) {
		Report.dump(indent, "AbsTypeName " + typeName.position.toString() + ": " + typeName.name);
		{
			AbsDef def = SymbDesc.getNameDef(typeName);
			if (def != null)
				Report.dump(indent + 2, "#defined at " + def.position.toString());
		}
		{
			SemType typ = SymbDesc.getType(typeName);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
	}

	public void visit(AbsUnExpr unExpr) {
		switch (unExpr.oper) {
		case AbsUnExpr.ADD:
			Report.dump(indent, "AbsUnExpr " + unExpr.position.toString() + ": ADD");
			break;
		case AbsUnExpr.SUB:
			Report.dump(indent, "AbsUnExpr " + unExpr.position.toString() + ": SUB");
			break;
		case AbsUnExpr.MEM:
			Report.dump(indent, "AbsUnExpr " + unExpr.position.toString() + ": MEM");
			break;
		case AbsUnExpr.VAL:
			Report.dump(indent, "AbsUnExpr " + unExpr.position.toString() + ": VAL");
			break;
		case AbsUnExpr.NOT:
			Report.dump(indent, "AbsUnExpr " + unExpr.position.toString() + ": NOT");
			break;
		default:
			Report.error("Internal error :: scc.frames.Frames.visit(AbsBinExpr)");
		}
		{
			SemType typ = SymbDesc.getType(unExpr);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; unExpr.expr.accept(this); indent -= 2;
	}

	public void visit(AbsVarDef varDef) {
		Report.dump(indent, "AbsVarDef " + varDef.position.toString() + ": " + varDef.name);
		{
			SemType typ = SymbDesc.getType(varDef);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		{
			FrmAccess access = FrmDesc.getAccess(varDef);
			if (access != null)
				Report.dump(indent + 2, "#accesed as " + access.toString());
		}
		indent += 2; varDef.type.accept(this); if(varDef.expr != null) varDef.expr.accept(this); indent -= 2;
	}

	public void visit(AbsVarName varName) {
		Report.dump(indent, "AbsVarName " + varName.position.toString() + ": " + varName.name);
		{
			AbsDef def = SymbDesc.getNameDef(varName);
			if (def != null)
				Report.dump(indent + 2, "#defined at " + def.position.toString());
		}
		{
			SemType typ = SymbDesc.getType(varName);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
	}

	public void visit(AbsWhile whileStmt) {
		Report.dump(indent, "AbsWhile " + whileStmt.position.toString() + ":");
		{
			SemType typ = SymbDesc.getType(whileStmt);
			if (typ != null)
				Report.dump(indent + 2, "#typed as " + typ.toString());
		}
		indent += 2; whileStmt.cond.accept(this); indent -= 2;
		indent += 2; whileStmt.body.accept(this); indent -= 2;
	}

}
