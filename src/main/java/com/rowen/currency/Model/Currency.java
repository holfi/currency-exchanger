package com.rowen.currency.Model;

import com.rowen.currency.Service.XmlDoubleAdapter;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Entity
@Data
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @XmlAttribute(name = "ID")
    @Column()
    String val_id;

    @XmlElement(name = "NumCode")
    String numCode;

    @XmlElement(name = "CharCode")
    String charCode;

    @XmlElement(name = "Name")
    String name;

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(XmlDoubleAdapter.class)
    Double value;

    LocalDate date;

}
