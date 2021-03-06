package net.bodz.xml.models.pdb.tr;

import net.bodz.xml.util.Translet;

/**
 * <ol>
 * <li>1, modpdb.Models 仍旧直接映射到 modpdb.xml。
 * <li>2, PDB2FJ 将用于转换模型至 fj.sql 结构。
 * <li>3, fj.sql 没必要维持 mda-info 信息。fj.sql 没必要逆转换至 modpdb。
 * <li>4, pdb2ddl 在内部使用 fj.sql 作为 dialect 解释层。
 * </ol>
 */
public class PDB2DDL extends Translet {

    public static void main(String[] args) throws Exception {
        new PDB2DDL().run(args);
    }

}
