package io.github.hurshi.simplifydagger.processor.automodule;

import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import io.github.hurshi.simplifydagger.annotation.AutoModule;
import io.github.hurshi.simplifydagger.processor.utils.Utils;

public final class AutoModuleProcessor {
    public static void process(RoundEnvironment env, Filer filer) {
        AutoModuleJavaCodeGenerator.autoComponentGenerator(filer, getAutoComponentWrappers(env));
    }

    private static List<AutoModuleWrapper> getAutoComponentWrappers(RoundEnvironment env) {
        return Utils.getWrappers(env, AutoModule.class, (mirror, element) -> {
            AutoModuleWrapper wrapper = new AutoModuleWrapper();
            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirror.getElementValues().entrySet()) {
                String key = entry.getKey().getSimpleName().toString();
                Object value = entry.getValue().getValue();
                switch (key) {
                    case "scope": {
                        wrapper.setScopeValue(value);
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
