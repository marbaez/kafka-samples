public class TranferRequest {

    private Integer numberOfUsers;
    private Integer numberOfCompanions;
    private Long distance;
    private Integer numberOfActiveProviders;


    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(Integer numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public Integer getNumberOfCompanions() {
        return numberOfCompanions;
    }

    public void setNumberOfCompanions(Integer numberOfCompanions) {
        this.numberOfCompanions = numberOfCompanions;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Integer getNumberOfActiveProviders() {
        return numberOfActiveProviders;
    }

    public void setNumberOfActiveProviders(Integer numberOfActiveProviders) {
        this.numberOfActiveProviders = numberOfActiveProviders;
    }
}
