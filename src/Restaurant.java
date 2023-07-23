//This is the Restaurant class it has a Restaurant Object
class Restaurant {
    int orderNum;
    String RestaurantName;
    String RestContactNum;
    String RestAddress;
    String RestLocation;
    String RestEmail;

    // Constructor
    public Restaurant(int orderNum, String RestaurantName, String RestContactNum, String RestAddress,
            String RestLocation, String RestEmail) {
        this.orderNum = orderNum;
        this.RestaurantName = RestaurantName;
        this.RestContactNum = RestContactNum;
        this.RestAddress = RestAddress;
        this.RestLocation = RestLocation;
        this.RestEmail = RestEmail;
    }

    // Methods
    public int getorderNum() {
        return orderNum;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public String getRestContactNum() {
        return RestContactNum;
    }

    public String getRestAddress() {
        return RestAddress;
    }

    public String GetRestLocation() {
        return RestLocation;
    }

    public String GetRestEmail() {
        return RestEmail;
    }

}