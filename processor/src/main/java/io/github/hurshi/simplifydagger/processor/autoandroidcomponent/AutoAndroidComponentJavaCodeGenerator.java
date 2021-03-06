package io.github.hurshi.simplifydagger.processor.autoandroidcomponent;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;

import io.github.hurshi.simplifydagger.processor.utils.Constant;
import io.github.hurshi.simplifydagger.processor.utils.Utils;


class AutoAndroidComponentJavaCodeGenerator {
    private static final String NAME_PREF = "AutoAndroid";
    private static final String NAME_SUFFER = "ComponentInjector";

    static void autoComponentGenerator(Filer filer, List<AutoAndroidComponentWrapper> wrappers) {
        List<String> fragments = new LinkedList<>();
        String packageName = "";

        for (int i = 0; i < wrappers.size(); i++) {
            AutoAndroidComponentWrapper wrapper = wrappers.get(i);
            if (null != wrapper.getFragmentsValue() && wrapper.getFragmentsValue().toString().length() > 0) {
                fragments.addAll(Arrays.asList(wrapper.getFragmentsValue().toString().split("[,]")));
            }
            packageName = Utils.getSameHead(packageName, wrapper.getTypeElement().getQualifiedName().toString()
                    .replace("." + wrapper.getTypeElement().getSimpleName().toString(), ""));
        }

        while (packageName.endsWith(".")) {
            packageName = packageName.substring(0, packageName.length() - 1);
        }
        packageName += Constant.PACKAGE_NAME_SUFFIX;

        Map<String, AutoAndroidComponentJavaFileWrapper> map = new LinkedHashMap<>();
        for (AutoAndroidComponentWrapper w : wrappers) {
            String middleName = "";
            String packaggName = "";
            if (fragments.contains(w.getTypeElement().getQualifiedName().toString() + ".class") || (null != w.getTryMergeValue() && w.getTryMergeValue().equals(false))) {
                middleName = w.getTypeElement().getSimpleName().toString();
                packaggName = w.getTypeElement().getQualifiedName().toString()
                        .replace("." + w.getTypeElement().getSimpleName().toString(), "");
            } else if (null != w.getScopeValue() && w.getScopeValue().toString().length() > 0
                    && w.getScopeValue() != void.class) {
                String[] scopeSplit = w.getScopeValue().toString().split("[.]");
                middleName = scopeSplit[scopeSplit.length - 1];
                packaggName = packageName;
            }

            String uniqueKey = packaggName + middleName;
            AutoAndroidComponentJavaFileWrapper javaFileWrapper = map.get(uniqueKey);
            if (null == javaFileWrapper) {
                javaFileWrapper = new AutoAndroidComponentJavaFileWrapper(packaggName, middleName);
            }
            javaFileWrapper.addWrapper(w);
            map.put(uniqueKey, javaFileWrapper);
        }
        map.forEach((t, javaFileWrapper) -> generateSingleScope(filer, javaFileWrapper));
    }

    private static void generateSingleScope(Filer filer, AutoAndroidComponentJavaFileWrapper javaFileWrapper) {
        if (null == javaFileWrapper || javaFileWrapper.getWrappers().size() <= 0) {
            return;
        }
        String realClassName = NAME_PREF + javaFileWrapper.getClassMiddleName() + NAME_SUFFER;
        StringBuilder builder = new StringBuilder()
                .append(appendPackage(javaFileWrapper.getPackageName()))
                .append("@dagger.Module\n")
                .append("public abstract class ").append(realClassName).append(" {\n\n")
                .append(appendBody(javaFileWrapper.getWrappers()))
                .append("}");

        Utils.writeJavaFile(filer, javaFileWrapper.getPackageName() + "." + realClassName, builder);
    }


    private static StringBuilder appendPackage(String packageName) {
        StringBuilder builder = new StringBuilder("// Generated by SimplifyDagger (https://github.com/hurshi/SimplifyDagger).\n");
        builder.append("package ").append(packageName).append(";\n")
                .append("\n");
        return builder;
    }

    private static StringBuilder appendBody(List<AutoAndroidComponentWrapper> wrappers) {
        StringBuilder builder = new StringBuilder();
        wrappers.forEach(wrapper -> {
            builder.append(appendSingleWrapper(wrapper));
        });
        return builder;
    }

    private static StringBuilder appendSingleWrapper(AutoAndroidComponentWrapper wrapper) {
        return new StringBuilder()
                .append(appendScope(wrapper))
                .append(appendInjectorAndModules(wrapper))
                .append(appendMethod(wrapper));
    }


    private static StringBuilder appendScope(AutoAndroidComponentWrapper wrapper) {
        StringBuilder builder = new StringBuilder();
        //add factoryScope if exist
        if (null != wrapper.getScopeValue() && wrapper.getScopeValue().toString().length() > 0
                && wrapper.getScopeValue() != void.class) {
            builder.append(Constant.TAB).append("@").append(wrapper.getScopeValue().toString()).append("\n");
        }
        return builder;
    }

    private static StringBuilder appendInjectorAndModules(AutoAndroidComponentWrapper wrapper) {
        StringBuilder builder = new StringBuilder(Constant.TAB).append("@dagger.android.ContributesAndroidInjector(");

        //add modules if exist
        boolean haveModule = null != wrapper.getModulesValue() && wrapper.getModulesValue().toString().length() > 0;
        boolean haveFragments = null != wrapper.getFragmentsValue() && wrapper.getFragmentsValue().toString().length() > 0 && wrapper.getScopeValue() != void.class;
        List<String> realFragmentNames = new LinkedList<>();
        if (haveFragments) {
            String[] fgFullNames = wrapper.getFragmentsValue().toString().split("[,]");
            for (int i = 0; i < fgFullNames.length; i++) {
                String simpleClassName = "";
                String[] fgPath = fgFullNames[i].split("[.]");
                if (fgPath.length >= 2) simpleClassName = fgPath[fgPath.length - 2];
                realFragmentNames.add(fgFullNames[i].replace(simpleClassName, NAME_PREF + simpleClassName + NAME_SUFFER));
            }
        }

        haveFragments = realFragmentNames.size() > 0;

        if (haveModule || haveFragments) {
            builder.append("modules = {");
            if (haveModule) builder.append(wrapper.getModulesValue().toString());
            if (haveModule && haveFragments) builder.append(", ");
            if (haveFragments) {
                realFragmentNames.forEach(className -> builder
                        .append("\n").append(Constant.TAB).append(Constant.TAB).append(Constant.TAB)//换行
                        .append(className).append(", "));
                if (builder.length() > 2) {//删掉最后的 ", "
                    builder.setLength(builder.length() - 2);
                }
            }
            builder.append("}");
        }

        builder.append(")\n");
        return builder;
    }

    private static StringBuilder appendMethod(AutoAndroidComponentWrapper wrapper) {
        return new StringBuilder(Constant.TAB)
                .append("public abstract ").append(wrapper.getTypeElement().getQualifiedName().toString())
                .append(" inject").append(wrapper.getTypeElement().getSimpleName().toString())
                .append("();\n\n");
    }


}
