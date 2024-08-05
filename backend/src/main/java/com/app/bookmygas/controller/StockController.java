package com.app.bookmygas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.bookmygas.entity.Stock;
import com.app.bookmygas.service.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> createStock(@Valid @RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Integer id) {
        Stock stock = stockService.getStockById(id);
        return stock != null ? new ResponseEntity<>(stock, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Integer id, @Valid @RequestBody Stock stock) {
        Stock updatedStock = stockService.updateStock(id, stock);
        return updatedStock != null ? new ResponseEntity<>(updatedStock, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        boolean isDeleted = stockService.deleteStock(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}