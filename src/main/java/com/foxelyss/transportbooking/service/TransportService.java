package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Mean;
import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.repos.TransportingResult;
import com.foxelyss.transportbooking.repos.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {
    @Autowired
    private TransportRepo transportRepo;

    public List<Mean> getAllTransportingMeans() {
        return transportRepo.findAllTransportingMeans();
    }

    public TransportingResult getItemById(Long id) {
        return transportRepo.findById(id);
    }

    public void createItem(Transporting item) {
        transportRepo.save(item);
    }

    public void deleteItem(Long id) {
        transportRepo.deleteById(id);
    }

    public List<TransportingResult> findByDest(int dep_point, int arr_point, int quantity, long wanted_time, int mean, int page) {
        if (mean == -1) {
            return transportRepo.findByDestination(dep_point, arr_point, quantity, wanted_time, page);
        } else {
            return transportRepo.findByDestination(dep_point, arr_point, quantity, wanted_time, mean, page);
        }

    }
}
