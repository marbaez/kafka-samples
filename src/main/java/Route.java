import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Route {

    private String type = "route";
    private Date timestamp;
    private String module = "CAREROUTE";
    private String provider;

    public Route() {
        this.timestamp = new Date();
        this.provider = "Mi proveedor";
    }

    private List<TranferRequest> paths;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<TranferRequest> getPaths() {
        return paths;
    }

    public void setPaths(List<TranferRequest> paths) {
        this.paths = paths;
    }
}
