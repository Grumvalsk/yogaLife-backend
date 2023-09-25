package com.grumvalski.yogaLifebackend.service;

import com.grumvalski.yogaLifebackend.entity.Event;
import com.grumvalski.yogaLifebackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> getAllEvents(){
        List<Event> lista=repository.findAll();
        return lista;

    }

    public Event getOneEvent(String id)throws Exception{
        Optional<Event> event=repository.findById(id);
        if(event.isPresent()){
            Event event1= event.get();
            return event1;
        }else{
            throw new Exception("Evento non presente nel db");

        }
    }

    public void createEvent(Event newEvent){
        repository.save(newEvent);
    }

    public void deleteEvent(String id) throws Exception{
        Optional<Event> event=repository.findById(id);
        if(event.isPresent()){
            Event event1= event.get();
            repository.delete(event1);
        }else{
            throw new Exception("Evento non presente nel db");

        }
    }
}
