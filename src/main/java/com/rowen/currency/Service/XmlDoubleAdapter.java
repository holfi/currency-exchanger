package com.rowen.currency.Service;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlDoubleAdapter extends XmlAdapter<String, Double> {
    @Override
    public Double unmarshal(String s) throws Exception {
        return Double.parseDouble(s.replace(',', '.'));
    }

    @Override
    public String marshal(Double aDouble) throws Exception {
        return null;
    }
}
