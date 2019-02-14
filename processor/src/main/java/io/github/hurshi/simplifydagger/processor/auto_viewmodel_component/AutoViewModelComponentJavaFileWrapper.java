package io.github.hurshi.simplifydagger.processor.auto_viewmodel_component;

import java.util.LinkedList;
import java.util.List;

public class AutoViewModelComponentJavaFileWrapper {
    private String packageName;
    private String classMiddleName;
    private List<AutoViewModelComponentWrapper> wrappers = new LinkedList<>();

    public AutoViewModelComponentJavaFileWrapper(String packageName, String classMiddleName) {
        this.packageName = packageName;
        this.classMiddleName = classMiddleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<AutoViewModelComponentWrapper> getWrappers() {
        return wrappers;
    }

    public void setWrappers(List<AutoViewModelComponentWrapper> wrappers) {
        this.wrappers = wrappers;
    }

    public String getClassMiddleName() {
        return classMiddleName;
    }

    public void setClassMiddleName(String classMiddleName) {
        this.classMiddleName = classMiddleName;
    }

    public void addWrapper(AutoViewModelComponentWrapper wrapper) {
        if (!wrappers.contains(wrapper)) {
            wrappers.add(wrapper);
        }
    }
}
