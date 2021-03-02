package com.seewo.parse.defineParse;

import com.seewo.parse.engineer.HiveLexer;
import com.seewo.parse.engineer.HiveParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class TestOverrideStateMent {

    public static void main(String[] args) {

         String sql = "insert overwrite table account2 select id,age,name from account_tmp;";
         sql = "insert into table account2 select id,age,name from account_tmp limit 188;";
         HiveLexer lexer = new HiveLexer(new ANTLRInputStream(sql.toUpperCase()));
         CommonTokenStream tokenStream = new CommonTokenStream(lexer);
         //解析器
         HiveParser hiveParser=new HiveParser(tokenStream);

         HiveParser.InsertClauseContext insertClauseContext = hiveParser.insertClause();
         OverrideStatement overrideStatement = new OverrideStatement();
         overrideStatement.visitInsertClause(insertClauseContext);
         overrideStatement.visitRegularBody(hiveParser.regularBody());
         overrideStatement.visitQueryStatementExpressionBody(hiveParser.queryStatementExpressionBody());
         System.out.println(overrideStatement.type);
         System.out.println(overrideStatement.tableName);

    }
}
