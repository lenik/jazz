package net.bodz.uni.shelj.codegen.java;

import java.util.ArrayList;
import java.util.List;

public class LoadExpr {

    public List<String> prepareStatements = new ArrayList<>();
    public String expr;

    public LoadExpr(String expr) {
        this.expr = expr;
    }

    public static LoadExpr simple(String expr) {
        return new LoadExpr(expr);
    }

    public static LoadExpr prepared(String expr, String... prepareStatements) {
        LoadExpr le = new LoadExpr(expr);
        for (String ps : prepareStatements)
            le.prepareStatements.add(ps);
        return le;
    }

    public List<String> getPrepareStatements() {
        return prepareStatements;
    }

    public String getExpr() {
        return expr;
    }

}
