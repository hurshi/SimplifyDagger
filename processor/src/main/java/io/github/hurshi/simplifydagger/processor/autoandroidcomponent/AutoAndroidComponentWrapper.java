package io.github.hurshi.simplifydagger.processor.autoandroidcomponent;

import javax.lang.model.element.TypeElement;

public class AutoAndroidComponentWrapper {
    private TypeElement typeElement;
    private Object modulesValue;
    private Object scopeValue;
    private Object fragmentsValue;
    private Object tryMergeValue;

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

    public Object getFragmentsValue() {
        return fragmentsValue;
    }

    public void setFragmentsValue(Object fragmentsValue) {
        this.fragmentsValue = fragmentsValue;
    }

    public Object getTryMergeValue() {
        return tryMergeValue;
    }

    public void setTryMergeValue(Object tryMergeValue) {
        this.tryMergeValue = tryMergeValue;
    }
}

