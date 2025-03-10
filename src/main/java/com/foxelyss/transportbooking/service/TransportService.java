package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.model.TransportingResult;
import com.foxelyss.transportbooking.repos.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {
    @Autowired
    private TransportRepo itemRepository;

    public List<TransportingResult> getAllItems() {
        return itemRepository.findAll();
    }

    public TransportingResult getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Transporting createItem(Transporting item) {
        itemRepository.save(item);
        return item;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public List<TransportingResult> findByDest(int dep_point, int arr_point, int quantity, long wanted_time) {
        return itemRepository.findByDest(dep_point, arr_point, quantity, wanted_time);
    }
}
