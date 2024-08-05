package com.app.bookmygas.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockid")
    private Integer stockId;

    @ManyToOne
    @JoinColumn(name = "agencyid", nullable = false)
    @JsonIgnore
    private GasAgency gasAgency;

    @Enumerated(EnumType.STRING)
    @Column(name = "gastype", nullable = false)
    @NotNull(message = "Gas type cannot be null")
    private GasType gasType;

    @Column(name = "quantityavailable", nullable = false)
    @NotNull(message = "Quantity available cannot be null")
    @Positive(message = "Quantity available must be positive")
    private Integer quantityAvailable;

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public GasAgency getGasAgency() {
		return gasAgency;
	}

	public void setGasAgency(GasAgency gasAgency) {
		this.gasAgency = gasAgency;
	}

	public GasType getGasType() {
		return gasType;
	}

	public void setGasType(GasType gasType) {
		this.gasType = gasType;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
    
    
}