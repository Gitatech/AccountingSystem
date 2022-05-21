package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class ApartmentService {

    private static final Logger LOGGER = LogManager.getLogger(ApartmentService.class);

    private static final String PATH_TO_FILE = "AccountingSystem/university-lab/src/main/resources/results.txt";

    private ApartmentService() {

    }

    private static class SingletonHolder {
        private static final ApartmentService APARTMENT_SERVICE = new ApartmentService();
    }

    public static ApartmentService getInstance() {
        return SingletonHolder.APARTMENT_SERVICE;
    }

    public double getTotalApartmentArea(Apartment apartment) {

        final double area = apartment.getTotalApartmentLength() * apartment.getTotalApartmentWidth();

        keepOnFile(area);

        return area;
    }

    private void keepOnFile(double area) {
        try {
            FileWriter outputStream = new FileWriter(PATH_TO_FILE, true);
            outputStream.write(String.valueOf(area) + '\n');
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
