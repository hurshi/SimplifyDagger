package io.github.hurshi.simplifydagger.processor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent;
import io.github.hurshi.simplifydagger.annotation.AutoComponent;
import io.github.hurshi.simplifydagger.processor.auto_android_component.AutoAndroidComponentProcessor;
import io.github.hurshi.simplifydagger.processor.auto_component.AutoComponentProcessor;
import io.github.hurshi.simplifydagger.processor.utils.Logger;

//@AutoService(Processor.class)
public final class SimplifyDaggerProcessor extends AbstractProcessor {
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        filer = env.getFiler();
        Logger.init(env.getMessager());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        Logger.log("process SimplifyDagger");
        AutoComponentProcessor.process(env, filer);
        AutoAndroidComponentProcessor.process(env, filer);
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(AutoComponent.class.getCanonicalName());
        types.add(AutoAndroidComponent.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
