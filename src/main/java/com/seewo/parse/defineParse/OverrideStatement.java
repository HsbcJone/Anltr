package com.seewo.parse.defineParse;

import com.ibm.icu.impl.locale.XCldrStub;
import com.seewo.parse.engineer.HiveParser;
import com.seewo.parse.engineer.HiveParserBaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class OverrideStatement extends HiveParserBaseVisitor<String> {
    public String type="";
    public String tableName="";

    @Override
    public String visitInsertClause(HiveParser.InsertClauseContext ctx) {
        TerminalNode terminalNode = ctx.KW_OVERWRITE();
        if(terminalNode!=null) {
            type = ctx.KW_OVERWRITE().getText();
        }else {
            type=ctx.KW_INTO().getText();
            tableName=ctx.KW_TABLE().getText();
            HiveParser.DestinationContext destination = ctx.destination();
            System.out.println(destination);
            HiveParser.TableOrPartitionContext tableOrPartitionContext = ctx.tableOrPartition();
            HiveParser.PartitionSpecContext partitionSpecContext = tableOrPartitionContext.partitionSpec();
            System.out.println(partitionSpecContext);
            HiveParser.TableNameContext tableNameContext = tableOrPartitionContext.tableName();
            String text = tableNameContext.getText();
            System.out.println(text);
        }
         return type.concat("**").concat(tableName);
    }

    @Override
    public String visitRegularBody(HiveParser.RegularBodyContext ctx) {
        HiveParser.SelectStatementContext selectStatementContext = ctx.selectStatement();
        HiveParser.FromClauseContext fromClauseContext = selectStatementContext.atomSelectStatement().fromClause();
        String text = fromClauseContext.fromSource().getText();
        String limittxt = ctx.selectStatement().limitClause().Number().toString();
        System.out.println(text);
        System.out.println(limittxt);
        return super.visitRegularBody(ctx);
    }

    @Override
    public String visitBody(HiveParser.BodyContext ctx) {
        HiveParser.SelectClauseContext selectClauseContext = ctx.selectClause();
        return super.visitBody(ctx);
    }

    @Override
    public String visitQueryStatementExpressionBody(HiveParser.QueryStatementExpressionBodyContext ctx) {
        HiveParser.RegularBodyContext regularBodyContext = ctx.regularBody();
        return super.visitQueryStatementExpressionBody(ctx);
    }
}
