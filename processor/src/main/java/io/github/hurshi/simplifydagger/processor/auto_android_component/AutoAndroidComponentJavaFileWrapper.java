package io.github.hurshi.simplifydagger.processor.auto_android_component;

import java.util.LinkedList;
import java.util.List;

public class AutoAndroidComponentJavaFileWrapper {
    private String packageName;
    private String classMiddleName;
    private List<AutoAndroidComponentWrapper> wrappers = new LinkedList<>();

    public AutoAndroidComponentJavaFileWrapper(String packageName, String classMiddleName) {
        this.packageName = packageName;
        this.classMiddleName = classMiddleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<AutoAndroidComponentWrapper> getWrappers() {
        return wrappers;
    }

    public void setWrappers(List<AutoAndroidComponentWrapper> wrappers) {
        this.wrappers = wrappers;
    }

    public String getClassMiddleName() {
        return classMiddleName;
    }

    public void setClassMiddleName(String classMiddleName) {
        this.classMiddleName = classMiddleName;
    }

    public void addWrapper(AutoAndroidComponentWrapper wrapper) {
        if (!wrappers.contains(wrapper)) {
            wrappers.add(wrapper);
        }
    }
}
