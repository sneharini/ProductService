package com.sneha.productservice.models;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// parent class for common attributes
@Getter
@Setter
public class BaseModel {
    private long id;
    private Date createdAt;
    private Date updatedAt;
}
