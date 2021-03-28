package com.rowen.currency.Model;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Converter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fromCur;

    String toCur;

    @NotNull
    Long quantity;

    Double value;

    LocalDate dateHistory;

    String username;

}
