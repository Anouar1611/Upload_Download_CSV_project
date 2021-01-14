package com.project.demo.controller;

import com.project.demo.helper.CSVHelper;
import com.project.demo.message.ResponseMessage;
import com.project.demo.model.Car;
import com.project.demo.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

    }

    //SELECT * FROM Car;
    @GetMapping("/download")
    public ResponseEntity<List<Car>> getAllCars () {
            try {
                List<Car> Cars = fileService.loadDataFromDatabase();

                if (Cars.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<List<Car>>(Cars, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    
    @GetMapping(value = "/Cars/findByModel/{Model}")
    public ResponseEntity<List<Car>> getCarByModel (@PathVariable String Model) {
        try {
            List<Car> Cars = fileService.getCarsByModel(Model);

            if (Cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Car>>(Cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/Cars/findByName/{name}")
    public ResponseEntity<List<Car>> getCarsByName (@PathVariable String name) {
        try {
            List<Car> Cars = fileService.getCarByName(name);

            if (Cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Car>>(Cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/Cars/findCarWithLessPowerHorse/stream")
    public ResponseEntity<Car> getCarHaveLessHorsePower () {
        try {
            Car Car = fileService.getCarHaveLessHorsePoweer();

            if (Car == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/Cars/sortedByModelAndHorsePower/stream/")
    public ResponseEntity<List<Car>> sortedByModelAndHorsePower() {
        try {
            List<Car> Cars = fileService.sortedByModelAndHorsePower();

            if (Cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Car>>(Cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/Cars/getCarsByModelAndSortedByHorsePower/stream/{model}")
    public ResponseEntity<List<Car>> getCarsByModelAndSortedByHorsePower(@PathVariable String model) {
        try {
            List<Car> Cars=fileService.getCarsByNameAndSortedByModel(model);

            if (Cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Car>>(Cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/Cars/getCarsByModelOfOriginAndSortedByModel/stream/{origin}/{model}")
    public ResponseEntity<List<Car>> getCarsByModelAndSortedByHorsePower(@PathVariable String origin,@PathVariable String model) {
        try {
            List<Car> Cars=fileService.getCarsByModelAndOriginAndSortedByHorsePower(origin,model);

            if (Cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Car>>(Cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


