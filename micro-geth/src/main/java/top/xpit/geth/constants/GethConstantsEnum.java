package top.xpit.geth.constants;

/**
 * @Author: ptj
 * @Date: 2023/04/15/18:35
 * @Description:
 */
public enum GethConstantsEnum {

    MICRO_BID(100, "micro_bid"),
    MICRO_GOODS(200, "micro_goods");

    private int id;
    private String sourceType;
    GethConstantsEnum(int id, String sourceType){
        this.id = id;
        this.sourceType = sourceType;
    }
    public int getID() {
        return id;
    }

    public String getSourceType() {
        return sourceType;
    }


}
