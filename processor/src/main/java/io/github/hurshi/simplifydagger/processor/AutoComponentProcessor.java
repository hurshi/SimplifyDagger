package io.github.hurshi.simplifydagger.processor;

import com.google.gson.Gson;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import io.github.hurshi.simplifydagger.annotation.AutoComponent;

//@AutoService(Processor.class)
public final class AutoComponentProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        System.out.println(">>>>>>");
//        System.out.println(">>> set = " + new Gson().toJson(set));
//        System.out.println(">>> env = " + new Gson().toJson(env));

//        mMessage.printMessage(Diagnostic.Kind.ERROR, ">>> printMessage : ");
//        for (Element element : env.getElementsAnnotatedWith(AutoComponent.class)) {
//            AutoComponent autoComponent = element.getAnnotation(AutoComponent.class);
//            Class moduleClass = autoComponent.module();
//            Class scopeClass = autoComponent.scope();
////            JavaFile javaFile = binding.brewJava(sdk, debuggable);
//
//        }
        return false;
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
