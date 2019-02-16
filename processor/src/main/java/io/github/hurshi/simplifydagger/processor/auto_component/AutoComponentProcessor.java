package io.github.hurshi.simplifydagger.processor.auto_component;

import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import io.github.hurshi.simplifydagger.annotation.AutoComponent;
import io.github.hurshi.simplifydagger.processor.utils.Utils;

public final class AutoComponentProcessor {
    public static void process(RoundEnvironment env, Filer filer) {
        List<AutoComponentWrapper> autoComponentWrappers = getAutoComponentWrappers(env);
        for (AutoComponentWrapper wrapper : autoComponentWrappers) {
            AutoComponentJavaCodeGenerator.autoComponentGenerator(filer, wrapper);
        }
    }

    private static List<AutoComponentWrapper> getAutoComponentWrappers(RoundEnvironment env) {
        return Utils.getWrappers(env, AutoComponent.class, (mirror, element) -> {
            AutoComponentWrapper wrapper = new AutoComponentWrapper();
            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirror.getElementValues().entrySet()) {
                String key = entry.getKey().getSimpleName().toString();
                Object value = entry.getValue().getValue();
                switch (key) {
                    case "modules": {
                        wrapper.setModulesValue(value);
                        break;
                    }
                    case "scope": {
                        wrapper.setScopeValue(value);
                        break;
                    }
                    case "dependencies": {
                        wrapper.setDependenciesValue(value);
                        break;
                    }
                }
            }
            if (element instanceof TypeElement) {
                wrapper.setTypeElement((TypeElement) element);
                return wrapper;
            }
            return null;
        });
    }

}
