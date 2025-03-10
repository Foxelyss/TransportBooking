package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Company;
import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.model.TransportingResult;
import com.foxelyss.transportbooking.repos.CompanyRepo;
import com.foxelyss.transportbooking.repos.PointsRepo;
import com.foxelyss.transportbooking.repos.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo itemRepository;

    public List<Company> getAllItems() {
        return itemRepository.findAll();
    }

    public Company getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Company createItem(Company item) {
        itemRepository.save(item);
        return item; // Возвращаем созданный объект
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

}
