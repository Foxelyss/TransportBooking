package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.model.TransportingResult;
import com.foxelyss.transportbooking.service.PointService;
import com.foxelyss.transportbooking.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private TransportService itemService;
    @Autowired
    private PointService pointService;

    @GetMapping("/point")
    public Point searchForPoint(@RequestParam(value = "point", defaultValue = "Томск") String point) {
        return pointService.findByName(point);
    }

    @GetMapping("/points")
    public List<Point> searchForPoints() {
        return pointService.getAllItems();
    }

    @GetMapping("/search")
    public List<TransportingResult> searchForTransport(@RequestParam(value = "point_a") int point_a,
            @RequestParam(value = "point_b") int point_b,
            @RequestParam(value = "quantity", defaultValue = "15") int quantity,
            @RequestParam(value = "wanted_time", defaultValue = "0") long wanted_time) {
        return itemService.findByDest(point_a, point_b, quantity, wanted_time);
    }
}

;