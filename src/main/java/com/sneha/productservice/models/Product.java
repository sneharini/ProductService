package com.sneha.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //this tells the hiberate to make a table out of this class
public class Product extends BaseModel{
    private String title;
    private double price;

    @ManyToOne
    private Category category;
}

/* cardinality
    1            1
* product --- category  ==> M : 1 => category id in product table
    M            1
* */
