package ir.man.JavaInWeb.tag;

import ir.man.JavaInWeb.model.ProductCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class Select extends BodyTagSupport {

    private String name;
    private Collection<?> list;
    private String labelProperty;
    private String valueProperty;
    private String width = "12px";

    @Override
    public int doStartTag() throws JspException {
        System.out.println("doStartTag".toUpperCase());
        String style = "";
        String options = "";
        String select = "";
        Object next = null;
        if (width != null && !width.equals(""))
            style = String.format("style='width:%s'", width);

        Iterator<?> obj = list.iterator();
        System.out.println("SIZE : " + list.size());
        while (obj.hasNext()) {
            next = obj.next();
            try {
                labelProperty = Character.toUpperCase(labelProperty.charAt(0)) + labelProperty.substring(1);
                valueProperty = Character.toUpperCase(valueProperty.charAt(0)) + valueProperty.substring(1);
                Method labelProp = next.getClass().getMethod("get" + labelProperty);
                Method valueProp = next.getClass().getMethod("get" + valueProperty);
                Object label = labelProp.invoke(next);
                Object value = valueProp.invoke(next);
                options += String.format("<option value='%s'>%s</option>", value.toString(), label.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        select = String.format("<select name='%s' %s>%s</select>", name, style, options);
        JspWriter out = pageContext.getOut();
        try {
            out.print(select);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<?> getList() {
        return list;
    }

    public void setList(Collection<?> list) {
        this.list = list;
    }

    public String getLabelProperty() {
        return labelProperty;
    }

    public void setLabelProperty(String labelProperty) {
        this.labelProperty = labelProperty;
    }

    public String getValueProperty() {
        return valueProperty;
    }

    public void setValueProperty(String valueProperty) {
        this.valueProperty = valueProperty;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
