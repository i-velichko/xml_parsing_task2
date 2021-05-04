package com.epam.secondtask;

import com.epam.secondtask.builder.MedicinesDomBuilder;
import com.epam.secondtask.builder.MedicinesSaxBuilder;
import com.epam.secondtask.model.Medicine;

public class Main {
    public static void main(String[] args) {

        MedicinesSaxBuilder medicinesSaxBuilder = new MedicinesSaxBuilder();
        Medicine medicine = medicinesSaxBuilder.parse();


        System.out.println("Medicine - " + medicine );
    }
}
