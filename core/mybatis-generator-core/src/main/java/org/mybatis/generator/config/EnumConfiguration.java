package org.mybatis.generator.config;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class EnumConfiguration extends PropertyHolder implements ColumnRenamingConfiguration {

	public static final String ID = "id"; //$NON-NLS-1$
	public static final String ENUM = "enum"; //$NON-NLS-1$
	public static final String MISSING_IS_ERROR = "missingIsError"; //$NON-NLS-1$
	public static final String NAME_COLUMN = "nameColumn"; //$NON-NLS-1$
	public static final String ORDINAL_COLUMN = "ordinalColumn"; //$NON-NLS-1$
	public static final String PUBLIC_CONSTANTS = "publicConstants"; //$NON-NLS-1$
	public static final String NAME = "name"; //$NON-NLS-1$

	private ColumnRenamingRule columnRenamingRule;
	private String ordinalColumn;
	private String nameColumn;
	private final ArrayList<AttributeOrArgument> attributeList = new ArrayList<AttributeOrArgument>();
	private final ArrayList<AttributeOrArgument> argumentList = new ArrayList<AttributeOrArgument>();
	private final String elementId;
	private final String enumName;

	public EnumConfiguration(String id, String name) {
		elementId = id;
		enumName = name;
	}

	public void addArgument(AttributeOrArgument argument) {
		argumentList.add(argument);
	}

	public void addAttribute(AttributeOrArgument attribute) {
		attributeList.add(attribute);
	}

	public ColumnRenamingRule getColumnRenamingRule() {
		return columnRenamingRule;
	}

	public String getOrdinalColumn() {
		return ordinalColumn;
	}

	public String getName() {
		return enumName;
	}

	public String getNameColumn() {
		return nameColumn;
	}

	public List<AttributeOrArgument> getAttributeList() {
		return Collections.unmodifiableList(attributeList);
	}

	public List<AttributeOrArgument> getArgumentList() {
		return Collections.unmodifiableList(argumentList);
	}

	public String getId() {
		return elementId;
	}

	public boolean isMissingAnError() {
		return ordinalColumn == null && Boolean.valueOf(getProperty(MISSING_IS_ERROR));
	}

	public boolean isPublicConstants() {
		return Boolean.valueOf(getProperty(PUBLIC_CONSTANTS));
	}

	public void setColumnRenamingRule(ColumnRenamingRule crr) {
		columnRenamingRule = crr;
	}

	public void setNameColumn(String column) {
		nameColumn = column;
	}

	public void setOrdinalColumn(String column) {
		ordinalColumn = column;
	}

	public Element toXmlElement() {
		XmlElement xml = new XmlElement(ENUM);
		if (!elementId.equals(nameColumn)) {
			xml.addAttribute(new Attribute(ID, elementId));
		}

		xml.addAttribute(new Attribute(NAME_COLUMN, nameColumn));

		if (stringHasValue(ordinalColumn)) {
			xml.addAttribute(new Attribute(ORDINAL_COLUMN, ordinalColumn));
		}

		if (!enumName.equals(nameColumn)) {
			xml.addAttribute(new Attribute(NAME, enumName));
		}

		if (columnRenamingRule != null) {
			xml.addElement(columnRenamingRule.toXmlElement());
		}

		for (AttributeOrArgument att : attributeList) {
			xml.addElement(att.toXmlElement());
		}

		for (AttributeOrArgument arg : argumentList) {
			xml.addElement(arg.toXmlElement());
		}

		addPropertyXmlElements(xml);
		return xml;
	}

	public void validate(List<String> errors) {
		if (!stringHasValue(nameColumn)) {
			errors.add(getString("ValidationError.26", enumName)); //$NON-NLS-1$
		}

		if (columnRenamingRule != null) {
			columnRenamingRule.validate(errors, enumName);
		}

		for (AttributeOrArgument arg : argumentList) {
			arg.validate(errors, enumName);
		}

		for (AttributeOrArgument att : attributeList) {
			att.validate(errors, enumName);
		}
	}

}
