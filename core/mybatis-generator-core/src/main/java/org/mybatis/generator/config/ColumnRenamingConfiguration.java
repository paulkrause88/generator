package org.mybatis.generator.config;

public interface ColumnRenamingConfiguration {

	ColumnRenamingRule getColumnRenamingRule();
	void setColumnRenamingRule(ColumnRenamingRule crr);

}
