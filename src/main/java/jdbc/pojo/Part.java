package jdbc.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Part {

    private String partName;
    private String partNumber;
    private String vendor;
    private Integer qty;
    private String shipped;
    private String receive;


    public Part(String partName, String partNumber, String vendor, Integer qty, Date shipped, Date receive) {
        this.partName = partName;
        this.partNumber = partNumber;
        this.vendor = vendor;
        this.qty = qty;

        SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        this.shipped = shipped!= null ? ft.format(shipped) : "";
        this.receive = receive!= null ? ft.format(receive) : "";
    }


    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getShipped() {
        return shipped;
    }

    public void setShipped(String shipped) {
        this.shipped = shipped;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }
}
