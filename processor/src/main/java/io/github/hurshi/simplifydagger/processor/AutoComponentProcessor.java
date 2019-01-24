package io.github.hurshi.simplifydagger.processor;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import io.github.hurshi.simplifydagger.annotation.AutoComponent;

//@AutoService(Processor.class)
public final class AutoComponentProcessor extends AbstractProcessor {
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        Logger.log("process @AutoComponent");
        List<AutoComponentWrapper> autoComponentWrappers = getAutoComponentWrappers(env);
        for (AutoComponentWrapper wrapper : autoComponentWrappers) {
            JavaCodeGenerator.autoComponentGenerator(filer, wrapper);
        }
        return false;
    }

    private List<AutoComponentWrapper> getAutoComponentWrappers(RoundEnvironment env) {
        List<AutoComponentWrapper> autoComponentWrappers = new LinkedList<>();
        for (Element element : env.getElementsAnnotatedWith(AutoComponent.class)) {
            for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
                AutoComponentWrapper wrapper = new AutoComponentWrapper();
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirror.getElementValues().entrySet()) {
                    String key = entry.getKey().getSimpleName().toString();
                    Object value = entry.getValue().getValue();
                    switch (key) {
                        case "module": {
                            wrapper.setModuleValue(value);
                            break;
                        }
                        case "scope": {
                            wrapper.setScopeValue(value);
                            break;
                        }
                    }
                }
                if (null != wrapper.getModuleValue()
                        && null != wrapper.getScopeValue()
                        && element instanceof TypeElement) {
                    wrapper.setTypeElement((TypeElement) element);
                    autoComponentWrappers.add(wrapper);
                }
            }
        }
        return autoComponentWrappers;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(AutoComponent.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
