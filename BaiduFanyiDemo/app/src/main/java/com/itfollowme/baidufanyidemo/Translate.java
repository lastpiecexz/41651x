package com.itfollowme.baidufanyidemo;

import java.util.Arrays;
import java.util.Map;

public class Translate {
    private String from;
    private String to;
    private Map[] trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map[] getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(Map[] trans_result) {
        this.trans_result = trans_result;
    }

    @Override
    public String toString() {
        return "Translate [from=" + from + ", to=" + to + ", trans_result=" + Arrays.toString(trans_result) + "]";
    }


}
