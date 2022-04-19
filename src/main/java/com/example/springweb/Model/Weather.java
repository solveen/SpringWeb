package com.example.springweb.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Weather implements Serializable {


private String description;
private BigDecimal temp;
private BigDecimal feels_like;





}
