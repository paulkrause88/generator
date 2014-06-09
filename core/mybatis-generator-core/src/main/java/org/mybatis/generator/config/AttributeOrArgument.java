package org.mybatis.generator.config;

import static org.mybatis.generator.internal.util.StringUtility.stringContainsSpace;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;


public class AttributeOrArgument extends PropertyHolder {

    private String elementId;
    private final String elementType;
    private final String columnName;
    private String javaName;
    private String jdbcType;
    private String javaType;
    private String typeHandler;
    private boolean isColumnNameDelimited;

    private String configuredDelimitedColumnName;

    public AttributeOrArgument(String column, String type) {
        columnName = column;
        elementType = type;
        isColumnNameDelimited = stringContainsSpace(columnName);
    }

    public String getColumnName() {
        return columnName;
    }

    public String getId() {
        return elementId;
    }

    public String getJavaName() {
        return javaName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public boolean isArgument() {
        return "argument".equals(elementType); //$NON-NLS-1$
    }

    public boolean isAttribute() {
        return "attribute".equals(elementType); //$NON-NLS-1$
    }

    public void setColumnNameDelimited(boolean flag) {
        isColumnNameDelimited = flag;
        configuredDelimitedColumnName = Boolean.toString(flag);
    }

    public void setId(String id) {
        elementId = id;
    }

    public void setJavaName(String name) {
        javaName = name;
    }

    public void setJavaType(String type) {
        javaType = type;
    }

    public void setJdbcType(String type) {
        jdbcType = type;
    }

    public void setTypeHandler(String handler) {
        typeHandler = handler;
    }

    public XmlElement toXmlElement() {
        XmlElement xml = new XmlElement(elementType); //$NON-NLS-1$
        if (stringHasValue(elementId)) {
            xml.addAttribute(new Attribute("id", elementId));
        }

        xml.addAttribute(new Attribute("column", columnName)); //$NON-NLS-1$

        if (stringHasValue(javaType)) {
            xml.addAttribute(new Attribute("javaType", javaType)); //$NON-NLS-1$
        }

        if (stringHasValue(jdbcType)) {
            xml.addAttribute(new Attribute("jdbcType", jdbcType)); //$NON-NLS-1$
        }

        if (stringHasValue(typeHandler)) {
            xml.addAttribute(new Attribute("typeHandler", typeHandler)); //$NON-NLS-1$
        }

        if (stringHasValue(configuredDelimitedColumnName)) {
            xml.addAttribute(new Attribute("delimitedColumnName", configuredDelimitedColumnName)); //$NON-NLS-1$
        }

        addPropertyXmlElements(xml);
        return xml;
    }

	public void validate(List<String> errors, String id) {
        if (stringHasValue(elementId)) id = elementId;
        if (!stringHasValue(columnName)) {
            errors.add(getString("ValidationError.22", id)); //$NON-NLS-1$
        }
    }		

}
