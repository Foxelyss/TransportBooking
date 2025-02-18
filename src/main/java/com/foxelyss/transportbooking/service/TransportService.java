package com.foxelyss.transportbooking.service;
import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.repos.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {
    @Autowired
    private TransportRepo itemRepository;

    public List<Transporting> getAllItems() {
        return itemRepository.findAll();
    }

    public Transporting getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Transporting createItem(Transporting item) {
        itemRepository.save(item);
        return item; // Возвращаем созданный объект
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Transporting> findByDest(int dep_point, int arr_point) {
        return itemRepository.findByDest(dep_point, arr_point);
    }
}
