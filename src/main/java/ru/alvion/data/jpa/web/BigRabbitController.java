package ru.alvion.data.jpa.web;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.alvion.data.jpa.domain.BigRabbit;
import ru.alvion.data.jpa.service.BigRabbitService;
import ru.alvion.data.jpa.service.exception.RabbitException;
import ru.alvion.data.jpa.web.dto.RabbitMotionEventDto;
import ru.alvion.data.jpa.web.dto.RabbitPastureEventDto;

import java.net.URI;

@RestController
@RequestMapping("/rabbits")
public class BigRabbitController {
    private static final Logger logger = LoggerFactory.getLogger(BigRabbitController.class);

    @Autowired
    private BigRabbitService bigRabbitService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BigRabbit> createRabbit(@RequestBody BigRabbit rabbit) {

        BigRabbit newRabbit = bigRabbitService.createRabbit(rabbit);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRabbitUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRabbit.getId())
                .toUri();
        responseHeaders.setLocation(newRabbitUri);
        return new ResponseEntity<>(newRabbit, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{rabbitId}", method = RequestMethod.GET)
    public ResponseEntity<BigRabbit> getRabbit(@PathVariable Long rabbitId) {
        BigRabbit rabbit = bigRabbitService.getRabbit(rabbitId);
        return new ResponseEntity<>(rabbit, HttpStatus.OK);
    }

    /*@RequestMapping(value = "/{rabbitId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> killRabbit(@PathVariable Long rabbitId) {
        bigRabbitService.killRabbit(rabbitId);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @RequestMapping(method = RequestMethod.GET/*, consumes = {"application/json","application/yaml"}*/)
    public ResponseEntity<Iterable<BigRabbit>> listRabbits() {
        Iterable<BigRabbit> rabbits = bigRabbitService.findAllRabbits();
        return new ResponseEntity<>(rabbits, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<BigRabbit> updateRabbit(@RequestBody BigRabbit rabbit) {
        BigRabbit updated = bigRabbitService.updateRabbit(rabbit);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @RequestMapping(value = "/{rabbitId}/motions", method = RequestMethod.GET)
    public ResponseEntity<Iterable<RabbitMotionEventDto>> listRabbitMotions(@PathVariable("rabbitId") long rabbitId) {
        Iterable<RabbitMotionEventDto> motionEvents = bigRabbitService.listRabbitMotions(rabbitId);
        return new ResponseEntity<>(motionEvents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{rabbitId}/pastures", method = RequestMethod.GET)
    public ResponseEntity<Iterable<RabbitPastureEventDto>> listRabbitPastures(@PathVariable("rabbitId") long rabbitId) {
        Iterable<RabbitPastureEventDto> pastureEvents = bigRabbitService.listRabbitPastures(rabbitId);
        return new ResponseEntity<>(pastureEvents, HttpStatus.OK);
    }

    @RequestMapping(value = "/motion", method = RequestMethod.POST)
    public ResponseEntity<RabbitMotionEventDto> createRabbitMotion(@RequestBody RabbitMotionEventDto dto) {

        RabbitMotionEventDto motionEventDto = bigRabbitService.createRabbitMotionEvent(dto);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(motionEventDto.getId())
                .toUri();
        responseHeaders.setLocation(location);
        return new ResponseEntity<>(motionEventDto, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/pasture", method = RequestMethod.POST)
    public ResponseEntity<RabbitPastureEventDto> createRabbitPastureEvent(@RequestBody RabbitPastureEventDto dto) {

        RabbitPastureEventDto pastureEventDto = bigRabbitService.createRabbitPastureEvent(dto);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pastureEventDto.getId())
                .toUri();
        responseHeaders.setLocation(location);
        return new ResponseEntity<>(pastureEventDto, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/calculateEnergy/{rabbitId}", method = RequestMethod.GET)
    public ResponseEntity<?> calculateRabbitEnergy(@PathVariable Long rabbitId,
                                                   @RequestParam DateTime fromDate,
                                                   @RequestParam DateTime toDate) {
        String energy = bigRabbitService.calculateRabbitEnergy(rabbitId, fromDate, toDate);
        return new ResponseEntity<>(energy, HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {

        logger.warn(ClassUtils.getShortName(ex.getClass()) + " -- " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RabbitException.class)
    public ResponseEntity<?> handleRabbitException(RabbitException ex) {

        logger.warn(ClassUtils.getShortName(ex.getClass()) + " -- " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
