package com.app.bookmygas.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.Stock;
import com.app.bookmygas.repository.StockRepository;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock getStockById(int id) {
        return stockRepository.findById(id).orElse(null);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock updateStock(int id, Stock stock) {
        if (stockRepository.existsById(id)) {
            stock.setStockId(id);
            return stockRepository.save(stock);
        }
        return null;
    }

    public boolean deleteStock(int id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

