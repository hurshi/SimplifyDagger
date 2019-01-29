package io.github.hurshi.simplifydagger.processor.auto_android_component;

import javax.lang.model.element.TypeElement;

public class AutoAndroidComponentWrapper {
    private TypeElement typeElement;
    private Object modulesValue;
    private Object scopeValue;

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    public Object getModulesValue() {
        return modulesValue;
    }

    public void setModulesValue(Object modulesValue) {
        this.modulesValue = modulesValue;
    }

    public Object getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(Object scopeValue) {
        this.scopeValue = scopeValue;
    }
}
