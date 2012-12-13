package net.bodz.bas.t.tree.legacy;

public abstract class NamedNode
        implements INamedNode {

    protected final String name;

    private INamedNode parent;
    private final Class<?> childType;

    /**
     * <ul>
     * <li>base = null, parent != null
     * <li>base != null, parent = null
     * <li>base != null, parent != null: base is ignored.
     * </ul>
     */
    public NamedNode(Class<?> childType, INamedNode parent) {
        this.name = "(null)";

        if (childType == null && parent == null)
            throw new NullPointerException("Both childType and parent are null");

        this.parent = parent;
        this.childType = childType;

        init();
    }

    public NamedNode(String name, Class<?> childType, INamedNode parent) {
        this.name = name;

        if (childType == null && parent == null)
            throw new NullPointerException("Both childType and parent are null");

        this.parent = parent;
        this.childType = childType;

        init();
    }

    private void init() {
        if (childType != null) {
            ReverseLookupRegistry registry = ReverseLookupRegistry.getInstance();
            registry.register(this);
        }
    }

    @Override
    public String refName() {
        return name;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public INamedNode getParent() {
        return parent;
    }

    @Override
    public void setParent(INamedNode parent) {
        this.parent = parent;
    }

    @Override
    public Class<?> getChildType() {
        return childType;
    }

    @Override
    public boolean containsChild(Object obj) {
        return findChild(obj) != null;
    }
/*
 * private transient Map<String, IOperation> operationMap;
 * 
 * @Override public Collection<IOperation> getOperations() { return getOperationMap().values(); }
 * 
 * @Override public IOperation getOperation(String name) { IOperation operation =
 * getOperationMap().get(name); return operation; }
 * 
 * @Override public Map<String, IOperation> getOperationMap() { if (operationMap == null) {
 * synchronized (this) { if (operationMap == null) { OperationBuilder operationBuilder = new
 * OperationBuilder(); buildOperation(operationBuilder); operationMap = operationBuilder.getMap(); }
 * } } return operationMap; }
 * 
 * protected void buildOperation(OperationBuilder builder) { // builder.discover(getClass()); //
 * builder.discover(this); }
 */
}
