package io.github.hurshi.simplifydagger.processor.auto_viewmodel_component;

import javax.lang.model.element.TypeElement;

public class AutoViewModelComponentWrapper {
    private TypeElement typeElement;
    private Object viewModelValue;
    private Object scopeValue;
    private Object factoryValue;
    private Object tryMergeValue;

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    public Object getViewModelValue() {
        return viewModelValue;
    }

    public void setViewModelValue(Object viewModelValue) {
        this.viewModelValue = viewModelValue;
    }

    public Object getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(Object scopeValue) {
        this.scopeValue = scopeValue;
    }

    public Object getFactoryValue() {
        return factoryValue;
    }

    public void setFactoryValue(Object factoryValue) {
        this.factoryValue = factoryValue;
    }

    public Object getTryMergeValue() {
        return tryMergeValue;
    }

    public void setTryMergeValue(Object tryMergeValue) {
        this.tryMergeValue = tryMergeValue;
    }
}

