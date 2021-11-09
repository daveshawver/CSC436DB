package u21g;

public class DatabasePath {
    private static DatabasePath databasePathInstance = new DatabasePath();

    public static DatabasePath getInstance() {
        return databasePathInstance;
    }

    private String dbPath;

    private DatabasePath() {
    }

    public String getPath() {
        return dbPath;
    }

    public void setPath(String databasePath) {
        this.dbPath = databasePath;
    }
}