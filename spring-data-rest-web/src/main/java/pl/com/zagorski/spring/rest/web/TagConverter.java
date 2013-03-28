package pl.com.zagorski.spring.rest.web;

import pl.com.zagorski.spring.rest.web.model.Tag;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author : luke.zagorski
 */
@FacesConverter(value = "tagConverter")
public class TagConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        Tag tag = new Tag();
        tag.setName(s);

        return tag;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        return ((Tag) o).getName();
    }
}
