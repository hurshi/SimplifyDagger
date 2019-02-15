package io.github.hurshi.simplifydagger.processor.auto_android_component;

import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent;
import io.github.hurshi.simplifydagger.processor.utils.Utils;

public final class AutoAndroidComponentProcessor {
    public static void process(RoundEnvironment env, Filer filer) {
        AutoAndroidComponentJavaCodeGenerator.autoComponentGenerator(filer, getAutoComponentWrappers(env));
    }

    private static List<AutoAndroidComponentWrapper> getAutoComponentWrappers(RoundEnvironment env) {
        return Utils.getWrappers(env, AutoAndroidComponent.class, (mirror, element) -> {
            AutoAndroidComponentWrapper wrapper = new AutoAndroidComponentWrapper();
            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirror.getElementValues().entrySet()) {
                String key = entry.getKey().getSimpleName().toString();
                Object value = entry.getValue().getValue();
                switch (key) {
                    case "modules": {
                        wrapper.setModulesValue(value);
                        break;
                    }
                    case "factoryScope": {
                        wrapper.setScopeValue(value);
                        break;
                    }
                    case "fragments": {
                        wrapper.setFragmentsValue(value);
                        break;
                    }
                    case "tryMerge": {
                        wrapper.setTryMergeValue(value);
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
