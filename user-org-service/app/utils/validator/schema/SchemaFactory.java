package utils.validator.schema;

public class SchemaFactory {

    static volatile SchemaManager INSTANCE;


    private SchemaFactory() {
        throw new AssertionError();
    }


    /**
     * this methods should be invoke to get the instance of the class.
     *
     * @return
     */
    public static SchemaManager getInstance() {
        if (INSTANCE == null) {
            synchronized (SchemaFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SchemaManager();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * this method is been made to protect the class from deserialization
     *
     * @return INSTANCE
     */
    protected SchemaManager readResolved() {
        return getInstance();
    }

}
