package io.github.hurshi.simplifydagger.processor;

import javax.lang.model.element.TypeElement;

public class AutoComponentWrapper {
    private TypeElement typeElement;
    private Object modulesValue;
    private Object dependenciesValue;
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

    public Object getDependenciesValue() {
        return dependenciesValue;
    }

    public void setDependenciesValue(Object dependenciesValue) {
        this.dependenciesValue = dependenciesValue;
    }

    public Object getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(Object scopeValue) {
        this.scopeValue = scopeValue;
    }
}
