package com.grumvalski.yogaLifebackend.controller;

import com.grumvalski.yogaLifebackend.entity.Event;
import com.grumvalski.yogaLifebackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yogaLife/event")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping("/all")
    public List<Event> getAllEvents(){
        List <Event> lista=service.getAllEvents();
        return lista;
    }

    @GetMapping("/one-event")
    public Event getOneEvent(String id) throws Exception {
        Event event=service.getOneEvent(id);
        return event;
    }
    @PostMapping("/create")
    public void createEvent(@RequestBody Event newEvent){
    service.createEvent(newEvent);
    }

    @DeleteMapping("/delete")
    public void deleteEvent(String id) throws Exception {
        service.deleteEvent(id);
    }


}
