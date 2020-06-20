package net.bodz.sms;

import java.util.List;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface ISmsProvider {

    List<IShortMessageService> getShortMessageServices();

}
