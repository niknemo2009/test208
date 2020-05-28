package ThreadLab.entities;

public class Invoice {
    private Product product;
    private int count;
    private float price;
    private String bankAccount;
    private String shopName;
    private boolean isCompleted;

    public Invoice(Product product, int count, float priceForOne, String bankAccount, String shopName) {
        this.product = product;
        this.count = count;
        this.price = priceForOne * count;
        this.bankAccount = bankAccount;
        this.shopName = shopName;
        this.isCompleted = false;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getCount() {
        return count;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void complete() {
        if(!this.isCompleted) {
            this.isCompleted = true;
        }
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "product=" + product +
                ", price=" + price +
                ", bankAccount=" + bankAccount +
                ", shopName='" + shopName + '\'' +
                '}';
    }
}