package com.sms.store;

/**
 * Created by james.jiang on 2017/6/14.
 *
 */
public class StoreBase {
    private LoadDate loadDate;
    private NormData normData;


    public LoadDate getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(LoadDate loadDate) {
        this.loadDate = loadDate;
    }

    public NormData getNormData() {
        return normData;
    }

    public void setNormData(NormData normData) {
        this.normData = normData;
    }
}
