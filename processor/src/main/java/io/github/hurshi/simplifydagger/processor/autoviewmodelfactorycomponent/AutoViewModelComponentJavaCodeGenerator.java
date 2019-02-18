package io.github.hurshi.simplifydagger.processor.autoviewmodelfactorycomponent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;

import io.github.hurshi.simplifydagger.processor.utils.Constant;
import io.github.hurshi.simplifydagger.processor.utils.Utils;


class AutoViewModelComponentJavaCodeGenerator {
    private static final String NAME_PREF = "AutoViewModel";
    private static final String NAME_SUFFER = "ComponentInjector";

    static void autoComponentGenerator(Filer filer, List<AutoViewModelComponentWrapper> wrappers) {
        String packageName = getPackageName(wrappers);

        Map<String, AutoViewModelComponentJavaFileWrapper> map = new LinkedHashMap<>();
        for (AutoViewModelComponentWrapper w : wrappers) {
            String middleName = "";
            String packaggName = packageName;
            if (null != w.getTryMergeValue() && w.getTryMergeValue().equals(false)) {
                middleName = w.getTypeElement().getSimpleName().toString();
                packaggName = w.getTypeElement().getQualifiedName().toString()
                        .replace("." + w.getTypeElement().getSimpleName().toString(), "");
            } else if (null != w.getFactoryScopeValue() && w.getFactoryScopeValue().toString().length() > 0
                    && w.getFactoryScopeValue() != void.class) {
                String[] scopeSplit = w.getFactoryScopeValue().toString().split("[.]");
                middleName = scopeSplit[scopeSplit.length - 1];
            }

            String uniqueKey = packaggName + middleName;
            AutoViewModelComponentJavaFileWrapper javaFileWrapper = map.get(uniqueKey);
            if (null == javaFileWrapper) {
                javaFileWrapper = new AutoViewModelComponentJavaFileWrapper(packaggName, middleName);
            }
            javaFileWrapper.addWrapper(w);
            map.put(uniqueKey, javaFileWrapper);
        }
        map.forEach((t, javaFileWrapper) -> generateSingleScope(filer, javaFileWrapper));
    }

    private static void generateSingleScope(Filer filer, AutoViewModelComponentJavaFileWrapper javaFileWrapper) {
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

    private static StringBuilder appendBody(List<AutoViewModelComponentWrapper> wrappers) {
        StringBuilder builder = new StringBuilder();
//        Map<String, String> factorySet = new HashMap<>();
        wrappers.forEach(wrapper -> {
            builder.append(appendSingleWrapper(wrapper));


//            final String factory = wrapper.getFactoryValue().toString();
//            final String oldScope = factorySet.get(factory);
//            String scope = null;
//            if (null != wrapper.getFactoryScopeValue()) {
//                scope = wrapper.getFactoryScopeValue().toString();
//            }
//            if (null != oldScope && !oldScope.equals(scope)) {
//                //some wrong
//            } else {
//                factorySet.put(factory, scope);
//            }
        });
//        factorySet.forEach((factory, scope) -> {
//            builder.append(appendFactory(factory, scope));
//        });
        return builder;
    }

    private static StringBuilder appendSingleWrapper(AutoViewModelComponentWrapper wrapper) {
        return new StringBuilder()
                .append(appendScope(wrapper.getFactoryScopeValue()))
                .append(Constant.TAB).append("@dagger.Binds\n")
                .append(Constant.TAB).append("@dagger.multibindings.IntoMap\n")
                .append(appendViewModelKey(wrapper))
                .append(appendMethod(wrapper));
    }


    private static StringBuilder appendScope(Object scopeValue) {
        StringBuilder builder = new StringBuilder();
        //add factoryScope if exist
        if (null != scopeValue && scopeValue.toString().length() > 0
                && scopeValue != void.class) {
            builder.append(Constant.TAB).append("@").append(scopeValue.toString()).append("\n");
        }
        return builder;
    }

    private static StringBuilder appendViewModelKey(AutoViewModelComponentWrapper wrapper) {
        StringBuilder builder = new StringBuilder();
        boolean haveViewModel = null != wrapper.getViewModelValue() && wrapper.getViewModelValue().toString().length() > 0;

        if (haveViewModel) {
            builder.append(Constant.TAB).append("@io.github.hurshi.simplifydagger.annotation.ViewModelKey(");
            builder.append(wrapper.getViewModelValue().toString())
                    .append(".class)\n");
        }
        return builder;
    }

    private static StringBuilder appendMethod(AutoViewModelComponentWrapper wrapper) {
        return new StringBuilder(Constant.TAB)
                .append("public abstract android.arch.lifecycle.ViewModel bind")
                .append(getSimpleName(wrapper.getViewModelValue().toString()))
                .append("(").append(wrapper.getViewModelValue().toString()).append(" m")
                .append(");\n\n");
    }

    private static StringBuilder appendFactory(String factory, String scope) {
        return new StringBuilder()
                .append(appendScope(scope))
                .append(Constant.TAB).append("@dagger.Binds\n")
                .append(Constant.TAB).append("public abstract android.arch.lifecycle.ViewModelProvider.Factory bind")
                .append(getSimpleName(factory)).append("(").append(factory).append(" factory").append(");\n\n");

    }

    private static String getSimpleName(String allName) {
        final String[] factorySplits = allName.split("[.]");
        return factorySplits[factorySplits.length - 1];
    }

    private static String getPackageName(List<AutoViewModelComponentWrapper> wrappers) {
        String packageName = "";
        for (int i = 0; i < wrappers.size(); i++) {
            packageName = Utils.getSameHead(packageName, wrappers.get(i).getTypeElement().getQualifiedName().toString()
                    .replace("." + wrappers.get(i).getTypeElement().getSimpleName().toString(), ""));
        }
        while (packageName.endsWith(".")) {
            packageName = packageName.substring(0, packageName.length() - 1);
        }
        packageName += Constant.PACKAGE_NAME_SUFFIX;
        return packageName;
    }
}
