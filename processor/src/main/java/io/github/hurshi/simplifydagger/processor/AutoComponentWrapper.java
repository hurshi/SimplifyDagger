package io.github.hurshi.simplifydagger.processor;

import javax.lang.model.element.TypeElement;

public class AutoComponentWrapper {
    private TypeElement typeElement;
    private Object moduleValue;
    private Object scopeValue;

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    public Object getModuleValue() {
        return moduleValue;
    }

    public void setModuleValue(Object moduleValue) {
        this.moduleValue = moduleValue;
    }

    public Object getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(Object scopeValue) {
        this.scopeValue = scopeValue;
    }
}
