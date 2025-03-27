package com.foxelyss.transportbooking.service;

import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.repos.PointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {
    @Autowired
    private PointsRepo itemRepository;

    public List<Point> getAllItems() {
        return itemRepository.findAll();
    }

    public Point getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Point createItem(Point item) {
        itemRepository.save(item);
        return item;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Point findByName(String name) {
        return itemRepository.findByName(name);
    }
}
