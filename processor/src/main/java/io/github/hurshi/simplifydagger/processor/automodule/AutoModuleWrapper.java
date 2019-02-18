package io.github.hurshi.simplifydagger.processor.automodule;

import javax.lang.model.element.TypeElement;

public class AutoModuleWrapper {
    private TypeElement typeElement;
    private Object scopeValue;

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    public Object getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(Object scopeValue) {
        this.scopeValue = scopeValue;
    }
}
