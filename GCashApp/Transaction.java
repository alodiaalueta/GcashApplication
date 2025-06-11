package GCashApp;

public class Transaction {
    private String id;
    private double amount;
    private String recipientName;
    private String recipientAccountId;
    private String transferToId;
    private String transferFromId;
    private String type;

    public Transaction(double amount, String recipientName, String recipientAccountId,
                       String transferToId, String transferFromId, String type) {
        this.id = "TX" + System.currentTimeMillis(); 
        this.amount = amount;
        this.recipientName = recipientName;
        this.recipientAccountId = recipientAccountId;
        this.transferToId = transferToId;
        this.transferFromId = transferFromId;
        this.type = type;
    }

    
    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientAccountId() {
        return recipientAccountId;
    }

    public String getTransferToId() {
        return transferToId;
    }

    public String getTransferFromId() {
        return transferFromId;
    }

    public String getType() {
        return type;
    }
}
