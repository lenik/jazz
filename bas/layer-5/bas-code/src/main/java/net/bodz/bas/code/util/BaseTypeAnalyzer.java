package net.bodz.bas.code.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.codegen.IJavaImporter;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.esm.ITsImporter;
import net.bodz.bas.esm.util.ITsImporterAware;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.t.tuple.QualifiedName;

public class BaseTypeAnalyzer
        implements
            ITsImporterAware {

    protected final IJavaImporter javaImporter;
    protected final ITsImporter tsImporter;
    protected final boolean typeScript;

    public BaseTypeAnalyzer(IJavaImporter javaImporter) {
        this.javaImporter = javaImporter;
        this.tsImporter = null;
        this.typeScript = false;
    }

    public BaseTypeAnalyzer(ITsImporter tsImporter) {
        this.javaImporter = null;
        this.tsImporter = tsImporter;
        this.typeScript = true;
    }

    @Override
    public ITsImporter getTsImporter() {
        return tsImporter;
    }

    @Override
    public QualifiedName getThisType() {
        return null;
    }

    protected BaseTypeExtendInfo newInfo() {
        return new BaseTypeExtendInfo();
    }

    public final BaseTypeExtendInfo getExtendInfo(QualifiedName qName) {
        return getExtendInfo(qName, null);
    }

    public BaseTypeExtendInfo getExtendInfo(QualifiedName type, //
            QualifiedName baseType) {

        BaseTypeExtendInfo info = newInfo();
        info.type = type;

        try {
            info.javaClass = Class.forName(info.type.getFullName());
        } catch (ClassNotFoundException e1) {
        }

        info.setBaseType(baseType);

        if (info.javaBaseClass != null) {
            TypeParameters aBaseTypeParams = info.javaBaseClass.getAnnotation(TypeParameters.class);
            if (aBaseTypeParams != null)
                parseGenerics(info, aBaseTypeParams.value());
        }
        return info;
    }

    protected void parseGenerics(BaseTypeExtendInfo info, TypeParamType[] baseTypeVarTypes) {
        List<String> typeVars = new ArrayList<>();
        List<String> baseTypeArgs = new ArrayList<>();
        List<QualifiedName> baseTypeBounds = new ArrayList<>();
        List<String> recursiveArgs = new ArrayList<>();
        List<TypeParamType> typeVarTypes = new ArrayList<>();
        for (int i = 0; i < baseTypeVarTypes.length; i++) {
            TypeParamType baseParamType = baseTypeVarTypes[i];
            TypeArg arg = getArg(info, baseParamType);
            if (arg == null)
                throw new UnexpectedException();
            baseTypeArgs.add(arg.name);
            baseTypeBounds.add(arg.bounds);
            if (arg.var != null) {
                typeVars.add(arg.var);
                typeVarTypes.add(arg.varType);
                recursiveArgs.add(arg.recursiveArg);
            }
        }

        String[] _typeVars = typeVars.toArray(new String[0]);
        String rec = StringArray.join(", ", recursiveArgs);
        for (int i = 0; i < _typeVars.length; i++)
            _typeVars[i] = _typeVars[i].replace("%R", rec);
        info.typeVars = _typeVars;
        info.typeVarTypes = typeVarTypes.toArray(new TypeParamType[0]);

        info.baseTypeArgs = baseTypeArgs.toArray(new String[0]);
        info.baseTypeBounds = baseTypeBounds.toArray(new QualifiedName[0]);
        info.baseTypeVarTypes = baseTypeVarTypes;
    }

    protected TypeArg getArg(BaseTypeExtendInfo info, TypeParamType baseParamType) {
        TypeArg arg = new TypeArg();

        switch (baseParamType) {
        case THIS_TYPE:
            arg.name = info.type.name;
            arg.bounds = info.type;
            break;

        case THIS_REC:
            if (typeScript)
                arg.var = "this_t";
            else
                arg.var = "this_t extends " + info.type.name + "<%R>";

            arg.recursiveArg = "this_t";
            arg.varType = TypeParamType.THIS_TYPE;

            arg.name = "this_t";
            arg.bounds = ThisType.QNAME;
            break;

        default:
            return null;
        }
        return arg;
    }

    protected static String[] array(String... args) {
        return args;
    }

}
