package cn.org.rapid_framework.generator.ext.tableconfig.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import cn.org.rapid_framework.generator.GeneratorTestCase;
import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfig.OperationConfig;
import cn.org.rapid_framework.generator.provider.db.sql.model.Sql;

public class TableConfigSetTest extends GeneratorTestCase {
    
    public void test() {
        TableConfigSet set = new TableConfigSet();
        assertTrue(set.getSequences().isEmpty());
        
        TableConfig config = new TableConfig();
        set.addTableConfig(config);
        assertTrue(set.getSequences().isEmpty());
        
        config.setSequence("123");
        assertFalse(set.getSequences().isEmpty());
    }
    Sql sql;
    public void testListner() throws SQLException, Exception {
//    	sql = newSql(newOperation("queryUserInfo","select * from user_info"),null);
//		
//		assertEquals(sql.getResultClass(),"UserInfo");
		
    	sql = newSql(newOperation("queryUserInfo","select * from user_info"),"CustomUserInfo");
		
		assertEquals(sql.getResultClass(),"CustomUserInfo");
		
    }
    
    public OperationConfig newOperation(String name,String sql) {
    	OperationConfig operation = new OperationConfig();
    	operation.setSql(sql);
    	operation.setName(name);
    	return operation;
    }

	private Sql newSql(OperationConfig operation,String customClassName)
			throws SQLException, Exception {
		TableConfigSet set = new TableConfigSet();
    	TableConfig tc = new TableConfig();
    	tc.setSqlName("user_info");
    	tc.setClassName(customClassName);
		tc.setOperations(Arrays.asList(operation));
		set.addTableConfig(tc);
		
		List<Sql> sqls = tc.getSqls();
		Sql sql = sqls.get(0);
		return sql;
	}
}