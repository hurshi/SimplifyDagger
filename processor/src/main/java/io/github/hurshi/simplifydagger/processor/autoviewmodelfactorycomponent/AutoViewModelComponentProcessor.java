package io.github.hurshi.simplifydagger.processor.autoviewmodelfactorycomponent;

import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import io.github.hurshi.simplifydagger.annotation.AutoViewModelFactoryComponent;
import io.github.hurshi.simplifydagger.processor.utils.Utils;

public final class AutoViewModelComponentProcessor {
    public static void process(RoundEnvironment env, Filer filer) {
        AutoViewModelComponentJavaCodeGenerator.autoComponentGenerator(filer, getAutoComponentWrappers(env));
    }

    private static List<AutoViewModelComponentWrapper> getAutoComponentWrappers(RoundEnvironment env) {
        return Utils.getWrappers(env, AutoViewModelFactoryComponent.class, (mirror, element) -> {
            AutoViewModelComponentWrapper wrapper = new AutoViewModelComponentWrapper();
            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirror.getElementValues().entrySet()) {
                String key = entry.getKey().getSimpleName().toString();
                Object value = entry.getValue().getValue();
                switch (key) {
                    case "viewModel": {
                        wrapper.setViewModelValue(value);
                        break;
                    }
                    case "factoryScope": {
                        wrapper.setFactoryScopeValue(value);
                        break;
                    }
                    case "factory": {
                        wrapper.setFactoryValue(value);
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
