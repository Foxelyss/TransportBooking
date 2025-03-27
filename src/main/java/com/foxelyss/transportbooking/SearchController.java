package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Mean;
import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.repos.TransportingResult;
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
    private TransportService transportService;
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

    @GetMapping("/means")
    public List<Mean> searchForMeans() {
        return transportService.getAllTransportingMeans();
    }

    @GetMapping("/search")
    public List<TransportingResult> searchForTransport(@RequestParam(value = "point_a") int point_a,
                                                       @RequestParam(value = "point_b") int point_b,
                                                       @RequestParam(value = "quantity", defaultValue = "15") int quantity,
                                                       @RequestParam(value = "wanted_time", defaultValue = "0") long wanted_time,
                                                       @RequestParam(value = "mean", defaultValue = "-1") int mean) {
        return transportService.findByDest(point_a, point_b, quantity, wanted_time, mean);
    }
}

;