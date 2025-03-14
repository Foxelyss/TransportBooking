package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.model.TransportingResult;
import com.foxelyss.transportbooking.repos.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TransportService {
    @Autowired
    private TransportRepo transportRepo;

    public List<HashMap<String, Object>> getAllTransportingMeans() {
        return transportRepo.findAllTransportingMeans();
    }

    public TransportingResult getItemById(Long id) {
        return transportRepo.findById(id);
    }

    public Transporting createItem(Transporting item) {
        transportRepo.save(item);
        return item;
    }

    public void deleteItem(Long id) {
        transportRepo.deleteById(id);
    }

    public List<TransportingResult> findByDest(int dep_point, int arr_point, int quantity, long wanted_time, int mean) {
        if (mean == -1) {
            return transportRepo.findByDest(dep_point, arr_point, quantity, wanted_time);
        } else {
            return transportRepo.findByDest(dep_point, arr_point, quantity, wanted_time, mean);
        }

    }
}
