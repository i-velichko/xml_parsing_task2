package com.epam.secondtask.parser;

import com.epam.secondtask.model.Medicine;

import java.util.List;

public interface AbstractMedicineBuilder {


List<Medicine> buildMedicinesList (String filePath);

}
