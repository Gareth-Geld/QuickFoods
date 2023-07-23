//This is the customer Class with the Customer Object
class Customer {
    int orderNum;
    String CustomerName;
    String CustContactNum;
    String CustAddress;
    String CustLocation;
    String CustEmail;
    String SpecialInst;

    // Constructor
    public Customer(int orderNum, String CustomerName, String CustContactNum, String CustAddress, String CustLocation,
            String CustEmail, String SpecialInst) {
        this.orderNum = orderNum;
        this.CustomerName = CustomerName;
        this.CustContactNum = CustContactNum;
        this.CustAddress = CustAddress;
        this.CustLocation = CustLocation;
        this.CustEmail = CustEmail;
        this.SpecialInst = SpecialInst;
    }

    // Methods
    public int getorderNum() {
        return orderNum;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCustContactNum() {
        return CustContactNum;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public String GetCustLocation() {
        return CustLocation;
    }

    public String GetCustEmail() {
        return CustEmail;
    }

    public String GetSpecialInst() {
        return SpecialInst;
    }

}
