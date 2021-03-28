package com.rowen.currency.Model;

import com.rowen.currency.Service.XmlLocalDateAdapter;
import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@Data
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlValCurs {

    @XmlAttribute(name = "Date")
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate date;

    @XmlElement(name = "Valute")
    private List<Currency> currencies;

}
