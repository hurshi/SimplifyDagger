package io.github.hurshi.simplifydagger.processor.automodule;

import java.util.LinkedList;
import java.util.List;

public class AutoModuleJavaFileWrapper {
    private String simpleScope;
    private List<AutoModuleWrapper> wrappers = new LinkedList<>();

    public AutoModuleJavaFileWrapper(String simpleScope) {
        this.simpleScope = simpleScope;
    }

    public List<AutoModuleWrapper> getWrappers() {
        return wrappers;
    }

    public void setWrappers(List<AutoModuleWrapper> wrappers) {
        this.wrappers = wrappers;
    }

    public void addWrapper(AutoModuleWrapper wrapper) {
        if (!wrappers.contains(wrapper)) {
            wrappers.add(wrapper);
        }
    }
}
